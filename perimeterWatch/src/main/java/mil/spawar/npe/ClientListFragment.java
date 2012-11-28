/*
 * Copyright (C) 2011 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package mil.spawar.npe;

import java.util.Collection;

import android.net.wifi.p2p.WifiP2pDevice;
import android.net.wifi.p2p.WifiP2pDeviceList;
import android.util.Log;

/**
 * A ListFragment that displays available peers on discovery and requests the
 * parent activity to handle user interaction events
 */
public class ClientListFragment extends DeviceListFragment {
    @Override
    public void onPeersAvailable(WifiP2pDeviceList peerList) {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
        peers.clear();
        
        Collection<WifiP2pDevice> temp=peerList.getDeviceList();
        
        for(WifiP2pDevice device: temp)
        {
        	if(this.device.status==WifiP2pDevice.CONNECTED)
        	{
	        	if(device.status==WifiP2pDevice.CONNECTED)
	        		peers.add(device);
        	}
        	else
        	{
        		if(device.status==WifiP2pDevice.AVAILABLE)
	        		peers.add(device);
        	}
        }
        
        ((WiFiPeerListAdapter) getListAdapter()).notifyDataSetChanged();
        if (peers.size() == 0) {
            Log.d(ClientModeActivity.TAG, "No devices found");
            return;
        }

    }

}
