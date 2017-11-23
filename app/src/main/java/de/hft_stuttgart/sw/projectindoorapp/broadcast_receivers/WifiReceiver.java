package de.hft_stuttgart.sw.projectindoorapp.broadcast_receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.util.Log;

import java.util.List;

import de.hft_stuttgart.sw.projectindoorapp.models.AccessPoint;
import de.hft_stuttgart.sw.projectindoorapp.models.RSSISignal;


public class WifiReceiver extends BroadcastReceiver {

    private static final String LOG_TAG = "WifiReceiver";
    private WifiManager wifiManager;

    public WifiReceiver(WifiManager wifiManager) {
        this.wifiManager = wifiManager;
    }


    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(LOG_TAG, "receive...");
        List<ScanResult> scanResults = this.wifiManager.getScanResults();
        for (int i = 0; i < scanResults.size(); i++) {
            ScanResult result = scanResults.get(i);

            AccessPoint accessPoint = new AccessPoint();
            accessPoint.setMacAddress(result.BSSID);
            RSSISignal signal = new RSSISignal();
            signal.setSignalStrength(result.level);
            signal.setAccessPoint(accessPoint);

            Log.i(LOG_TAG, signal.toString());
        }
    }
}
