package com.lugq.appframedemo.net

import com.google.gson.JsonObject
import com.lugq.appframedemo.entity.ResultResponse
import com.lugq.appframedemo.entity.UserEntity
import com.lugq.appframedemo.entity.WarnintEntity
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

/**
 * @Description 定义接口
 * @Author Lu Guoqiang
 * @Time 2019/6/24 15:07
 *
 *
 * 此类函数规范：
 * 以增删查改为前缀（add、delete、query、update、uploadImg），
 * 请求方式为后缀（可选：get、postForm、postJson），
 * 例如：queryUsers（查询用户），queryUsersPost（查询用户）
 */
const val data = "data"

interface APIInterface {
    @get:GET("/users/lugq")
    val info: Observable<UserEntity?>?

    // post 表单方式
    @POST("/pw/warning/getWarningListByParams")
    @FormUrlEncoded
    fun getWarningListByParams(@Field("username") userName: String?): Observable<ResultResponse<List<WarnintEntity?>?>?>?

    @POST("/pw/warning/getWarningDetailPageInfo")
    @FormUrlEncoded
    fun getWarningDetailPageInfo(@Field("username") userName: String?): Observable<ResultResponse<List<WarnintEntity?>?>?>?

    @GET("/users/lugq")
    fun getGitHubInfo(): Call<ResultResponse<JsonObject>>
}