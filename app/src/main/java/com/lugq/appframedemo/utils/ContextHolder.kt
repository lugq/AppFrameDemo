package com.lugq.appframedemo.utils

import android.content.Context

class ContextHolder {

    companion object {
        public lateinit var context: Context

        fun initial(context: Context) {
            this.context = context
        }
    }

}