package com.lugq.appframedemo.ui.base

import android.app.Activity
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.lugq.appframedemo.listener.PermissionListener
import com.lugq.appframedemo.ui.activity.MainActivity
import org.greenrobot.eventbus.EventBus
import java.util.*

/**
 * @Description
 * @Author Lu Guoqiang
 * @Time 2019/6/28 16:19
 */
abstract class BaseActivity<T : BasePresenter<*>?> : AppCompatActivity() {

    protected var mPresenter: T? = null
    private var mPermissionListener: PermissionListener? = null

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mPresenter = createPresenter()
        //lifecycle.addObserver(mPresenter)
        // 子类不再需要设置布局ID
        setContentView(provideContentViewId())
        currentActivity = this
        initView(savedInstanceState)
        initData()
        initListener()
    }

    open fun initView() {}

    open fun initView(savedInstanceState: Bundle?) {
        initView()
    }

    open fun initData() {}
    open fun initListener() {}

    // 用于创建Presenter和判断是否使用MVP模式(由子类实现)
    // 如果子类没有Presenter则返回null即可
    protected abstract fun createPresenter(): T

    //得到当前界面的布局文件id(由子类实现)
    protected abstract fun provideContentViewId(): Int

    override fun onResume() {
        super.onResume()
        currentActivity = this
    }

    override fun onPause() {
        super.onPause()
        currentActivity = null
    }

    /**
     * 统一退出控制
     */
    override fun onBackPressed() {
        if (currentActivity is MainActivity) { //如果是主页面
            if (System.currentTimeMillis() - mPreTime > 2000) { // 两次点击间隔大于2秒
                //UIUtils.showToast("再按一次，退出应用");
                mPreTime = System.currentTimeMillis()
                return
            }
        }
        super.onBackPressed() // finish()
    }

    // 包含了EventBus,如果不需要，则去掉以下3个函数
    private fun isEventBusRegisted(subscribe: Any?): Boolean {
        return EventBus.getDefault().isRegistered(subscribe)
    }

    // EventBus
    private fun registerEventBus(subscribe: Any?) {
        if (!isEventBusRegisted(subscribe)) {
            EventBus.getDefault().register(subscribe)
        }
    }

    // EventBus
    private fun unregisterEventBus(subscribe: Any?) {
        if (isEventBusRegisted(subscribe)) {
            EventBus.getDefault().unregister(subscribe)
        }
    }

    /**
     * 申请运行时权限
     */
    fun requestRuntimePermission(permissions: Array<String>, permissionListener: PermissionListener) {
        mPermissionListener = permissionListener
        val permissionList: MutableList<String> = ArrayList()
        for (permission in permissions) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                permissionList.add(permission)
            }
        }
        if (permissionList.isNotEmpty()) {
            ActivityCompat.requestPermissions(this, permissionList.toTypedArray(), 1)
        } else {
            permissionListener.onGranted()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 1) {
            if (grantResults.isNotEmpty()) {
                val deniedPermissions: MutableList<String> = ArrayList()
                for (i in grantResults.indices) {
                    val grantResult = grantResults[i]
                    val permission = permissions[i]
                    if (grantResult != PackageManager.PERMISSION_GRANTED) {
                        deniedPermissions.add(permission)
                    }
                }
                if (deniedPermissions.isEmpty()) {
                    mPermissionListener!!.onGranted()
                } else {
                    mPermissionListener!!.onDenied(deniedPermissions)
                }
            }
        }
    }

    protected fun addFragment(containerViewId: Int, fragment: Fragment?) {
        val fragmentManager = this.supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.add(containerViewId, fragment!!)
        fragmentTransaction.commit()
    }

    companion object {
        private var mPreTime: Long = 0

        // 当前的 activity
        var currentActivity: Activity? = null
    }
}