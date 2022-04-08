package com.example.lxview.base.activity


import android.os.Bundle
import androidx.databinding.ViewDataBinding
import com.example.lxview.R
import com.example.lxview.base.dialog.ViewLoading
//import com.gyf.immersionbar.ImmersionBar

import kotlinx.coroutines.launch

open class BaseDataBindActivity<DB : ViewDataBinding> : BaseBindActivity<DB>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        ImmersionBar.with(this).fullScreen(true)
//            .navigationBarColor(R.color.transparent)
//            .statusBarColor(R.color.transparent).init()
    }

    protected fun request(done: suspend () -> Unit, isShowLoading: Boolean = true) {
        launch {
            if (isShowLoading)
                ViewLoading.show(this@BaseDataBindActivity)
            done()
            if (isShowLoading)
                ViewLoading.dismiss(this@BaseDataBindActivity)
        }
    }
}