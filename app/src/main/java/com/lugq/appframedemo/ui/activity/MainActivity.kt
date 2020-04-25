package com.lugq.appframedemo.ui.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.blankj.utilcode.util.LogUtils
import com.lugq.appframedemo.R
import com.lugq.appframedemo.entity.ResultResponse
import com.lugq.appframedemo.entity.UserEntity
import com.lugq.appframedemo.entity.WarnintEntity
import com.lugq.appframedemo.net.ApiService
import com.lugq.appframedemo.net.BaseObserver
import com.lugq.appframedemo.net.CommonSchedulers
import com.lugq.appframedemo.ui.presenter.LoginPresenter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnFake.setOnClickListener {
            login()
        }
    }

    fun login() {
        val observable = ApiService.createApiService().getWarningListByParams("hahaha")
        observable.compose(CommonSchedulers.io2main())
                .subscribe(object : BaseObserver<ResultResponse<List<WarnintEntity>>>() {
                    override fun onNext(t: ResultResponse<List<WarnintEntity>>) {
                        val datas = t.data
                        for (item in datas) {
                            LogUtils.i("请求成功${item.content}")
                        }
                    }

                })
    }

}
