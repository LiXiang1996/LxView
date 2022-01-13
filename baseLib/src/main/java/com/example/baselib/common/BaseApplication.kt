package com.example.baselib.common

import android.app.Application
import android.content.res.Configuration


open class BaseApplication : Application() {

    companion object {
        lateinit var context: Application
        lateinit var requestActivityConfig: Configuration
        var ccDeviceToken: String = ""
        var needToReInitSdk = false
    }

//    lateinit var appComponent: AppComponent
}
