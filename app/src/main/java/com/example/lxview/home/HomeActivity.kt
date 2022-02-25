package com.example.lxview.home

import android.content.Intent
import androidx.appcompat.widget.AppCompatTextView
import com.example.baselib.utils.startAct
import com.example.lxview.myFreeView.activity.ExOneActivity
import com.example.lxview.R
import com.example.lxview.myFreeView.activity.SampleActivity
import com.example.lxview.base.BaseActivity
import com.example.lxview.base.activity.SampleDialogAndPopActivity
import com.example.lxview.function.home.activity.HomeFunctionActivity
import com.example.lxview.ipc.binder.AidlBinderActivity
import com.example.lxview.ipc.messager.client.MessengerActivity
import com.example.lxview.jetpack.MyJetPackActivity
import com.example.lxview.listTimestamp.activity.ListTimeActivity
import com.example.lxview.timePicker.activity.TimePickerActivity

/**
 * author: 李 祥
 * date:   2021/12/10 10:38 上午
 * description:
 */
class HomeActivity : BaseActivity() {

    var tv1: AppCompatTextView? = null
    var tv2: AppCompatTextView? = null
    private var tv3: AppCompatTextView? = null
    var tv4: AppCompatTextView? = null
    var tv5: AppCompatTextView? = null
    private var tv6: AppCompatTextView? = null
    private var tv7: AppCompatTextView? = null
    var tvJetPackModule: AppCompatTextView? = null
    override val contentId: Int
        get() = R.layout.home_activity_layout


    override fun initView() {
        tv1 = findViewById(R.id.home_define_view)
        tv2 = findViewById(R.id.home_define_loading)
        tv3 = findViewById(R.id.home_define_time_picker)
        tv4 = findViewById(R.id.home_system_time_picker)
        tv5 = findViewById(R.id.home_system_date_picker)
        tv6 = findViewById(R.id.home_function)
        tv7 = findViewById(R.id.home_dialog_and_pop)
        tvJetPackModule = findViewById(R.id.home_jetpack)
    }

    override fun initData() {
    }

    override fun initListener() {
        tv1?.setOnClickListener {
            this.startActivity(Intent(this, ExOneActivity::class.java))
        }
        tv2?.setOnClickListener {
            this.startActivity(Intent(this, SampleActivity::class.java))
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
            this.startActivity(Intent(this, HomeFunctionActivity::class.java))
        }
        tv7?.setOnClickListener {
            this.startActivity(Intent(this, SampleDialogAndPopActivity::class.java))
        }
        tvJetPackModule?.setOnClickListener {
            startAct<MyJetPackActivity>()
        }

    }


}