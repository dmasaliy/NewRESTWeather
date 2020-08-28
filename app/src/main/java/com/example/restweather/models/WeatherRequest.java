package com.example.restweather.models;

public class WeatherRequest {

    private String city;
    private  String appid;
    private String units;



    public WeatherRequest(String city, String appid, String units) {
        this.city = city;
        this.appid = appid;
        this.units = units;
    }
    public WeatherRequest(String city, String appid) {
        this.city = city;
        this.appid = appid;
        this.units = "metric";
    }



    public String getUnits() {
        return units;
    }

    public String getCity() {
        return city;
    }

    public String getAppid() {
        return appid;
    }

}
