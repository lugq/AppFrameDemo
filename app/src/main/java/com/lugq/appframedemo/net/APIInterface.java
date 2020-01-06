package com.lugq.appframedemo.net;

import com.lugq.appframedemo.entity.UserEntity;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * @Description 定义接口
 * @Author Lu Guoqiang
 * @Time 2019/6/24 15:07
 * <p>
 * 此类函数规范：
 * 以增删查改为前缀（add、delete、query、update、uploadImg），
 * 请求方式为后缀（可选：get、postForm、postJson），
 * 例如：queryUsers（查询用户），queryUsersPost（查询用户）
 */
public interface APIInterface {

    @GET("/users/lugq")
    Observable<UserEntity> getInfo();
}
