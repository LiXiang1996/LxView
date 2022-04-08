package com.example.lxview.login

import androidx.lifecycle.Observer


/**
 * author: 李 祥
 * date:   2022/3/9 11:20 上午
 * description:
 */
class LoginVerifyCountDownController : Observer<Long> {

    private var observerKey = System.currentTimeMillis().toString()
    companion object{
        var loginManager: LoginVerifyCountDownController?=null
    }

    init {
//        TimerManager.addObserverForever(1000, observerKey, this)
    }


    fun get(): LoginVerifyCountDownController {
        var loginVerifyCountDownController = loginManager
        if (loginVerifyCountDownController == null){
            loginVerifyCountDownController = LoginVerifyCountDownController()
            loginManager = loginVerifyCountDownController
        }
        return loginVerifyCountDownController
    }


    fun removeObserver(observer: Observer<Long>){
//        TimerManager.removeObserver(observerKey, this)
//        TimerManager.removeObserver(this)
//        TimerManager.release()
        LoginVerifyCountDownUtils.clearAllCountdownObservers()
    }

    override fun onChanged(t: Long?) {
        LoginVerifyCountDownUtils.countDownTime(t)
    }

}