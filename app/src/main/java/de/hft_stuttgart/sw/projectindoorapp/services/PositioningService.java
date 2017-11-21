package de.hft_stuttgart.sw.projectindoorapp.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import retrofit2.Retrofit;


public class PositioningService extends Service {

    private PositioningRestClient positioningService;

    public PositioningService() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("").build();
        positioningService = retrofit.create(PositioningRestClient.class);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public void getPositionFromWifiReading(String wifiReading) {
        positioningService.getPositionForWifiReading(wifiReading);
    }

}
