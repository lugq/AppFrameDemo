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
     *
     * 有缺陷：当data对应的是null时候会抛出异常
     */
    fun getUsers() {
        val call = api.getGitHubInfo()
        call.enqueue(object : Callback<ResultResponse<JsonObject>> {
            override fun onFailure(call: Call<ResultResponse<JsonObject>>, t: Throwable) {
                Log.i(TAG, "解析错误")
            }

            override fun onResponse(call: Call<ResultResponse<JsonObject>>, response: Response<ResultResponse<JsonObject>>) {
                /*
                response.body()?.data?.let { it0 ->
                    it0.get(STUDENTS)?.toString()?.let { it1 ->
                        val news = Gson().fromJson<List<User>>(it1, object : TypeToken<List<User>>() {}.type)
                        mView?.showData(news)
                    } ?: mView?.showData(listOf())
                }*/

                //  对应解析 mock4.json
                /*
                response.body()?.data?.let { it0 ->
                    val code = it0["code"]
                    val msg = it0["msg"]

                    Log.i(TAG, "code:${code},msg:${msg}")
                }*/

                // 对应解析 mock5.json
                /**
                 * Expected a com.google.gson.JsonObject but was com.google.gson.JsonNull
                 * 客户端在定义数据接口的时候避免直接使用 JsonObject 类型，一定要自己定义类型，问题即可解决
                 */
                response.body()?.code?.let {
                    Log.i(TAG, "code:${it}")
                }
            }
        })
    }

    fun getUsers2() {
        val call = api.getGitHubInfo2()
        call.enqueue(object : Callback<ResultResponse<Any>> {
            override fun onFailure(call: Call<ResultResponse<Any>>, t: Throwable) {
                Log.i(TAG, "解析错误")
            }

            override fun onResponse(call: Call<ResultResponse<Any>>, response: Response<ResultResponse<Any>>) {
                //  对应解析 mock4.json
                response.body()?.let {
                    Log.i(TAG, "code:${it.code} msg:${it.msg}")
                }
            }
        })
    }

    /*
    private fun testInfo(response: Response<ResultResponse<JsonObject>>) {
        val jsonObject = response.body()?.data
        val str = jsonObject?.get(STUDENTS).toString()
        Log.i(TAG, str)
    }
     */

    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG, "LoginActivity destroy")
    }

}