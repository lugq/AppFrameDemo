package com.lugq.appframedemo.ui.activity

import android.content.Intent
import android.util.Log
import com.lugq.appframedemo.R
import com.lugq.appframedemo.entity.UserEntity
import com.lugq.appframedemo.ui.base.BaseActivity
import com.lugq.appframedemo.ui.presenter.LoginPresenter
import com.lugq.appframedemo.ui.view.LoginView
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity<LoginPresenter?>(), LoginView {

    override fun createPresenter(): LoginPresenter {
        return LoginPresenter(this)
    }

    override fun provideContentViewId(): Int {
        return R.layout.activity_login
    }

    override fun initListener() {
        super.initListener()
        // 登录
        btn_login.setOnClickListener {
            val name = et_name!!.text.toString()
            val pwd = et_name!!.text.toString()
            mPresenter!!.login(name, pwd)
            startActivity(Intent(this, MainActivity::class.java))
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG, "onDestroy()")
    }

    companion object {
        private val TAG = LoginActivity::class.java.simpleName
    }

    override fun showUserInfo(user: UserEntity?) {
    }
}