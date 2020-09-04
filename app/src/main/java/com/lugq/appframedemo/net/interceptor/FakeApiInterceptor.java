package com.lugq.appframedemo.net.interceptor;

import com.lugq.appframedemo.BuildConfig;
import com.lugq.appframedemo.utils.ReadAssetsUtils;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * @Description
 * @Author Lu Guoqiang
 * @Time 2019/7/17 14:45
 */
public class FakeApiInterceptor implements Interceptor {

    private static final String BASE_URL = "https://www.easy-mock.com";

    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {
        Response response;
        //if (BuildConfig.DEBUG && chain.request().url().toString().contains(BASE_URL)) {
        //    response = mockModifyName(chain, "mock/mock2.json");
        //}

        // 对特殊的地址进行假数据回传，用于接
        if (BuildConfig.DEBUG && chain.request().url().toString().contains("/pw/warning/getWarningListByParams")) {
            response = mockModifyName(chain, "mock/mock1.json");
        } else if (BuildConfig.DEBUG && chain.request().url().toString().contains("/users/lugq")) {
            response = mockModifyName(chain, "mock/mock3.json");
        } else {
            response = chain.proceed(chain.request());
        }

        return response;
    }

    /**
     * 模拟 xxx 情况
     * 模拟修改名字数据
     */
    private Response mockModifyName(Chain chain, String fileName) {
        String json = ReadAssetsUtils.getJson(fileName);
        return new Response.Builder()
                .code(200)
                .addHeader("Content-Type", "application/json")
                .body(ResponseBody.create(MediaType.parse("application/json"), json))
                .message(json)
                .request(chain.request())
                .protocol(Protocol.HTTP_2)
                .build();
    }


}
