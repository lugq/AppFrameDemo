package com.lugq.appframedemo.ui.fragment

import android.os.Bundle
import com.blankj.utilcode.util.ToastUtils
import com.lugq.appframedemo.R
import com.lugq.appframedemo.ui.base.BaseFragment
import com.lugq.appframedemo.ui.presenter.MinePresenter
import com.lugq.appframedemo.ui.view.MineView
import kotlinx.android.synthetic.main.activity_main.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 *
 */
class MineFragment : BaseFragment<MinePresenter>(), MineView {

    companion object {
        @JvmStatic
        fun newInstance() =
                MineFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }

    private var param1: String? = null
    private var param2: String? = null

    override fun initData() {
        super.initData()
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    /*override fun createPresenter(): MinePresenter {
        return MinePresenter(this)
    }*/
    /*override fun provideContentViewId(): Int {
        return R.layout.fragment_mine
    }*/

    // 更精简写法
    override fun createPresenter() = MinePresenter(this)

    override fun provideContentViewId() = R.layout.fragment_mine

    override fun initListener() {
        super.initListener()
        mPresenter?.getUserProfile("000")

        btnLogin.setOnClickListener {
            mPresenter
        }

        clClear.setOnClickListener {
            ToastUtils.showShort("clear")
        }
    }
}