package com.example.myapplication;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements MainView {
    @Nullable private MainPresenter mainPresenter;
    private EditText input;
    private TextView output;
    private ProgressBar progressBar;
    // TODO - use dagger or hilt for DI
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build();
    private WeatherAPI weatherAPI = retrofit.create(WeatherAPI.class);
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        input = findViewById(R.id.input);
        output = findViewById(R.id.text_view);
        mainPresenter = new MainPresenter(weatherAPI);
        progressBar = findViewById(R.id.indeterminateBar);
        mainPresenter.setView(this);
        findViewById(R.id.button).setOnClickListener(view -> mainPresenter.fetchWeather(input.getText().toString()));
        input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                input.setError(null);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mainPresenter.setView(null);
    }


    @Override
    public void setWeather(String temperature, String condition) {
        output.setText(getString(R.string.temparature_condition, temperature, condition));
    }

    @Override
    public void setEmptyError() {
        input.setError(getContext().getResources().getString(R.string.empty_city_error));
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void setUnknownError() {
        input.setError(getResources().getString(R.string.unknown_error));
    }

    @Override
    public void setLoading(boolean loading) {
        progressBar.setVisibility(loading ? View.VISIBLE: View.GONE);
    }
}
