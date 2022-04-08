package com.example.lxview.base

import android.app.Application
import android.content.Context

open class BaseApplication : Application() {
    companion object{
        lateinit var appContext:Context
    }

    override fun onCreate() {
        super.onCreate()
        appContext = this.applicationContext
        SPStore.init(appContext)
    }

    override fun onTrimMemory(level: Int) {
        super.onTrimMemory(level)
        when(level){
            TRIM_MEMORY_COMPLETE -> {
                //No enough memory, app will be kill soon.
            }
        }
    }
}