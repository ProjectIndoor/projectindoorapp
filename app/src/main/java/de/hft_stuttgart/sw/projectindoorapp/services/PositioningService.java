package de.hft_stuttgart.sw.projectindoorapp.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

import de.hft_stuttgart.sw.projectindoorapp.models.MockPositionData;
import de.hft_stuttgart.sw.projectindoorapp.models.Position;


public class PositioningService extends Service {

    private PositionRestClient positioningService;
    private List<LatLng> points = new ArrayList<LatLng>();

    private int index = 0;

    public PositioningService() {
        //Retrofit retrofit = new Retrofit.Builder().baseUrl("").build();
        //positioningService = retrofit.create(PositionRestClient.class);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * @param wifiReading wifi line
     *                    WIFI data: 'WIFI;AppTimestamp(s);SensorTimeStamp(s);Name_SSID;MAC_BSSID;RSS(dBm);
     * @return returns position received from server or empty position in case of IOException.
     */
    public Position getPositionFromWifiReading(String wifiReading) {
        MockPositionData data = new MockPositionData(new Position(48.780551, 9.171766), new Position(48.780159, 9.173488));

        return data.getPosition();
        /*
        //Position position;
        Call<Position> call = positioningService.getPositionForWifiReading(wifiReading);

        try {
            return call.execute().body();
        } catch (IOException exception) {
            // TODO: properly handle exception.
            return new Position();
        }*/
    }

}
