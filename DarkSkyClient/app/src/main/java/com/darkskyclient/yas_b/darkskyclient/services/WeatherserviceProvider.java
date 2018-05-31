package com.darkskyclient.yas_b.darkskyclient.services;

import android.util.Log;

import com.darkskyclient.yas_b.darkskyclient.events.WeatherEvent;

import org.greenrobot.eventbus.EventBus;

import models.Currently;
import models.Weather;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by YAS-B on 03/30/2018.
 */

public class WeatherserviceProvider {

    private static final String BASE_URL = "https://api.darksky.net/forecast/0978b60cd06d3b76711a0f272b347409/" ;
    /*at first we sent lang and latitude in BASE_URL for getting temperature but in next test
     * we wanna send lang and latitude in main ACTIVITY  so we send BASE_URL without lang and lat
      * "https://api.darksky.net/forecast/0978b60cd06d3b76711a0f272b347409/38.8267,-122.4233" ;*/
    private static final String TAG = WeatherserviceProvider.class.getSimpleName() ;
    private Retrofit retrofit;


    private Retrofit getRetrofit() {
        if (this.retrofit == null) {
            this.retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        }

        return this.retrofit;
    }

    /*and if we send lang and lat in BASE_URL we must use getweather without input method
    * */

    /* we had a getweather(double lat, double lon , Callback callback)*/
    public void getweather(double lat, double lon ){

        Weatherservice weatherservice = getRetrofit().create(Weatherservice.class);
        Call<Weather> weatherData = weatherservice.getWeather(lat , lon);
        weatherData.enqueue(new Callback<Weather>() {
            @Override
            public void onResponse(Call<Weather> call, Response<Weather> response) {

                Weather weather = response.body();
                Currently currently = weather.getCurrently();
                Log.e(TAG, "Temprature =" + currently.getTemperature());
                EventBus.getDefault().post(new WeatherEvent(weather));

            }

            @Override
            public void onFailure(Call<Weather> call, Throwable t) {

                Log.e(TAG, "Failure to get weather data !");

            }
        }
);
    }

}

