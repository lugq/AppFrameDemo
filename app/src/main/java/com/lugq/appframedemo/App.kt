package com.lugq.appframedemo

import android.app.Application
import com.blankj.utilcode.util.Utils
import com.lugq.appframedemo.utils.ContextHolder

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        ContextHolder.initial(this)
        Utils.init(this)
    }

}