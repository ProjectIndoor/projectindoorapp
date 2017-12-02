package de.hft_stuttgart.sw.projectindoorapp.broadcast_receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.util.Log;

import java.util.List;

import de.hft_stuttgart.sw.projectindoorapp.activities.MapActivity;
import de.hft_stuttgart.sw.projectindoorapp.models.AccessPoint;
import de.hft_stuttgart.sw.projectindoorapp.models.Position;
import de.hft_stuttgart.sw.projectindoorapp.models.RSSISignal;
import de.hft_stuttgart.sw.projectindoorapp.models.RadioMapElement;
import de.hft_stuttgart.sw.projectindoorapp.services.PositioningService;
import io.realm.Realm;


public class WifiReceiver extends BroadcastReceiver {

    private static final String LOG_TAG = "WifiReceiver";
    private WifiManager wifiManager;
    protected MapActivity activity;
    private Realm realm;

    public WifiReceiver(WifiManager wifiManager, MapActivity activity) {
        this.wifiManager = wifiManager;
        this.activity = activity;
    }


    @Override
    public void onReceive(Context context, Intent intent) {
        realm = Realm.getDefaultInstance();
        Log.i(LOG_TAG, "receive...");
        List<ScanResult> scanResults = this.wifiManager.getScanResults();

        RadioMapElement row = new RadioMapElement();
        int id;
        try {
            id = realm.where(RadioMapElement.class).max("id").intValue() + 1;
        } catch (NullPointerException ex) {
            id = 0;
        }
        row.setId(id);

        for (int i = 0; i < scanResults.size(); i++) {

            ScanResult result = scanResults.get(i);

            AccessPoint accessPoint = new AccessPoint();
            accessPoint.setMacAddress(result.BSSID);
            try {
                id = realm.where(AccessPoint.class).max("id").intValue() + 1;
            } catch (NullPointerException ex) {
                id = 0;
            }
            accessPoint.setId(id);

            final RSSISignal signal = new RSSISignal();
            signal.setSignalStrength(result.level);
            try {
                id = realm.where(RSSISignal.class).max("id").intValue() + 1;
            } catch (NullPointerException ex) {
                id = 0;
            }
            signal.setId(id);

            signal.setAccessPoint(accessPoint);
            signal.setRadioMapElement(row);


            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    realm.copyToRealmOrUpdate(signal);
                }
            });

            Log.i(LOG_TAG, signal.toString());
        }

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                PositioningService positioningService = new PositioningService();
                final Position position = positioningService.getPositionFromWifiReading("");
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        activity.addPositionToTrack(position);
                    }
                });
            }
        });
        t.start();
        this.wifiManager.startScan();
    }
}
