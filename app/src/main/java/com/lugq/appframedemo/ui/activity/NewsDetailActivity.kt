package com.lugq.appframedemo.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lugq.appframedemo.R
import kotlinx.android.synthetic.main.activity_news_detail.*
import kotlinx.android.synthetic.main.include_title_bar.*
import kotlinx.android.synthetic.main.include_title_bar.mToolbar

class NewsDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_detail)
        initToolbar(getString(R.string.act_login_user))
        tvContent.text = "333"
    }

    private fun initToolbar(title: String) {
        tvTitle.text = title
        // 这是必须的
        mToolbar.title = ""
        setSupportActionBar(mToolbar)
        // 显示系统默认的返回按钮
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        mToolbar.setNavigationOnClickListener { finish() }
    }
}