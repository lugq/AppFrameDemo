package com.lugq.appframedemo.ui.presenter

import android.util.Log
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import com.lugq.appframedemo.entity.ResultResponse
import com.lugq.appframedemo.entity.UserEntity
import com.lugq.appframedemo.entity.WarnintEntity
import com.lugq.appframedemo.net.ApiService
import com.lugq.appframedemo.net.BaseObserver
import com.lugq.appframedemo.net.CommonSchedulers
import com.lugq.appframedemo.ui.base.BasePresenter
import com.lugq.appframedemo.ui.presenter.LoginPresenter
import com.lugq.appframedemo.ui.view.LoginView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginPresenter(view: LoginView?) : BasePresenter<LoginView?>(view) {
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
    fun test2(name: String?, pwd: String?) {
        val call = api.getGitHubInfo()
        call.enqueue(object : Callback<ResultResponse<JsonObject>> {
            override fun onFailure(call: Call<ResultResponse<JsonObject>>, t: Throwable) {
            }

            override fun onResponse(call: Call<ResultResponse<JsonObject>>, response: Response<ResultResponse<JsonObject>>) {
                response.body()?.data?.let {
                    val banners = Gson().fromJson<List<WarnintEntity>>(it.toString(), object : TypeToken<List<WarnintEntity>>() {}.type)
                    mView?.showData(banners)
                }
            }

        })
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG, "LoginActivity destroy")
    }

    companion object {
        private val TAG = LoginPresenter::class.java.simpleName
    }
}