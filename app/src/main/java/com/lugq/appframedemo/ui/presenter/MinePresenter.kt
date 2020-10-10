package com.lugq.appframedemo.ui.presenter

import com.lugq.appframedemo.entity.ResultResponse
import com.lugq.appframedemo.entity.WarnintEntity
import com.lugq.appframedemo.net.ApiService
import com.lugq.appframedemo.net.BaseObserver
import com.lugq.appframedemo.net.CommonSchedulers
import com.lugq.appframedemo.ui.base.BasePresenter
import com.lugq.appframedemo.ui.view.MineView

class MinePresenter(view: MineView) : BasePresenter<MineView>(view) {

    fun getUserProfile(userId: String) {

    }

    fun login() {
        val observable = ApiService.createApiService().getWarningListByParams("hahaha")
        observable?.compose(CommonSchedulers.io2main())
                ?.subscribe(object : BaseObserver<ResultResponse<List<WarnintEntity?>?>?>() {
                    /*
                    override fun onNext(t: ResultResponse<List<WarnintEntity>>) {
                        val datas = t.data
                        /*for (item in datas) {
                            LogUtils.i("请求成功${item.content}")
                        }*/
                    }*/

                    override fun onNext(t: ResultResponse<List<WarnintEntity?>?>) {

                    }
                })
    }
}