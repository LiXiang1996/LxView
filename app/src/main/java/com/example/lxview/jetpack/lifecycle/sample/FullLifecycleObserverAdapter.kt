package com.example.lxview.jetpack.lifecycle.sample

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.OnLifecycleEvent

import androidx.lifecycle.LifecycleOwner

import androidx.lifecycle.LifecycleObserver




/**
 * author: 李 祥
 * date:   2022/2/21 6:24 下午
 * description:
 */
class FullLifecycleObserverAdapter internal constructor(lifecycleOwner: LifecycleOwner, observer: FullLifecycleObserver) : LifecycleObserver {
    private val mObserver: FullLifecycleObserver = observer
    private val mLifecycleOwner: LifecycleOwner = lifecycleOwner

    //在activity合适的生命周期调用合适的方法
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
        Log.i(TAG, "onCreate: ")
        mObserver.onCreate(mLifecycleOwner)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStart() {
        Log.i(TAG, "onStart: ")
        mObserver.onStart(mLifecycleOwner)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume() {
        Log.i(TAG, "onResume: ")
        mObserver.onResume(mLifecycleOwner)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onPause() {
        Log.i(TAG, "onPause: ")
        mObserver.onPause(mLifecycleOwner)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onStop() {
        Log.i(TAG, "onStop: ")
        mObserver.onStop(mLifecycleOwner)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy() {
        Log.i(TAG, "onDestroy: ")
        mObserver.onDestroy(mLifecycleOwner)
    }

    companion object {
        private const val TAG = "FullLifecycleObserverAd"
    }

}
