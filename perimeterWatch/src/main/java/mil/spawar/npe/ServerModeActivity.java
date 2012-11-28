package mil.spawar.npe;

import java.util.ArrayList;

import mil.spawar.npe.networking.implementations.BasicServerNetwork;
import mil.spawar.npe.networking.interfaces.ServerNetworkInterface;

import android.app.Activity;
import android.app.ListActivity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.net.wifi.p2p.WifiP2pManager;
import android.net.wifi.p2p.WifiP2pManager.Channel;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;

public class ServerModeActivity extends ListActivity {
	private static String TAG = "testApp";
	private ServerNetworkInterface network; 
	
	// LIST OF ARRAY STRINGS WHICH WILL SERVE AS LIST ITEMS
	ArrayList<String> listItems = new ArrayList<String>();

	// DEFINING STRING ADAPTER WHICH WILL HANDLE DATA OF LISTVIEW
	ArrayAdapter<String> adapter;

	// RECORDING HOW MUCH TIMES BUTTON WAS CLICKED
	int clickCounter = 0;
	private IntentFilter intentFilter;
	
	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		Log.i(TAG, "onCreate");

		network=new BasicServerNetwork(this);
		
		setContentView(R.layout.serverview);
		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, listItems);
		setListAdapter(adapter);

		Button quit = (Button) findViewById(R.id.quit);

		intentFilter = new IntentFilter();
		intentFilter.addAction(WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION);
		intentFilter.addAction(WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION);
		intentFilter.addAction(WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION);
		intentFilter.addAction(WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION);

		quit.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				finish();
				System.exit(0);
			}

		});

		listItems.add("Client 1");
		listItems.add("Client 2");
		listItems.add("Client 3");
	}

	// METHOD WHICH WILL HANDLE DYNAMIC INSERTION
	public void addItems(View v) {
		listItems.add("Clicked : " + clickCounter++);
		adapter.notifyDataSetChanged();
	}
}