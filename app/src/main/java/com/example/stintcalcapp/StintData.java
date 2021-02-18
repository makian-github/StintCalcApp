package com.example.stintcalcapp;

import android.app.Application;

public class StintData extends Application {

    private String driverName = "default";

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }
}
