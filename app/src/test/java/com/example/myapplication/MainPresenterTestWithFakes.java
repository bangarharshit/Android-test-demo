package com.example.myapplication;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.example.myapplication.model.Main;
import com.example.myapplication.model.Weather;
import com.example.myapplication.model.WeatherModel;
import com.google.errorprone.annotations.DoNotMock;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(RobolectricTestRunner.class)
public class MainPresenterTestWithFakes {
    @Rule public final ImmediateSchedulersRule immediateSchedulersRule = new ImmediateSchedulersRule();
    private final FakeMainView mainView = new FakeMainView();
    private final FakeWeatherAPI fakeWeatherAPI = new FakeWeatherAPI();
    private MainPresenter mainPresenter;
    @Before
    public void setup() {
        mainPresenter = new MainPresenter(fakeWeatherAPI);
        mainPresenter.setView(mainView);
    }

    @Test
    public void fetchExchangeCurrent_2USD_setInrInView() {
        mainPresenter.fetchWeather("London");
        assertTrue(mainView.isLoading());
        List<Weather> weathers = new ArrayList<>();
        weathers.add(new Weather("Sunny"));
        fakeWeatherAPI.emitWeatherModel(new WeatherModel("London", weathers, new Main(10.0)));
        assertEquals("Sunny", mainView.getCondition());
    }
}
