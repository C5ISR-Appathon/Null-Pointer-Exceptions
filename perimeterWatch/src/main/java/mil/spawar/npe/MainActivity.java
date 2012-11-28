package mil.spawar.npe;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.p2p.WifiP2pManager;
import android.net.wifi.p2p.WifiP2pManager.Channel;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

	private static String TAG = "perimeterWatch";
	private WifiP2pManager manager;
	private Channel channel;
	private BroadcastReceiver receiver;
	private IntentFilter intentFilter;

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
		manager = (WifiP2pManager) getSystemService(Context.WIFI_P2P_SERVICE);
		channel = manager.initialize(this, getMainLooper(), null);
		receiver = new WifiBroadcastReciever(manager, channel, this);
		setContentView(R.layout.main);

		Button clientSelect = (Button) findViewById(R.id.clientselect);

		intentFilter = new IntentFilter();
		intentFilter.addAction(WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION);
		intentFilter.addAction(WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION);
		intentFilter
				.addAction(WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION);
		intentFilter
				.addAction(WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION);

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
	}

}
