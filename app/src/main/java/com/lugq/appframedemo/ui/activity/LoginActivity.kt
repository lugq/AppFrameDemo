package com.lugq.appframedemo.ui.activity

import android.content.Intent
import android.util.Log
import com.lugq.appframedemo.R
import com.lugq.appframedemo.entity.User
import com.lugq.appframedemo.entity.UserEntity
import com.lugq.appframedemo.ui.base.BaseActivity
import com.lugq.appframedemo.ui.presenter.LoginPresenter
import com.lugq.appframedemo.ui.view.LoginView
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity<LoginPresenter?>(), LoginView {

    companion object {
        private val TAG = LoginActivity::class.java.simpleName
    }

    override fun createPresenter() = LoginPresenter(this)

    override fun provideContentViewId() = R.layout.activity_login

    override fun initListener() {
        super.initListener()
        // 登录
        btn_login.setOnClickListener {
            val name = et_name?.text.toString()
            val pwd = et_name?.text.toString()
            mPresenter?.login(name, pwd)
            startActivity(Intent(this, MainActivity::class.java))
        }

        // 更精简的网络请求示例
        btnTest.setOnClickListener {
            mPresenter?.getUsers2()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG, "onDestroy()")
    }

    override fun showUserInfo(user: UserEntity?) {

    }

    override fun showData(datas: List<User>) {
        Log.i(TAG, "showData${datas.size}")
    }

}