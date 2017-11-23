package de.hft_stuttgart.sw.projectindoorapp.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import java.io.IOException;

import de.hft_stuttgart.sw.projectindoorapp.models.Position;
import retrofit2.Call;
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

    /**
     * @param wifiReading wifi line
     * @return returns position received from server or empty position in case of IOException.
     */
    public Position getPositionFromWifiReading(String wifiReading) {
        //Position position;
        Call<Position> call = positioningService.getPositionForWifiReading(wifiReading);

        try {
            return call.execute().body();
        } catch (IOException exception) {
            // TODO: properly handle exception.
            return new Position();
        }
    }

}
