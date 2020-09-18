package com.lugq.appframedemo.ui.activity

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.lugq.appframedemo.App
import com.lugq.appframedemo.R
import com.lugq.appframedemo.ui.base.BaseActivity
import com.lugq.appframedemo.ui.fragment.HomeFragment
import com.lugq.appframedemo.ui.fragment.MineFragment
import com.lugq.appframedemo.ui.fragment.NewsFragment
import com.lugq.appframedemo.ui.presenter.MainPresenter
import com.lugq.appframedemo.ui.view.MainView

class MainActivity : BaseActivity<MainPresenter>(), MainView {

    companion object {
        val TAG = MainActivity::class.simpleName
        private const val EXTRA_SAVE_INDEX = "SaveIndex"
        private const val EXTRA_IS_HOME_ACT_DESTROY = "isHomeActDestroy"
    }

    private var currentFragment: Fragment? = null
    private var mMineFragment: MineFragment? = null
    private var mNewsFragment: NewsFragment? = null
    private var mHomeFragment: HomeFragment? = null
    private var saveIndex = 0

    // activity的销毁标识
    private var isHomeActDestroy = false

    override fun createPresenter() = MainPresenter(this)

    override fun provideContentViewId() = R.layout.activity_main

    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)
        // 正常情况下初始化
        if (savedInstanceState == null) {
            Log.i(TAG, "执行了 ---- onCreate(Bundle savedInstanceState is 空 )")
            initFragment()
            //第一次初始化首页默认显示第一个fragment
            switchFragment(mHomeFragment)
        } else {
            Log.i(TAG, "执行了 ---- onCreate(Bundle savedInstanceState is 非空 )")
            restoreFragmentInstance(savedInstanceState)

            // 因为内存原因Activity退到后天被kill，再打开的时候，会重新走onCreate
            // 这时候恢复的Fragment默认恢复第一个
            // 实际中这里可以根据需求进行修改
            switchFragment(mHomeFragment)
        }
    }


    private fun initFragment() {
        mHomeFragment = HomeFragment()
        mNewsFragment = NewsFragment()
        mMineFragment = MineFragment()
    }

    /**
     * 采用 add show 方式切换fragment
     */
    private fun switchFragment(targetFragment: Fragment?) {
        if (null == targetFragment) {
            Log.i(TAG, "为空")
            return
        }
        val transaction = supportFragmentManager.beginTransaction()
        // 先隐藏掉所有的Fragment
        hideFragment(transaction)

        // 在 show 操作
        if (!targetFragment.isAdded) {
            transaction.add(R.id.fl_container, targetFragment, targetFragment.javaClass.simpleName)
            transaction.show(targetFragment)
            transaction.commit()
            println("还没添加呢")
        } else {
            transaction
                    .show(targetFragment)
                    .commit()
            println("添加了( ⊙o⊙ )哇")
        }
        currentFragment = targetFragment
    }

    private fun hideFragment(transaction: FragmentTransaction) {
        if (mHomeFragment != null) {
            transaction.hide(mHomeFragment!!)
        }
        if (mNewsFragment != null) {
            transaction.hide(mNewsFragment!!)
        }
        if (mMineFragment != null) {
            transaction.hide(mMineFragment!!)
        }
    }

    private fun restoreFragmentInstance(savedInstanceState: Bundle?) {
        if (savedInstanceState != null) {
            saveIndex = savedInstanceState.getInt(EXTRA_SAVE_INDEX, 0)
            isHomeActDestroy = savedInstanceState.getBoolean(EXTRA_IS_HOME_ACT_DESTROY, false)
            val manager = supportFragmentManager
            val f0 = manager.findFragmentByTag(HomeFragment::class.java.simpleName)
            val f1 = manager.findFragmentByTag(NewsFragment::class.java.simpleName)
            val f2 = manager.findFragmentByTag(MineFragment::class.java.simpleName)

            // 复用
            mHomeFragment = if (f0 != null) {
                f0 as HomeFragment?
            } else {
                HomeFragment.newInstance()
            }
            mNewsFragment = if (f1 != null) {
                f1 as NewsFragment?
            } else {
                NewsFragment.newInstance()
            }
            mMineFragment = if (f2 != null) {
                f2 as MineFragment?
            } else {
                MineFragment.newInstance()
            }
        }
    }

    // 退出的时候保存状态
    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(EXTRA_SAVE_INDEX, saveIndex)
        outState.putBoolean(EXTRA_IS_HOME_ACT_DESTROY, true)
        super.onSaveInstanceState(outState)
    }

    fun onClick(view: View) {
        when (view.id) {
            R.id.btn_1 -> switchFragment(mHomeFragment)
            R.id.btn_2 -> switchFragment(mNewsFragment)
            R.id.btn_3 -> switchFragment(mMineFragment)
        }
    }

    /*btnFake.setOnClickListener {
        login()
    }

    btnLogin.setOnClickListener {
        startActivity(Intent(this, LoginActivity::class.java))
    }*/

    fun login() {
        /*
        val observable = ApiService.createApiService().getWarningListByParams("hahaha")
        observable?.compose(CommonSchedulers.io2main())
                ?.subscribe(object : BaseObserver<ResultResponse<List<WarnintEntity>>>() {
                    override fun onNext(t: ResultResponse<List<WarnintEntity>>) {
                        val datas = t.data
                        for (item in datas) {
                            LogUtils.i("请求成功${item.content}")
                        }
                    }

                })*/
    }

}
