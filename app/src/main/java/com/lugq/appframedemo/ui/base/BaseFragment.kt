package com.lugq.appframedemo.ui.base

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

/**
 * @Description
 * @Author Lu Guoqiang
 * @Time 2019/6/25 21:03
 */
abstract class BaseFragment<T : BasePresenter<*>?> : Fragment() {

    protected var mPresenter: T? = null
    private var rootView: View? = null
    protected var mActivity: Activity? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mPresenter = createPresenter()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (rootView == null) {
            rootView = inflater.inflate(provideContentViewId(), container, false)
        } else {
            if (rootView?.parent is ViewGroup) {
                val parent = rootView!!.parent as ViewGroup
                parent.removeView(rootView)
            }
        }
        return rootView
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mActivity = context as Activity
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
        initListener()
    }

    /**
     * 初始化数据
     */
    open fun initData() {}

    /**
     * 设置listener的操作
     */
    open fun initListener() {}

    //用于创建Presenter和判断是否使用MVP模式(由子类实现)
    protected abstract fun createPresenter(): T

    //得到当前界面的布局文件id(由子类实现)
    protected abstract fun provideContentViewId(): Int

    override fun onDestroy() {
        super.onDestroy()
        if (mPresenter != null) {
            mPresenter?.detachView()
            mPresenter = null
        }
        rootView = null
    }
}