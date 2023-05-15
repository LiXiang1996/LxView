package com.example.lxview.function.animation.activity

import android.content.Intent
import androidx.appcompat.widget.AppCompatTextView
import com.example.baselib.utils.startAct
import com.example.lxview.R
import com.example.lxview.base.activity.BaseActivity
import com.example.lxview.base.activity.SampleDialogAndPopActivity
import com.example.lxview.function.home.activity.ShareFunctionActivity
import com.example.lxview.function.ipc.binder.AidlBinderActivity
import com.example.lxview.function.ipc.messager.client.MessengerActivity
import com.example.lxview.function.jetpack.MyJetPackActivity
import com.example.lxview.function.listswip.listviewdeletedemo.ListSwipMainActivity
import com.example.lxview.function.timePicker.activity.TimePickerActivity

/**
 * author: 李 祥
 * date:   2022/11/24 14:59
 * description:
 */


class AnimationGoActivity : BaseActivity() {

    var frameAnimationTv: AppCompatTextView? = null
    var tweenAnimation: AppCompatTextView? = null
    private var tv3: AppCompatTextView? = null
    var tv4: AppCompatTextView? = null
    var tv5: AppCompatTextView? = null
    private var tv6: AppCompatTextView? = null
    private var tvTitle: AppCompatTextView? = null
    private var tv7: AppCompatTextView? = null
    private var tv8: AppCompatTextView? = null
    private var tv9: AppCompatTextView? = null
    var tvJetPackModule: AppCompatTextView? = null
    override val contentId: Int
        get() = R.layout.home_activity_layout


    override fun initView() {
         frameAnimationTv= findViewById(R.id.home_define_view)
        tweenAnimation = findViewById(R.id.home_define_loading)
        tv3 = findViewById(R.id.home_define_time_picker)
        tv4 = findViewById(R.id.home_system_time_picker)
        tv5 = findViewById(R.id.home_system_date_picker)
        tv6 = findViewById(R.id.home_function)
        tv7 = findViewById(R.id.home_dialog_and_pop)
        tv8 = findViewById(R.id.home_dialog_listview)
        tv9 = findViewById(R.id.home_animation)
        tvTitle = findViewById(R.id.login_tv_title)
        tvJetPackModule = findViewById(R.id.home_jetpack)
        frameAnimationTv?.text = "帧动画"
        tweenAnimation?.text = "补间动画"
        tvTitle?.text = "动画"
    }

    override fun initData() {
    }

    override fun initListener() {
        frameAnimationTv?.setOnClickListener {
            this.startActivity(Intent(this, FrameAnimationActivity::class.java))
        }
        tweenAnimation?.setOnClickListener {
            this.startActivity(Intent(this, TweenAnimationActivity::class.java))
        }
        tv3?.setOnClickListener {
            this.startActivity(Intent(this, TimePickerActivity::class.java))
        }
        tv4?.setOnClickListener {
            this.startActivity(Intent(this, MessengerActivity::class.java))
        }
        tv5?.setOnClickListener {
            this.startActivity(Intent(this, AidlBinderActivity::class.java))
        }

        tv6?.setOnClickListener {
            //分享
            this.startActivity(Intent(this, ShareFunctionActivity::class.java))
        }
        tv7?.setOnClickListener {
            this.startActivity(Intent(this, SampleDialogAndPopActivity::class.java))
        }
        tv8?.setOnClickListener {
            this.startActivity(Intent(this, ListSwipMainActivity::class.java))
        }
        tv9?.setOnClickListener {
            this.startActivity(Intent(this, ListSwipMainActivity::class.java))
        }
        tvJetPackModule?.setOnClickListener {
            startAct<MyJetPackActivity>()
        }

    }


}