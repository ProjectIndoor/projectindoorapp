package de.hft_stuttgart.sw.projectindoorapp.services;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by moritzschillinger on 21.11.17.
 */

public interface PositioningRestClient {

    @GET("calculatePositionWithWifiReading/")
    Call<Object> getPositionForWifiReading(@Query("wifiReading") String wifiReading);

}
