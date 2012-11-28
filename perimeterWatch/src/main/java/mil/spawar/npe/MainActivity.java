package mil.spawar.npe;

import mil.spawar.npe.cam.CameraCapture;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

	private static String TAG = "perimeterWatch";

	/**
	 * Called when the activity is first created.
	 * 
	 * @param savedInstanceState
	 *            If the activity is being re-initialized after previously being
	 *            shut down then this Bundle contains the data it most recently
	 *            supplied in onSaveInstanceState(Bundle). <b>Note: Otherwise it
	 *            is null.</b>
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.i(TAG, "onCreate");
		setContentView(R.layout.main);
		Button clientSelect = (Button) findViewById(R.id.clientselect);
		clientSelect.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// Launching new Activity on selecting single List Item
				Intent i = new Intent(getApplicationContext(),
						ClientModeActivity.class);
				startActivity(i);
			}

		});

		Button serverSelect = (Button) findViewById(R.id.serverselect);
		serverSelect.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// Launching new Activity on selecting single List Item
				Intent i = new Intent(getApplicationContext(),
						ServerModeActivity.class);
				startActivity(i);
			}

		});

		Button quit = (Button) findViewById(R.id.quit);
		quit.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				finish();
				System.exit(0);
			}

		});
		
		Button camera = (Button) findViewById(R.id.camera);
		camera.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(MainActivity.this, CameraCapture.class);
		    	startActivity(intent);
			}

		});
	}
	
	 public void startCamera(View v){
	    	Intent intent = new Intent(this, CameraCapture.class);
	    	startActivity(intent);
	    }

}

