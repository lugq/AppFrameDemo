package com.lugq.appframedemo;

import android.app.Application;

import com.blankj.utilcode.util.Utils;
import com.lugq.appframedemo.utils.ContextHolder;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ContextHolder.initial(this);
        Utils.init(this);
    }

}
