package com.example.lxview.function.animation.activity

import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import com.example.baselib.utils.startAct
import com.example.lxview.R
import com.example.lxview.base.activity.BaseActivity
import com.example.lxview.base.activity.SampleDialogAndPopActivity
import com.example.lxview.function.home.activity.ShareFunctionActivity
import com.example.lxview.function.ipc.binder.AidlBinderActivity
import com.example.lxview.function.ipc.messager.client.MessengerActivity
import com.example.lxview.function.jetpack.MyJetPackActivity
import com.example.lxview.function.listTimestamp.activity.ListTimeActivity
import com.example.lxview.function.listswip.listviewdeletedemo.ListSwipMainActivity
import com.example.lxview.function.myFreeView.activity.SampleActivity
import com.example.lxview.function.timePicker.activity.TimePickerActivity

/**
 * author: 李 祥
 * date:   2022/11/24 14:59
 * description:
 */


class FrameAnimationActivity : BaseActivity() {
    private var runImg: ImageView? = null
    private var startImg: ImageView? = null
    private var animRun: AnimationDrawable? = null
    private var animStart: AnimationDrawable? = null
    private var runStartOrStopTv: AppCompatTextView? = null
    private var runStartOrStopTv2: AppCompatTextView? = null
    override val contentId: Int
        get() = R.layout.animation_frame_activity_layout


    override fun initView() {
        runImg = findViewById(R.id.animation_frame_run_img)
        startImg = findViewById(R.id.animation_frame_start_img)
        runStartOrStopTv = findViewById(R.id.animation_frame_run_control_tv)
        runStartOrStopTv2 = findViewById(R.id.animation_frame_loading_control_tv)

        runImg?.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.anim_init_loading_run))
        animRun = runImg?.drawable as AnimationDrawable
        animRun?.start()

        startImg?.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.anim_login_start_loading))
        animStart = startImg?.drawable as AnimationDrawable
        animStart?.start()
    }

    override fun initData() {

    }

    override fun initListener() {
        runStartOrStopTv?.setOnClickListener {
            if (animRun?.isRunning == true) {
                animRun?.stop()
                runStartOrStopTv?.text = "继续"
            } else {
                animRun?.run()
                runStartOrStopTv?.text = "暂停"
            }
        }
        runStartOrStopTv2?.setOnClickListener {
            animStart?.start()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        animRun?.stop()
    }


}