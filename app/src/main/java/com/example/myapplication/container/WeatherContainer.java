package com.example.myapplication.container;

import androidx.annotation.Nullable;

import com.example.myapplication.model.WeatherModel;

public class WeatherContainer {
    @Nullable private final WeatherModel weatherModel;
    private final boolean loading;

    private WeatherContainer(@Nullable WeatherModel weatherModel, boolean loading) {
        this.weatherModel = weatherModel;
        this.loading = loading;
    }

    public static WeatherContainer from(WeatherModel weatherModel) {
        return new WeatherContainer(weatherModel, false);
    }

    @Nullable
    public WeatherModel getWeatherModel() {
        return weatherModel;
    }

    public boolean isLoading() {
        return loading;
    }

    public static WeatherContainer LOADING = new WeatherContainer(null, true);
}
