package mil.spawar.npe.networking.implementations;

import java.util.List;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.net.wifi.p2p.WifiP2pManager;
import android.net.wifi.p2p.WifiP2pManager.Channel;

import mil.spawar.npe.WifiBroadcastReciever;
import mil.spawar.npe.networking.interfaces.ServerNetworkInterface;
import mil.spawar.npe.objects.Client;

public class BasicServerNetwork implements ServerNetworkInterface {
	
	private WifiP2pManager manager;
	private Channel channel;
	private BroadcastReceiver receiver;
	
	public  BasicServerNetwork(Activity activity)
	{
		manager = (WifiP2pManager) activity.getSystemService(Context.WIFI_P2P_SERVICE);
		channel = manager.initialize(activity, activity.getMainLooper(), null);
		receiver = new WifiBroadcastReciever(manager, channel, activity);
	}
	
	@Override
	public List<Client> getAllConnectedClients() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void disconnectAllClients() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void disconnectClient(Client client) {
		// TODO Auto-generated method stub
		
	}



}
