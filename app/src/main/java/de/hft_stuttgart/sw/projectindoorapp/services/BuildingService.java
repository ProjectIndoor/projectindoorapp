package de.hft_stuttgart.sw.projectindoorapp.services;

import java.io.IOException;
import java.util.ArrayList;

import de.hft_stuttgart.sw.projectindoorapp.models.external.Building;
import de.hft_stuttgart.sw.projectindoorapp.network.Networking;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by moritzschillinger on 16.12.17.
 */

public class BuildingService {

    private BuildingRestClient restClient;

    public BuildingService() {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectionPool(Networking.getPool())
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://doblix.de/indoorweb/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        restClient = retrofit.create(BuildingRestClient.class);
    }

    public ArrayList<Building> getAllBuildings() {
        try {
            return this.restClient.getAllBuildings().execute().body();
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

}
