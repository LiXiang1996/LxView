package com.example.sharelib.ui

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.widget.RelativeLayout
import com.example.baselib.utils.MultilingualUtils
import com.example.sharelib.R
import com.example.sharelib.ShareManager
import com.example.sharelib.`interface`.Platform
import com.example.sharelib.utils.ShareSelectDialog
import io.github.inflationx.viewpump.ViewPumpContextWrapper

class ShareDialogActivity : Activity(), ShareSelectDialog.OnSelectShareListener{
    private var mDelayedTime = 400
    private lateinit  var mBg:RelativeLayout
    private var mHandler: Handler = @SuppressLint("HandlerLeak") object : Handler() {
        override fun handleMessage(msg: Message) {
            when (msg.what) {
                0 -> {
                    finish()
                    overridePendingTransition(0, 0)
                }
            }
        }
    }
    private val mShareSelectDialog by lazy {
        object : ShareSelectDialog(this,
            R.style.share_DialogStyle,
            intent.getIntExtra("share_style_state", 0)
            , intent.getBooleanExtra("share_style_ptype", false)) {
            override fun dismiss() {
                super.dismiss()
                mHandler.sendEmptyMessage(0)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.share_activity_dialog)
        ShareActivityManager.instance.addActivity(this)
        mShareSelectDialog.show()
        mShareSelectDialog.setOnSelectShareListener(this)
        mDelayedTime = resources.getInteger(android.R.integer.config_mediumAnimTime)
        mBg = findViewById(R.id.mShareBackgroundLayout)
        mBg.setOnClickListener { finish() }
    }

    override fun selectShareType(type: String) {
        when (type) {
            "share_facebook" -> ShareManager.getInstance(this).setPlatform(Platform.FACEBOOK).doShare(this)

            "share_messager" -> ShareManager.getInstance(this).setPlatform(Platform.FBMESSAGER).doShare(this)

            "share_whatapp" -> ShareManager.getInstance(this).setPlatform(Platform.WHATAPP).doShare(this)

            "share_instagram" -> ShareManager.getInstance(this).setPlatform(Platform.INSTAGRAM).doShare(this)

            "share_snapchat" -> ShareManager.getInstance(this).setPlatform(Platform.SNAPCHAT).doShare(this)

            "share_messages" -> ShareManager.getInstance(this).setPlatform(Platform.MESSAGE).doShare(this)

            "share_friends" -> ShareManager.getInstance(this).setPlatform(Platform.FRIENDS).doShare(this)

            "share_copy" -> ShareManager.getInstance(this).setPlatform(Platform.COPY).doShare(this)

            "share_more" -> ShareManager.getInstance(this).setPlatform(Platform.ANDROID).doShare(this)

            "share_add_favorites" -> ShareManager.getInstance(this).setPlatform(Platform.ADD_TO_FAVORITES).doShare(this)

            "share_copy_link" -> ShareManager.getInstance(this).setPlatform(Platform.SHARE_COPY_LINK).doShare(this)

            "share_download" -> ShareManager.getInstance(this).setPlatform(Platform.SHARE_DOWNLOAD).doShare(this)

            "share_line"->ShareManager.getInstance(this).setPlatform(Platform.LINE).doShare(this)
        }
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
