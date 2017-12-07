package de.hft_stuttgart.sw.projectindoorapp.broadcast_receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import de.hft_stuttgart.sw.projectindoorapp.activities.MapActivity;
import de.hft_stuttgart.sw.projectindoorapp.models.AccessPoint;
import de.hft_stuttgart.sw.projectindoorapp.models.Position;
import de.hft_stuttgart.sw.projectindoorapp.models.RSSISignal;
import de.hft_stuttgart.sw.projectindoorapp.services.PositioningService;


public class WifiReceiver extends BroadcastReceiver {

    private static final String LOG_TAG = "WifiReceiver";
    private WifiManager wifiManager;
    protected MapActivity activity;

    public WifiReceiver(WifiManager wifiManager, MapActivity activity) {
        this.wifiManager = wifiManager;
        this.activity = activity;
    }


    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(LOG_TAG, "... receive wifi scan results.");
        List<ScanResult> scanResults = this.wifiManager.getScanResults();

        final List<String> wifiReadings = new ArrayList<>();
        String wifiReading;
        for (int i = 0; i < scanResults.size(); i++) {
            ScanResult result = scanResults.get(i);

            AccessPoint accessPoint = new AccessPoint();
            accessPoint.setMacAddress(result.BSSID);
            RSSISignal signal = new RSSISignal();
            signal.setSignalStrength(result.level);
            signal.setAccessPoint(accessPoint);

            // "WIFI;2.795;4985.268;test-CAR;00:0b:86:27:36:c2;-83"
            wifiReading = "WIFI;" + result.timestamp + ";" + result.timestamp + ";" + result.SSID + ";" + result.BSSID + ";" + result.level;
            wifiReadings.add(wifiReading);

            Log.i(LOG_TAG, signal.toString());
        }

        // java.lang.OutOfMemoryError: Could not allocate JNI Env
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                PositioningService positioningService = new PositioningService();
                final Position currentPosition = positioningService.generateSinglePositionResult(wifiReadings);
                if (currentPosition.getLatitude() != 0 && currentPosition.getLongitude() != 0) {
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            activity.addPositionToTrack(currentPosition);
                        }
                    });
                }
            }
        });
        t.start();
    }
}
