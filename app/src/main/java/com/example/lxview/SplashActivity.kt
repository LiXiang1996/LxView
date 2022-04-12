package com.example.lxview

import android.annotation.SuppressLint
import android.os.CountDownTimer
import android.widget.ImageView
import android.widget.TextView
import com.example.baselib.utils.startAct
import com.example.lxview.base.BaseActivity
import com.example.lxview.home.MainActivity

/**
 * author: 李 祥
 * date:   2022/3/31 1:43 下午
 * description:
 */
class SplashActivity :BaseActivity() {

    override val contentId: Int
        get() = R.layout.activity_splash

    private val skipText:TextView by lazy { findViewById(R.id.skip_txt) }
    private val splashImg:ImageView by lazy { findViewById(R.id.splash_img) }

    override fun initData() {
        super.initData()
    }

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
        startAct<MainActivity>()
    }
}