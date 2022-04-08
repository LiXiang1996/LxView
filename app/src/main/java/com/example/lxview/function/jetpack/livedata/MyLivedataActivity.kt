package com.example.lxview.function.jetpack.livedata

import android.content.ContentValues.TAG
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatTextView
import androidx.lifecycle.MutableLiveData

import androidx.lifecycle.ViewModelProviders


/**
 * author: 李 祥
 * date:   2022/2/16 3:39 下午
 * description:LIvaData简单使用
 *
 * 使用：1、LiveData 内部已经实现了观察者模式，如果你的数据要同时通知几个界面，可以采取这种方式
 *          2、我们知道 LiveData 数据变化的时候，会回调 Observer 的 onChange 方法，
 * 但是回调的前提是 lifecycleOwner（即所依附的 Activity 或者 Fragment） 处于 started 或者 resumed 状态，它才会回调，
 * 否则，必须等到 lifecycleOwner 切换到前台的时候，才回调。
 *         3、因此，这对性能方面确实是一个不小的提升。但是，对于你想做一些类似与在后台工作的（黑科技）， liveData 就不太适合了，
 * 你可以使用 observeForever 方法，或者自己实现观察者模式去吧。
 */
class MyLivedataActivity : AppCompatActivity() {

    /**
     *Livedata 主要有几个方法
     *1.observe
     *2.onActive 当回调该方法的时候，表示该 liveData 正在被使用，因此应该保持最新
     *3.onInactive 当该方法回调时，表示他所有的 obervers 没有一个状态处理 STARTED 或者 RESUMED，注意，这不代表没有 observers。
     *4.observeForever 跟 observe 方法不太一样的是，它在 Activity 处于 onPause ，onStop， onDestroy 的时候，都可以回调 obsever 的 onChange 方法，
     * 但是有一点需要注意的是，我们必须手动 remove obsever，否则会发生内存泄漏。比如全局倒计时监听
     *
     */
    private var mTvName: AppCompatTextView? = null
    private var mTvName2: AppCompatTextView? = null

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        /**
         *  ViewModelProvider 的 of 方法，他主要有四个方法，分别是
         *1.ViewModelProvider of(@NonNull Fragment fragment)
         *2.ViewModelProvider of(@NonNull FragmentActivity activity)
         *3.ViewModelProvider of(@NonNull Fragment fragment, @Nullable Factory factory)
         *4.ViewModelProvider of(@NonNull FragmentActivity activity, @Nullable Factory factory)
         *
         * 1,2 方法之间的主要区别是传入 Fragment 或者 FragmentActivity。
         * 而我们知道，通过 ViewModel of 方法创建的 ViewModel 实例， 对于同一个 fragment 或者 fragmentActivity 实例，ViewModel 实例是相同的，
         * 因而我们可以利用该特点，在 Fragment 中创建 ViewModel 的时候，传入的是 Fragment 所依附的 Activity。
         * 因而他们的 ViewModel 实例是相同的，从而可以做到共享数据。
         */
        val mTestViewModel = ViewModelProviders.of(this).get(TestViewModel::class.java) //不带参数
        val mTestViewModel2 = ViewModelProviders.of(this, TestViewModel2.Factory("lx")).get(TestViewModel2::class.java) //带参数

        val nameEvent: MutableLiveData<String> = mTestViewModel.getNameEvent()
        val nameEvent2: MutableLiveData<String> = mTestViewModel2.getNameEvent()

        /**     原来的写法，当内容改变实时更新到UI展示
         *  nameEvent.observe(this, object : Observer<String?> {
        override fun onChanged(t: String?) {
        Log.i(TAG, "onChanged: s = $t")
        mTvName?.text = t
        }
        })
         */ //lambda表达式
        nameEvent.observe(this, { t ->
            Log.i(TAG, "onChanged: s = $t")
            mTvName?.text = t
        })
        nameEvent2.observe(this, { t ->
            Log.i(TAG, "onChanged: s = $t")
            mTvName2?.text = t
        })


        super.onCreate(savedInstanceState, persistentState)
    }

}
