package de.hft_stuttgart.sw.projectindoorapp.services;

import de.hft_stuttgart.sw.projectindoorapp.models.external.Position;
import de.hft_stuttgart.sw.projectindoorapp.models.requests.SinglePositionRequest;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by moritzschillinger on 21.11.17.
 */

public interface PositionRestClient {

    @GET("calculatePositionWithWifiReading/")
    public Call<Position> getPositionForWifiReading(@Query("wifiReading") String wifiReading);

    @POST("position/processRadioMapFiles/")
    public Call<Position> processRadioMapFiles(@Query("buildingIdentifier") int buildingIdentifier, @Query("withPixelPosition") boolean withPixelPosition);

    @POST("position/processEvalFiles/")
    public Call<Position> processEvalFiles(@Query("buildingIdentifier")int buildingIdentifier, @Query("withPixelPosition")boolean withPixelPosition);

    @GET("position/getEvalFilesForBuilding/")
    public Call<Position> getEvalFilesForBuilding(@Query("buildingIdentifier")int buildingIdentifier);

    @GET("position/getRadioMapsForBuilding/")
    public Call<Position> getRadioMapsForBuilding(@Query("buildingIdentifier")int buildingIdentifier);

    @POST("position/generateBatchPositionResults/")
    public Call<Position> generateBatchPositionResults(@Query("withPixelPosition")boolean withPixelPosition);
    //somemore undefined attributes to be added above

    @POST("position/generateSinglePositionResult/")
    public Call<Position> generateSinglePositionResult(@Body SinglePositionRequest singlePositionRequest);

}

