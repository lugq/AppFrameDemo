package com.lugq.appframedemo.constant;

import android.os.Environment;

import com.lugq.appframedemo.BuildConfig;

import java.io.File;

public class Constants {

    public static String BASE_URL = BuildConfig.DOMAIN;

    //路径分隔符
    private static final String SEPARATOR = File.separator;
    public static final String BASE_PATH = Environment.getExternalStorageDirectory().getAbsolutePath() + SEPARATOR;

}
