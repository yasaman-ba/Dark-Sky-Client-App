package com.darkskyclient.yas_b.darkskyclient;

import android.support.multidex.MultiDex;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import org.greenrobot.eventbus.* ;

import com.darkskyclient.yas_b.darkskyclient.events.WeatherEvent;
import com.darkskyclient.yas_b.darkskyclient.services.Weatherservice;
import com.darkskyclient.yas_b.darkskyclient.services.WeatherserviceProvider;

import butterknife.BindView;
import butterknife.ButterKnife;

import models.Currently;
import models.Weather;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();


   @BindView(R.id.tempTextView)
    TextView tempTextView ;

    /* we can use eventbus library to show data weather in tempTextview , but at first
    * we wanna try this without eventbus library , so in WeatherserviceProvider Activity
    *  we changed getweather(double lat, double lon) << this function input and Added
    *   one more input that the name is callback getweather(double lat, double lon , Callback callback)
    *   and changed getweather input here and added callback here too and Added
    *
    *   Callback callback = new Callback<Weather>() {......} here , now we can access tempTextview*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       /* MultiDex.install(this);*/

        setContentView(R.layout.activity_main);

        requestCurrentWeather(38.8267,-122.4233);

         ButterKnife.bind(this);


       // tempTextView = (TextView) findViewById(R.id.tempTextView);
        // with butter knife we don't need this defination

        /*
        * here we have another summarize we wanna put these code into another method
        *
        *  WeatherserviceProvider weatherserviceProvider = new WeatherserviceProvider();
        weatherserviceProvider.getweather(38.8267,-122.4233);

        */

   }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onWeatherEvent(WeatherEvent weatherEvent) {

    Currently currently = weatherEvent.getWeather().getCurrently();
    tempTextView.setText(String.valueOf(currently.getTemperature()));

    }


    private void requestCurrentWeather(double lat, double lon) {

        WeatherserviceProvider weatherserviceProvider = new WeatherserviceProvider();

        /* in that time we can move everything into weatherserviceprovider Activity
        (in fact we will use eventbus) and
        * in main Activity just call them so for this goal we most undo every callback that
        *  defined here ... */
        weatherserviceProvider.getweather(lat,lon);
       }






    }

