package com.lugq.appframedemo.ui.view

import com.lugq.appframedemo.entity.UserEntity

interface LoginView {
    fun showUserInfo(user: UserEntity?)
}