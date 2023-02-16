package com.example.conduitrealworld;

import android.app.Application;

public class MainApplication extends Application {
    private  static  MainApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static  MainApplication getInstance(){
        return  instance;
    }
}
