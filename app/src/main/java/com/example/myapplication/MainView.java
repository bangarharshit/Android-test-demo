package com.example.myapplication;

import android.content.Context;

interface MainView {
    void setWeather(String temperature, String condition);
    void setEmptyError();
    Context getContext();
    void setUnknownError();
    void setLoading(boolean loading);
}
