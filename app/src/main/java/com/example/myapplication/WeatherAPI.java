package com.example.myapplication;

import com.example.myapplication.model.WeatherModel;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherAPI {
    @GET("data/2.5/weather?appid=c8f166bceed3280c6b445376cf32bd30&units=metric")
    Observable<WeatherModel> getWeatherData(@Query("q") String city);
}
