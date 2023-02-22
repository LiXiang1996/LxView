package com.example.lxview

import android.annotation.SuppressLint
import android.content.Intent
import android.os.CountDownTimer
import android.widget.ImageView
import android.widget.TextView
import com.example.lxview.base.activity.BaseActivity

/**
 * author: 李 祥
 * date:   2022/3/31 1:43 下午
 * description:启屏页，加载5秒
 */
class SplashActivity : BaseActivity() {

    override val contentId: Int
        get() = R.layout.activity_splash

    private val skipText:TextView by lazy { findViewById(R.id.skip_txt) }
    private val splashImg:ImageView by lazy { findViewById(R.id.splash_img) }


    override fun initView() {
        timer.start()
        splashImg.setImageResource(R.drawable.splash_bg2)
        super.initView()
    }

    override fun initListener() {
        super.initListener()
        skipText.setOnClickListener {
            toMain()
        }
    }



    private val timer = object : CountDownTimer(5000, 1000) {
        override fun onFinish() {
            toMain()
        }

        @SuppressLint("SetTextI18n")
        override fun onTick(millisUntilFinished: Long) {
            val step = millisUntilFinished / 1000
            skipText.text = "${(step + 1)} ${getString(R.string.skip)}"
        }
    }


    fun toMain(){
        val intent =Intent()
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.setClass(this,MainActivity::class.java)
        this.startActivity(intent)
        finish()
    }
}