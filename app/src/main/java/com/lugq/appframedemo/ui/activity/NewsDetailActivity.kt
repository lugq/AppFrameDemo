package com.lugq.appframedemo.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lugq.appframedemo.R
import kotlinx.android.synthetic.main.include_title_bar.*

class NewsDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_detail)
        initToolbar("自定义")
    }

    private fun initToolbar(title: String) {
        tvTitle.text = title
        mToolbar.title = ""// 这是必须的
        setSupportActionBar(mToolbar)
        // 显示系统默认的返回按钮
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        mToolbar.setNavigationOnClickListener { finish() }
    }
}