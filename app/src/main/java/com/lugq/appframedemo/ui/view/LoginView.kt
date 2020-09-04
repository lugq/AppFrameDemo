package com.lugq.appframedemo.ui.view

import com.lugq.appframedemo.entity.UserEntity
import com.lugq.appframedemo.entity.WarnintEntity

interface LoginView {
    fun showUserInfo(user: UserEntity?)

    fun showData(datas: List<WarnintEntity>)
}