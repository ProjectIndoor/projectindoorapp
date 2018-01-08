package de.hft_stuttgart.sw.projectindoorapp.services;

import java.io.IOException;
import java.util.ArrayList;

import de.hft_stuttgart.sw.projectindoorapp.models.external.Project;
import de.hft_stuttgart.sw.projectindoorapp.network.Networking;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by moritzschillinger on 17.12.17.
 */

public class ProjectService {

    private ProjectRestClient restClient;

    public ProjectService() {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectionPool(Networking.getPool())
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://doblix.de/indoorweb/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        restClient = retrofit.create(ProjectRestClient.class);
    }

    public ArrayList<Project> getAllProjects() {
        try {
            return this.restClient.getAllProjects().execute().body();
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

}
