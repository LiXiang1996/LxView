package com.example.lxview.base.activity

import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import kotlinx.coroutines.*


abstract class BaseBindActivity<DB : ViewDataBinding> : Base2Activity(), CoroutineScope {
    override val isBinding: Boolean = true
    lateinit var mBinding: DB
    override fun initViewDataBinding() {
        mBinding = DataBindingUtil.setContentView(this, layout)
        mBinding.lifecycleOwner = this
    }

}