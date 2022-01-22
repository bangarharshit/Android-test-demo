package com.example.myapplication;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.myapplication.model.Main;
import com.example.myapplication.model.Weather;
import com.example.myapplication.model.WeatherModel;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.subjects.PublishSubject;

@RunWith(RobolectricTestRunner.class)
public class MainPresenterTestWithMocks {
    @Rule
    public final ImmediateSchedulersRule immediateSchedulersRule = new ImmediateSchedulersRule();
    @Mock private MainView mainView;
    @Mock private WeatherAPI weatherAPI;
    private MainPresenter mainPresenter;
    private final PublishSubject<WeatherModel> publishSubject = PublishSubject.create();

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        Mockito.when(mainView.getContext()).thenReturn(RuntimeEnvironment.getApplication());
        mainPresenter = new MainPresenter(weatherAPI);
        when(weatherAPI.getWeatherData("London")).thenReturn(publishSubject);
        mainPresenter.setView(mainView);
    }

    @Test
    public void fetchExchangeCurrent_2USD_setInrInView() {
        mainPresenter.fetchWeather("London");
        List<Weather> weathers = new ArrayList<>();
        weathers.add(new Weather("Sunny"));
        publishSubject.onNext(new WeatherModel("London", weathers, new Main(10.0)));
        verify(mainView).setLoading(true);
        verify(mainView).setWeather(any(String.class), anyString());
        verify(mainView).setLoading(false);
    }
}
