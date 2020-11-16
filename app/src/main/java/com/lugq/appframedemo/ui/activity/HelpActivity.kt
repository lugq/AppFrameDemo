package com.lugq.appframedemo.ui.activity

import com.lugq.appframedemo.R
import com.lugq.appframedemo.ui.base.BaseActivity
import com.lugq.appframedemo.ui.base.BasePresenter
import com.lugq.appframedemo.ui.base.BaseView

/**
 * 场景：比如一些简单的页面，不想写 Presenter 和 View 的情况可以这样
 */
class HelpActivity : BaseActivity<BasePresenter<BaseView>>() {

    override fun provideContentViewId() = R.layout.activity_help

    override fun createPresenter(): Nothing? = null
}