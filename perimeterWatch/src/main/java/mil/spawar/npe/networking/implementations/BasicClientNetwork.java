package mil.spawar.npe.networking.implementations;

import java.util.List;

//import mil.spawar.npe.WifiBroadcastReciever;
import mil.spawar.npe.networking.interfaces.ClientNetworkInterface;
import mil.spawar.npe.objects.Server;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.net.wifi.p2p.WifiP2pManager;
import android.net.wifi.p2p.WifiP2pManager.Channel;

public class BasicClientNetwork implements ClientNetworkInterface {

	private WifiP2pManager manager;
	private Channel channel;
	private BroadcastReceiver receiver;
	
	public  BasicClientNetwork(Activity activity)
	{
		manager = (WifiP2pManager) activity.getSystemService(Context.WIFI_P2P_SERVICE);
		channel = manager.initialize(activity, activity.getMainLooper(), null);
		//receiver = new WifiBroadcastReciever(manager, channel, activity);
	}
		
	@Override
	public List<Server> getAllServers() {
		
		manager.discoverPeers(channel, new WifiP2pManager.ActionListener() {

            @Override
            public void onSuccess() 
            {

            }

            @Override
            public void onFailure(int reasonCode) 
            {
            	
            }

            });
		
		return null;
	}

	@Override
	public void connectToServer(Server server) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void disconnectFromServer() {
		// TODO Auto-generated method stub
		
	}



}
