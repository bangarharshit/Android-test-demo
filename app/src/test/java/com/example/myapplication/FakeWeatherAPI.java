package com.example.myapplication;

import com.example.myapplication.model.WeatherModel;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

public class FakeWeatherAPI implements WeatherAPI{
    private final PublishSubject<WeatherModel> weatherModelPublishSubject = PublishSubject.create();

    @Override
    public Observable<WeatherModel> getWeatherData(String city) {
        return weatherModelPublishSubject.hide();
    }

    public void emitWeatherModel(WeatherModel weatherModel) {
        weatherModelPublishSubject.onNext(weatherModel);
    }

    public void emitError(Throwable throwable) {
        weatherModelPublishSubject.onError(throwable);
    }
}
