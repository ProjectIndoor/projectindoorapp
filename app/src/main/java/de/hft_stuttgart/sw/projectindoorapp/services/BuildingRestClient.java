package de.hft_stuttgart.sw.projectindoorapp.services;

import de.hft_stuttgart.sw.projectindoorapp.models.Position;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Sony on 11/30/2017.
 */

public interface BuildingRestClient {
    @POST("building/addNewBuilding/")
    public Call<Position>addNewBuilding (@Query("buildingName")String buildingName,@Query("numberOfFloors")int numberOfFloors);

    @GET("building/getAllBuildings/")
    public Call<Position> getAllBuildings ();

}
