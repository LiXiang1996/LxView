package com.example.lxview.base.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.*
//import io.utown.core.log.CTLog
import kotlin.coroutines.CoroutineContext

abstract class Base2Activity : AppCompatActivity(), CoroutineScope {

//    var log: CTLog = CTLog(this.javaClass.name)

    private val errHandler = CoroutineExceptionHandler{ corContext, ex ->
//        CTLog.e("defaultErrHandler", ex.toString(), ex)
    }
    override val coroutineContext: CoroutineContext
        get() = lifecycleScope.coroutineContext + errHandler

    open fun initListener() {}
    open fun initView() {}
    open fun initViewDataBinding(){}
    open suspend fun handleData() {}
    open val layout: Int = 0
    open val isBinding: Boolean = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (layout != 0) {
            if (isBinding) {
                initViewDataBinding()
            } else {
                setContentView(layout)
            }
        }
        initView()
        initListener()
        launch {
            handleData()
        }
    }


    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}