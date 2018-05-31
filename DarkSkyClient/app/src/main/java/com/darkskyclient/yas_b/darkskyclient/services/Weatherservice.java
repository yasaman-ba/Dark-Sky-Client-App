package com.darkskyclient.yas_b.darkskyclient.services;

import models.Weather;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by YAS-B on 03/30/2018.
 */

public interface Weatherservice {

    @GET("{lat},{lon}")
    Call<Weather> getWeather(@Path("lat") double lat, @Path("lon") double lon);

    /*
    @GET("{lat} , {lon}")
    Call<Weather> getWeather
            (@Path("lat") double lat, @Path("lon") double lon);*/
}
