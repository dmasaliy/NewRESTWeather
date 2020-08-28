package com.example.restweather;

import com.example.restweather.models.Forecast;

public interface WeatherCallback {

    void onWeatherResponse(Forecast forecast);
    void onWeatherResponseError(String message);

}
