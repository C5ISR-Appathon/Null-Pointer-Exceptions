package mil.spawar.npe;

import mil.spawar.npe.DeviceListFragment.DeviceActionListener;
import android.app.Activity;
import android.net.wifi.p2p.WifiP2pManager.ChannelListener;

public abstract class AbstractNetworkActivity extends Activity implements ChannelListener, DeviceActionListener{
	
	 public boolean isWifiP2pEnabled = false;
	 public void setIsWifiP2pEnabled(boolean isWifiP2pEnabled) 
	 {
	        this.isWifiP2pEnabled = isWifiP2pEnabled;
	 }
	 public void resetData() 
	 {
	        DeviceListFragment fragmentList = (DeviceListFragment) getFragmentManager()
	                .findFragmentById(R.id.frag_list);
	        DeviceDetailFragment fragmentDetails = (DeviceDetailFragment) getFragmentManager()
	                .findFragmentById(R.id.frag_detail);
	        if (fragmentList != null) {
	            fragmentList.clearPeers();
	        }
	        if (fragmentDetails != null) {
	            fragmentDetails.resetViews();
	        }
	}
	 
}
