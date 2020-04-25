package com.lugq.appframedemo.ui.presenter

import android.util.Log
import com.lugq.appframedemo.entity.UserEntity
import com.lugq.appframedemo.net.ApiService
import com.lugq.appframedemo.net.BaseObserver
import com.lugq.appframedemo.net.CommonSchedulers
import com.lugq.appframedemo.ui.base.BasePresenter
import com.lugq.appframedemo.ui.presenter.LoginPresenter
import com.lugq.appframedemo.ui.view.LoginView

class LoginPresenter(view: LoginView?) : BasePresenter<LoginView?>(view) {

    fun login(name: String?, pwd: String?) {
        val observable = ApiService.createApiService().info
        observable.compose(CommonSchedulers.io2main())
                .subscribe(object : BaseObserver<UserEntity?>() {
                    override fun onNext(s: UserEntity) {
                        Log.i(TAG, "请求成功")
                        mView!!.showUserInfo(s)
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