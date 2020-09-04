package com.lugq.appframedemo.ui.presenter

import android.util.Log
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import com.lugq.appframedemo.entity.ResultResponse
import com.lugq.appframedemo.entity.User
import com.lugq.appframedemo.entity.UserEntity
import com.lugq.appframedemo.entity.WarnintEntity
import com.lugq.appframedemo.net.*
import com.lugq.appframedemo.ui.base.BasePresenter
import com.lugq.appframedemo.ui.presenter.LoginPresenter
import com.lugq.appframedemo.ui.view.LoginView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginPresenter(view: LoginView?) : BasePresenter<LoginView?>(view) {
    companion object {
        private val TAG = LoginPresenter::class.java.simpleName
    }

    val api = ApiService.createApiService()

    fun login(name: String?, pwd: String?) {
        val observable = ApiService.createApiService().info
        observable?.compose(CommonSchedulers.io2main())
                ?.subscribe(object : BaseObserver<UserEntity?>() {
                    override fun onNext(s: UserEntity) {
                        Log.i(TAG, "请求成功")
                        mView?.showUserInfo(s)
                    }
                })
    }

    /**
     * 这个写法很精简
     */
    fun getUsers() {
        val call = api.getGitHubInfo()
        call.enqueue(object : Callback<ResultResponse<JsonObject>> {
            override fun onFailure(call: Call<ResultResponse<JsonObject>>, t: Throwable) {
            }

            override fun onResponse(call: Call<ResultResponse<JsonObject>>, response: Response<ResultResponse<JsonObject>>) {
                testInfo(response)
                response.body()?.data?.let {
                    response.body()?.data?.get(STUDENTS)?.toString()?.let {
                        val news = Gson().fromJson<List<User>>(it, object : TypeToken<List<User>>() {}.type)
                        mView?.showData(news)
                    } ?: mView?.showData(listOf())
                }
            }
        })
    }

    private fun testInfo(response: Response<ResultResponse<JsonObject>>) {
        val jsonObject = response.body()?.data
        val str = jsonObject?.get(STUDENTS).toString()
        Log.i(TAG, str)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG, "LoginActivity destroy")
    }

}