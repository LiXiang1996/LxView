package com.example.lxview.function.jetpack.lifecycle.sample

import android.os.Handler
import androidx.lifecycle.LifecycleOwner
import android.os.Looper

/**
 * author: 李 祥
 * date:   2022/2/21 6:30 下午
 * description:使用LifeCycle实现实时感知activity生命周期的Handler
 */
open class LifecycleHandler : Handler, FullLifecycleObserver {
    private var mLifecycleOwner: LifecycleOwner?

    constructor(lifecycleOwner: LifecycleOwner?) {
        mLifecycleOwner = lifecycleOwner
        addObserver()
    }

    constructor(lifecycleOwner: LifecycleOwner?, callback: Callback?) : super(callback) {
        mLifecycleOwner = lifecycleOwner
        addObserver()
    }

    constructor(lifecycleOwner: LifecycleOwner?, looper: Looper?) : super(looper!!) {
        mLifecycleOwner = lifecycleOwner
        addObserver()
    }

    constructor(lifecycleOwner: LifecycleOwner?, looper: Looper?, callback: Callback?) : super(looper!!, callback) {
        mLifecycleOwner = lifecycleOwner
        addObserver()
    }


    /**
     * 为每一个handler添加生命周期观察者
     */
    private fun addObserver() {
        if (mLifecycleOwner != null) {
            mLifecycleOwner!!.lifecycle.addObserver(FullLifecycleObserverAdapter(mLifecycleOwner!!, this))
        }
    }

    override fun onCreate(owner: LifecycleOwner?) {}
    override fun onStart(owner: LifecycleOwner?) {}
    override fun onResume(owner: LifecycleOwner?) {}
    override fun onPause(owner: LifecycleOwner?) {}
    override fun onStop(owner: LifecycleOwner?) {}
    override fun onDestroy(owner: LifecycleOwner?) {
        removeCallbacksAndMessages(null)
    }
}