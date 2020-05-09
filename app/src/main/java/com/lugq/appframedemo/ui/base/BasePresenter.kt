package com.lugq.appframedemo.ui.base

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

/**
 * @Description
 * @Author Lu Guoqiang
 * @Time 2019/6/25 20:41
 */
abstract class BasePresenter<V>(view: V) : LifecycleObserver {
    protected var mView: V? = null
    fun attachView(view: V) {
        mView = view
    }

    fun detachView() {
        mView = null
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    open fun onDestroy() {
    }

    init {
        attachView(view)
    }
}