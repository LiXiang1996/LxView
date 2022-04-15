package com.example.lxview.login

import android.content.Context
import androidx.lifecycle.LifecycleOwner
//import com.zj.timer.TimerManager
import java.lang.ref.WeakReference

object LoginVerifyCountDownUtils {

    private lateinit var countdownInfo: CountDownInfo

    fun countDownTime(value: Long?) {
        if (value != null) {
            countdownInfo.usedTime += value
        }
    }


    data class CountDownInfo(var expireTime: Long, private var l: WeakReference<CountdownListener?>) {
        var usedTime: Long = 0
            set(value) {
                if (field != value) {
                    field = value
                    calculateExpireTime()
                }
            }

        private fun calculateExpireTime() {
            this.l.get()?.onCountdown(expireTime - usedTime)
            LXConstant.expireTime = expireTime-usedTime
        }

        internal fun clearListener() {
            this.l.clear()
        }

        internal fun updateListener(l: WeakReference<CountdownListener?>) {
            this.l = l
        }

    }


    fun clearAllCountdownObservers() {
        countdownInfo.clearListener()
    }

    //为每个Item注册监听器
    internal fun registerCountdownObserver(expireTime: Long, l: CountdownListener) {
//        val exists = CountDownInfo(expireTime, WeakReference(l))
//        exists.updateListener(WeakReference(l))
//        countdownInfo = exists
    }


    interface CountdownListener {
        fun onCountdown(remainingTime: Long)
    }


    fun addObserverTime(className:String,context: Context){
        registerCountdownObserver(LXConstant.expireTime, context as CountdownListener )
//        TimerManager.addObserver(context as LifecycleOwner, 1000L, className, { time ->
//            if (time > LXConstant.expireTime) {
//                TimerManager.removeObserver(context,className)
//            }else{
//                countDownTime(time)
//            }
//        })
    }
}