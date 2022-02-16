package com.example.lxview.jetpack.lifecycle

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity

/**
 * author: 李 祥
 * date:   2022/2/16 3:39 下午
 * description:
 */
class MyLifeCycleActivity : AppCompatActivity() {
    /**
     * 1、如果是想监听某个 Activity 的生命周期，需要我们做的就是自定义组件，实现 LifecycleObserver 接口即可，该接口没有接口方法，不需要任何具体的实现。
     * 创建一个 MyPlayListener 类，实现 LifecycleObserver 接口，与播放相关的逻辑全在这个类里面完成。对于组件里面需要在 Activity 生命周期变化时得到通知的方法，
     * 用 @OnLifecycleEvent(Lifecycle.Event.ON_XXX) 注解进行标记，这样当 Activity 生命周期发生变化时，被标记过的方法便会被自动调用。
     * 而不需要通过onCreate()等方法
     *
     * 2、也可以使用 LifecycleService 解耦 Service 与组件，使用 @OnLifecycleEvent 标记希望在 Server 生命周期发生变化时得到同步调用的方法。
     *
     * 3、具有生命周期的组件除了 Activity、Fragment 和 Service 外，还有 Application。ProcessLifecycleOwner 就是用来监听整个应用程序的生命周期情况。
     */
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        val myLocationListener = MyPlayListener()
        lifecycle.addObserver(myLocationListener)
        super.onCreate(savedInstanceState, persistentState)
    }

}
