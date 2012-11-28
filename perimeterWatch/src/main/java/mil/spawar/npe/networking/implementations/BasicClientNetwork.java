package mil.spawar.npe.networking.implementations;

import java.util.List;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.net.wifi.p2p.WifiP2pManager;
import android.net.wifi.p2p.WifiP2pManager.Channel;
import android.widget.Toast;

import mil.spawar.npe.MainActivity;
import mil.spawar.npe.WifiBroadcastReciever;
import mil.spawar.npe.networking.interfaces.ClientNetworkInterface;
import mil.spawar.npe.objects.Server;

public class BasicClientNetwork implements ClientNetworkInterface {

    
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
