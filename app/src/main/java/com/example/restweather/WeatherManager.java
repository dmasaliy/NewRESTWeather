package com.example.restweather;

import com.example.restweather.models.Weather;
import com.example.restweather.models.WeatherRequest;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeatherManager {
    private static WeatherManager instance;
    public static final String URL = "https://api.openweathermap.org/data/2.5/";
    private WeatherService weatherService;
    private Disposable disposable;

    static WeatherManager getInstance(){
        if(instance == null)
            instance = new WeatherManager();
        return  instance;
    }

    private WeatherManager(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        weatherService = retrofit.create(WeatherService.class);
    }

    void getCurrentWeather(WeatherRequest weatherRequest, WeatherCallback callback){
        disposable = weatherService.getCurrentWeather(weatherRequest.getCity(), weatherRequest.getAppid(), weatherRequest.getUnits())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response->{
                    callback.onWeatherResponse(response);
                }, error ->{
                    callback.onWeatherResponseError(error.getMessage());

                });

    }

    void dispose(){
        disposable.dispose();
    }
}
