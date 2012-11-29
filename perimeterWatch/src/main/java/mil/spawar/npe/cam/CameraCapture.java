package mil.spawar.npe.cam;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Calendar;

import mil.spawar.npe.FileTransferService;
import mil.spawar.npe.R;
import mil.spawar.npe.DeviceDetailFragment.FileServerAsyncTask;
import android.app.Activity;
import android.content.Intent;
import android.graphics.ImageFormat;
import android.hardware.Camera;
import android.net.wifi.p2p.WifiP2pInfo;
import android.net.wifi.p2p.WifiP2pManager.ConnectionInfoListener;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class CameraCapture extends Activity implements ConnectionInfoListener {
	private final static String TAG = "CameraCapture";

	private SurfaceView preview = null;
	private SurfaceHolder previewHolder = null;
	private Camera camera = null;
	private boolean inPreview = false;
	private boolean cameraConfigured = false;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.camera_layout);

		preview = (SurfaceView) findViewById(R.id.preview);
		previewHolder = preview.getHolder();
		previewHolder.addCallback(surfaceCallback);
		previewHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
	}

	@Override
	public void onBackPressed()
	{
		System.exit(0);
	}
	
	@Override
	public void onResume() {
		super.onResume();

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
			Camera.CameraInfo info = new Camera.CameraInfo();

			for (int i = 0; i < Camera.getNumberOfCameras(); i++) {
				Camera.getCameraInfo(i, info);

				if (info.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
					camera = Camera.open(i);
				}
			}
		}

		if (camera == null) {
			camera = Camera.open();
		}

		startPreview();

		continueTakingPics = true;
		new TakePhotoTask().execute();
	}

	@Override
	public void onPause() {

		continueTakingPics = false;

		if (inPreview) {
			camera.stopPreview();
		}

		camera.release();
		camera = null;
		inPreview = false;

		super.onPause();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		new MenuInflater(this).inflate(R.menu.camera_options, menu);

		return (super.onCreateOptionsMenu(menu));
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == R.id.camera) {
			if (inPreview) {
				camera.takePicture(null, null, photoCallback);
				inPreview = false;
			}
		}

		return (super.onOptionsItemSelected(item));
	}

	private Camera.Size getBestPreviewSize(int width, int height,
			Camera.Parameters parameters) {
		Camera.Size result = null;

		for (Camera.Size size : parameters.getSupportedPreviewSizes()) {
			if (size.width <= width && size.height <= height) {
				if (result == null) {
					result = size;
				} else {
					int resultArea = result.width * result.height;
					int newArea = size.width * size.height;

					if (newArea > resultArea) {
						result = size;
					}
				}
			}
		}

		return (result);
	}

	private Camera.Size getSmallestPictureSize(Camera.Parameters parameters) {
		Camera.Size result = null;

		for (Camera.Size size : parameters.getSupportedPictureSizes()) {
			if (result == null) {
				result = size;
			} else {
				int resultArea = result.width * result.height;
				int newArea = size.width * size.height;

				if (newArea < resultArea) {
					result = size;
				}
			}
		}

		return (result);
	}

	private void initPreview(int width, int height) {
		if (camera != null && previewHolder.getSurface() != null) {
			try {
				camera.setPreviewDisplay(previewHolder);
			} catch (Throwable t) {
				Log.e("PreviewDemo-surfaceCallback",
						"Exception in setPreviewDisplay()", t);
				Toast.makeText(this, t.getMessage(), Toast.LENGTH_LONG).show();
			}

			if (!cameraConfigured) {
				Camera.Parameters parameters = camera.getParameters();
				Camera.Size size = getBestPreviewSize(width, height, parameters);
				Camera.Size pictureSize = getSmallestPictureSize(parameters);

				if (size != null && pictureSize != null) {
					parameters.setPreviewSize(size.width, size.height);
					parameters.setPictureSize(pictureSize.width,
							pictureSize.height);
					parameters.setPictureFormat(ImageFormat.JPEG);
					camera.setParameters(parameters);
					cameraConfigured = true;
				}
			}
		}
	}

	private void startPreview() {
		if (cameraConfigured && camera != null) {
			// camera.setPreviewCallback(previewCallback);
			camera.startPreview();
			inPreview = true;
		}
	}

	SurfaceHolder.Callback surfaceCallback = new SurfaceHolder.Callback() {
		public void surfaceCreated(SurfaceHolder holder) {
			// no-op -- wait until surfaceChanged()
		}

		public void surfaceChanged(SurfaceHolder holder, int format, int width,
				int height) {
			initPreview(width, height);
			startPreview();
		}

		public void surfaceDestroyed(SurfaceHolder holder) {
			// no-op
		}
	};

	Camera.PictureCallback photoCallback = new Camera.PictureCallback() {
		public void onPictureTaken(byte[] data, Camera camera) {
			Log.d(TAG, "Got picture data, saving to file...");
			new SavePhotoTask().execute(data);
			camera.startPreview();
			inPreview = true;
		}
	};

	public String storageDir = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Pictures";
	
	class SavePhotoTask extends AsyncTask<byte[], String, String> {
		@Override
		protected String doInBackground(byte[]... jpeg) {
			String picDir = storageDir + "/" + R.id.device_address;
			Log.d(TAG, "Attempting to save photo to " + picDir);
			File photo = new File(picDir, Calendar.getInstance()
					.getTimeInMillis() + ".jpg");

			if (photo.exists()) {
				photo.delete();
			}

			FileOutputStream fos = null;
			try {
				fos = new FileOutputStream(photo.getPath());

				fos.write(jpeg[0]);

				Intent serviceIntent = new Intent(CameraCapture.this, FileTransferService.class);
				serviceIntent.setAction(FileTransferService.ACTION_SEND_FILE);
				serviceIntent.putExtra(FileTransferService.EXTRAS_FILE_PATH, photo.getPath());
				serviceIntent.putExtra(FileTransferService.EXTRAS_GROUP_OWNER_ADDRESS, info.groupOwnerAddress.getHostAddress());
				serviceIntent.putExtra(FileTransferService.EXTRAS_GROUP_OWNER_PORT, 8988);
				startService(serviceIntent);

			} catch (java.io.IOException e) {
				Log.e("PictureDemo", "Exception in photoCallback", e);
				try {fos.close();} catch (Exception ex) { /* do nothing */};
			}

			return (null);
		}
	}

	boolean continueTakingPics = false;

	class TakePhotoTask extends AsyncTask {

		@Override
		protected Object doInBackground(Object... arg0) {
			Log.d(TAG, "do in background called...");

			while (continueTakingPics) {
				try {
					Thread.sleep(1000 * 2);
					Log.d(TAG, "Taking photo...");
					camera.takePicture(null, null, photoCallback);
				} catch (InterruptedException e) {
					continueTakingPics = false;
				}
			}
			return null;
		}

	}

	WifiP2pInfo info;

	@Override
	public void onConnectionInfoAvailable(WifiP2pInfo info) {
		this.info = info;
	}
}