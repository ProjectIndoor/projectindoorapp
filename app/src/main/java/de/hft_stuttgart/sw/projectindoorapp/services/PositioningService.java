package de.hft_stuttgart.sw.projectindoorapp.services;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.preference.PreferenceManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import de.hft_stuttgart.sw.projectindoorapp.R;
import de.hft_stuttgart.sw.projectindoorapp.models.MockPositionData;
import de.hft_stuttgart.sw.projectindoorapp.models.Position;
import de.hft_stuttgart.sw.projectindoorapp.models.requests.SinglePositionRequest;
import de.hft_stuttgart.sw.projectindoorapp.network.Networking;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class PositioningService extends Service {

    private PositionRestClient restClient;
    private Context context;

    public PositioningService(Context context) {
        OkHttpClient client = new OkHttpClient.Builder()
                //.addNetworkInterceptor(this)
                .connectionPool(Networking.getPool())
                //.addInterceptor(logging)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://doblix.de/indoorweb/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        restClient = retrofit.create(PositionRestClient.class);
        this.context = context;
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
    public Position generateSinglePositionResult(List<String> wifiReading) {
        SinglePositionRequest singlePositionRequest = new SinglePositionRequest();
        List<String> projectParameters = new ArrayList<>();

        // TODO: replace parameters with selected parameters from settings page.
        List<Long> radioMapFiles = new ArrayList<>();
        radioMapFiles.add(1L);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this.context);
        String selectedProject = sharedPreferences.getString(context.getResources().getString(R.string.selected_project_id), "3");

        singlePositionRequest.setWithPixelPosition(false)
                .setWifiReadings(wifiReading)
                .setProjectIdentifier(Long.parseLong(selectedProject))
                .setAlgorithmType("WIFI")
                .setBuildingIdentifier(1L) // TODO: set buildingIdentifier of project.
                .setEvaluationFile(1L)
                .setProjectParameters(projectParameters)
                .setRadioMapFiles(radioMapFiles);
                /*
                */

        Call<de.hft_stuttgart.sw.projectindoorapp.models.external.Position> call;
        call = restClient.generateSinglePositionResult(singlePositionRequest);

        try {
            de.hft_stuttgart.sw.projectindoorapp.models.external.Position remotePosition = call.execute().body();
            Position position = new Position();
            position.setLatitude(remotePosition.getX())
                    .setLongitude(remotePosition.getY());
            return position;
        } catch (IOException exception) {
            // TODO: properly handle exception.
            return new Position();
        } catch (NullPointerException exception) {
            return new Position();
        }
    }

    /**
     * @param wifiReading wifi line
     *                    WIFI data: 'WIFI;AppTimestamp(s);SensorTimeStamp(s);Name_SSID;MAC_BSSID;RSS(dBm);
     * @return returns position received from server or empty position in case of IOException.
     */
    public Position getPositionFromWifiReading(String wifiReading) {
        MockPositionData data = new MockPositionData(
                new Position(48.780551, 9.171766),
                new Position(48.780159, 9.173488));

        return data.getPosition();
    }


}
