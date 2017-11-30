package de.hft_stuttgart.sw.projectindoorapp.services;

import de.hft_stuttgart.sw.projectindoorapp.models.Position;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Sony on 11/30/2017.
 */

public interface ProjectRestClient {
    @POST("project/saveNewProject/")
    public Call<Position> saveNewProject(@Query("projectName") String projectName, @Query("algorithmType") String algorithmType);

    @POST("project/saveCurrentProject/")
    public Call<Position> saveCurrentProject(@Query("projectIdentifier") int projectIdentifier, @Query("projectName") String projectName, @Query("algorithmType") String algorithmType);

    @DELETE("project/deleteSelectedProject/")
    public Call<Position> deleteSelectedProject(@Query("projectIdentifier") int projectIdentifier);

    @GET("project/loadSelectedProject/")
    public Call<Position> loadSelectedProject(@Query("projectIdentifier") int projectIdentifier);

    @GET("project/getAllProjects/")
    public Call<Position> getAllProjects();

    @GET("project/getAllAlgorithmTypes/")
    public Call<Position> getAllAlgorithmTypes();
}