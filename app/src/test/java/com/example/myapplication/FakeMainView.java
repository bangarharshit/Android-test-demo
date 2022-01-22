package com.example.myapplication;

import android.content.Context;

import org.robolectric.RuntimeEnvironment;

public class FakeMainView  implements MainView{
    private String temparature;
    private String condition;
    private boolean emptyErrorSet;
    private boolean unknowErrorSet;
    private boolean loading;

    @Override
    public void setWeather(String temperature, String condition) {
        this.temparature = temperature;
        this.condition = condition;
    }

    @Override
    public void setEmptyError() {
        this.emptyErrorSet = true;
    }

    @Override
    public Context getContext() {
        return RuntimeEnvironment.getApplication();
    }

    @Override
    public void setUnknownError() {
        this.unknowErrorSet = true;
    }

    @Override
    public void setLoading(boolean loading) {
        this.loading = loading;
    }

    public void reset() {
        this.loading = false;
        this.unknowErrorSet = false;
        this.emptyErrorSet = false;
        this.condition = null;
        this.temparature = null;
    }

    public String getTemparature() {
        return temparature;
    }

    public String getCondition() {
        return condition;
    }

    public boolean isEmptyErrorSet() {
        return emptyErrorSet;
    }

    public boolean isUnknowErrorSet() {
        return unknowErrorSet;
    }

    public boolean isLoading() {
        return loading;
    }
}
