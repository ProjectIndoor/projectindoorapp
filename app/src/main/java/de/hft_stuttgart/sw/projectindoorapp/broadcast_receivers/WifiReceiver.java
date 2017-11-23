package de.hft_stuttgart.sw.projectindoorapp.broadcast_receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.util.Log;

import java.util.List;


public class WifiReceiver extends BroadcastReceiver {
 WifiManager wifiManager;

public WifiReceiver(WifiManager wifiManager) {


    this.wifiManager = wifiManager;
}


    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("WifiReceiver","receive...");
        List<ScanResult> scanResults = this.wifiManager.getScanResults();
        for(int i=0;i<scanResults.size();i++){
            ScanResult result=scanResults.get(i);
            Log.i("WifiReceiver",result.BSSID +"......"+result.level);


        }
    }
}
