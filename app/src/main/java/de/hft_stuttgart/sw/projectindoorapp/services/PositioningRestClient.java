package de.hft_stuttgart.sw.projectindoorapp.services;

import de.hft_stuttgart.sw.projectindoorapp.models.Position;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by moritzschillinger on 21.11.17.
 */

public interface PositioningRestClient {

    @GET("calculatePositionWithWifiReading/")
    Call<Position> getPositionForWifiReading(@Query("wifiReading") String wifiReading);

}
