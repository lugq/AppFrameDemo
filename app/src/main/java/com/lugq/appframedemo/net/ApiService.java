package com.lugq.appframedemo.net;

import com.lugq.appframedemo.constant.Constants;
import com.lugq.appframedemo.net.interceptor.FakeApiInterceptor;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @Description
 * @Author Lu Guoqiang
 * @Time 2019/7/2 11:56
 */
public class ApiService {

    /**
     * 默认的域名部分
     */
    public static APIInterface createApiService() {
        return retrofit.create(APIInterface.class);
    }

    /**
     * 日志拦截器 ，此处特别特别特别注意，
     * <p>
     * 先添加公共参数拦截器，后添加日志拦截器
     * 先添加公共参数拦截器，后添加日志拦截器
     * 先添加公共参数拦截器，后添加日志拦截器
     */
    private static OkHttpClient getOkHttpClient() {
        return new OkHttpClient.Builder()
                //.addInterceptor(new CommonParamsInterceptor())
                .addInterceptor(getHttpLoggingInterceptor())
                .addInterceptor(new FakeApiInterceptor())
                .build();
    }

    private static HttpLoggingInterceptor getHttpLoggingInterceptor() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        // 2.设置日志级别
        logging.level(HttpLoggingInterceptor.Level.BODY);
        return logging;
    }

    /**
     * 如果有不同域名的时候
     */
    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(getOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())// 支持RxJava
            .build();
}
