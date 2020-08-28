package com.example.restweather;

import com.example.restweather.models.Forecast;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherService {

    @GET("weather")
    Observable<Forecast> getCurrentWeather(@Query("q") String city, @Query("appid") String appid, @Query("units") String units);

}
