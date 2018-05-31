package com.darkskyclient.yas_b.darkskyclient.events;

import models.Weather;

/**
 * Created by YAS-B on 04/14/2018.
 */

public class WeatherEvent {

    private final Weather weather ;

    public WeatherEvent(Weather weather ){

        this.weather = weather ;
    }

    public Weather getWeather() {
        return weather;
    }
}
