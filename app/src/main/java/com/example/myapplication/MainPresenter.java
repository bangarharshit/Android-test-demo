package com.example.myapplication;

import android.os.Build;
import android.text.TextUtils;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.example.myapplication.container.WeatherContainer;
import com.example.myapplication.model.Main;
import com.example.myapplication.model.Weather;
import com.example.myapplication.model.WeatherModel;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class MainPresenter {
    @Nullable private MainView mainView; // Programming against interface
    private final WeatherAPI weatherAPI;
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();
    public MainPresenter(WeatherAPI weatherAPI) {
        this.weatherAPI = weatherAPI;
    }

    public void setView(MainView mainView) {
        this.mainView = mainView;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void fetchWeather(String city) {
        if (TextUtils.isEmpty(city)) { // exchange it with non android implementation
            mainView.setEmptyError();
            return;
        }
        compositeDisposable.add(weatherAPI
                .getWeatherData(city)
                .map(WeatherContainer::from)
                .startWith(WeatherContainer.LOADING)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(weatherContainer -> {
                    if (weatherContainer.isLoading()) {
                        mainView.setLoading(true);
                        return;
                    }
                    mainView.setLoading(false);
                    WeatherModel weatherModel = weatherContainer.getWeatherModel();
                    String weatherDescription = Optional.ofNullable(weatherModel)
                            .filter(weatherModel1 -> weatherModel1.getWeather() != null)
                            .map(WeatherModel::getWeather)
                            .filter(weathers -> weathers.size() > 0)
                            .map(weathers -> weathers.get(0))
                            .filter(weather1 -> !TextUtils.isEmpty(weather1.getDescription()))
                            .map(Weather::getDescription)
                            .orElse(null);
                    String temparature = Optional.ofNullable(weatherModel)
                            .filter(weatherModel12 -> weatherModel12.getMain() != null)
                            .map(WeatherModel::getMain)
                            .map(Main::getTemp)
                            .map(String::valueOf)
                            .orElse(null);
                    if (weatherDescription != null && temparature != null) {
                        mainView.setWeather(temparature, weatherDescription);
                    } else {
                        mainView.setUnknownError();
                    }
                }, throwable -> {
                    mainView.setLoading(false);
                    mainView.setUnknownError();
                }));

    }

    public void destroy() {
        compositeDisposable.dispose();
    }
}
