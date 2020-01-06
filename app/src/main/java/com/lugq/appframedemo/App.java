package com.lugq.appframedemo;

import android.app.Application;

import com.lugq.appframedemo.utils.ContextHolder;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ContextHolder.initial(this);
    }

}
