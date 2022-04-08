package com.example.lxview.function.jetpack.lifecycle

import android.util.Log
import androidx.lifecycle.*


/**
 * author: 李 祥
 * date:   2022/2/16 3:50 下午
 * description:
 */
class MyPlayListener : LifecycleObserver {

    /**
     * LifeCycle 有三种实现方法：
     *LifecycleObserver 配合 @OnLifecycleEvent 注解
     *DefaultLifecycleObserver 拥有宿主所有生命周期事件
     *LifecycleEventObserver 将宿主生命周期事件封装成 Lifecycle.Event
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
     fun initVideo() {
        Log.d(TAG, "initVideo")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
     fun startPlay() {
        Log.d(TAG, "startPlay")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
     fun pausePlay() {
        Log.d(TAG, "pausePlay")
    }

    companion object {
        private const val TAG = "MyVideoPlayListener"
    }
}