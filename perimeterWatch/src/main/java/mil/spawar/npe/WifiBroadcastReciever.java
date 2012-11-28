package mil.spawar.npe;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.p2p.WifiP2pManager;
import android.net.wifi.p2p.WifiP2pManager.Channel;

public class WifiBroadcastReciever extends BroadcastReceiver {

    private WifiP2pManager manager;
    private Channel channel;
    private Activity activity;
    
    public WifiBroadcastReciever(WifiP2pManager manager, Channel channel,Activity activity) 
    {
        super();
        this.manager = manager;
        this.channel = channel;
        this.activity = activity;
    }
	@Override
	public void onReceive(Context context, Intent intent) 
	{

		
	}

}
