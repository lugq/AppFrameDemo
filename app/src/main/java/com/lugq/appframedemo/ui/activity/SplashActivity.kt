package com.lugq.appframedemo.ui.activity

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.lugq.appframedemo.R
import kotlinx.android.synthetic.main.activity_splash.*

/**
 * 开屏页
 */
class SplashActivity : AppCompatActivity() {

    companion object {
        const val DELAY_MILLIS = 3000L
    }
    private lateinit var mHandler: Handler

    private lateinit var bgBitmap: Bitmap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        bgBitmap = compressBackgroundImg()
        ivBg.setImageBitmap(bgBitmap)

        mHandler = Handler()

        mHandler.postDelayed({
            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            finish()
        }, DELAY_MILLIS)
    }

    private fun compressBackgroundImg(): Bitmap {
        val options = BitmapFactory.Options()
        options.inScaled = false
        options.inSampleSize = 1
        options.inPreferredConfig = Bitmap.Config.RGB_565
        return BitmapFactory.decodeResource(resources, R.drawable.bg_splash, options)
    }

    override fun onDestroy() {
        super.onDestroy()
        bgBitmap.recycle()
        System.gc()
    }
}
