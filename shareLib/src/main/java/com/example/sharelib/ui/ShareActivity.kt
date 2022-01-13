package com.example.sharelib.ui

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import com.example.baselib.utils.MultilingualUtils
import com.example.sharelib.R
import com.example.sharelib.ShareFactory
import com.example.sharelib.ShareManager
import io.github.inflationx.viewpump.ViewPumpContextWrapper

class ShareActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.share_activity)
        ShareActivityManager.instance.addActivity(this)
        (ShareFactory.getShareManager(this) as ShareManager).exec(this)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        ShareFactory.getShareManager(this).onActivityResult(requestCode, resultCode, data)
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        val config = MultilingualUtils.onConfigurationChanged(this) ?: newConfig
        super.onConfigurationChanged(config)
    }

    override fun attachBaseContext(newBase: Context) {
        val ctx = MultilingualUtils.attachBaseContext(newBase)
        val lct = ViewPumpContextWrapper.wrap(ctx)
        super.attachBaseContext(lct)
    }
}