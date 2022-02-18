package com.example.lxview.jetpack

import androidx.appcompat.widget.AppCompatTextView
import com.example.baselib.utils.startAct
import com.example.lxview.R
import com.example.lxview.base.BaseActivity
import com.example.lxview.jetpack.lifecycle.MyLifeCycleActivity
import com.example.lxview.jetpack.livedata.MyLivedataActivity
import com.example.lxview.jetpack.navigation.MyNavigationActivity

/**
 * author: 李 祥
 * date:   2022/2/16 6:58 下午
 * description:
 */
class MyJetPackActivity : BaseActivity() {
    var tvLivedataActivity: AppCompatTextView? = null
    var tvLifecycleActivity: AppCompatTextView? = null
    var tvDataBindingActivity: AppCompatTextView? = null
    var tvNavigationActivity: AppCompatTextView? = null
    var tv5: AppCompatTextView? = null

    override val contentId: Int
        get() = R.layout.jetpack_activity_layout


    override fun initView() {
        tvLivedataActivity = findViewById(R.id.jetpack_livedata)
        tvLifecycleActivity = findViewById(R.id.jetpack_lifecycle)
        tvDataBindingActivity = findViewById(R.id.jetpack_databinding)
        tvNavigationActivity = findViewById(R.id.jetpack_navigation)
    }

    override fun initListener() {
        tvLivedataActivity?.setOnClickListener {
            startAct<MyLivedataActivity>()
        }
        tvLifecycleActivity?.setOnClickListener {
            startAct<MyLifeCycleActivity>()
        }
        tvDataBindingActivity?.setOnClickListener { }
        tvNavigationActivity?.setOnClickListener {
            startAct<MyNavigationActivity>()
        }
    }
}