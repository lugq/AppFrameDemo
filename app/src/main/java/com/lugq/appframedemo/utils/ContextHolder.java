package com.lugq.appframedemo.utils;

import android.content.Context;

public class ContextHolder {

    private static Context mContext;

    public static void initial(Context context) {
        mContext = context;
    }

    public static Context getContext() {
        return mContext;
    }
}
