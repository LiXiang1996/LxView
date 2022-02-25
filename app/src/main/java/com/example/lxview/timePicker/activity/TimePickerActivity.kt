package com.example.lxview.timePicker.activity

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.ProgressDialog
import android.app.TimePickerDialog
import android.os.Handler
import android.os.Message
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.lifecycle.LifecycleOwner
import com.example.lxview.R
import com.example.lxview.base.BaseActivity
import com.example.lxview.jetpack.lifecycle.sample.LifecycleHandler
import com.example.lxview.timePicker.listener.OnTimeSelectListener
import com.example.lxview.timePicker.utils.IMTrailerTimePickerUtils
import com.example.lxview.timePicker.view.TimePickerView
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


/**
 * author: 李 祥
 * date:   2021/12/8 10:55 上午
 * description:
 */
class TimePickerActivity : BaseActivity(), OnTimeSelectListener,LifecycleOwner {


    companion object {
        const val maxCount = 2000
    }

    private var trailerEditText: AppCompatEditText? = null
    private var trailerIconBack: AppCompatImageView? = null
    private var trailerIconDelete: AppCompatImageView? = null
    private var trailerTextCount: AppCompatTextView? = null
    private var trailerTimeText: AppCompatTextView? = null
    private var timePikerSystem: AppCompatTextView? = null
    private var datePikerSystem: AppCompatTextView? = null
    private var defineDatePikerSystem: AppCompatTextView? = null
    private var progressSystem: AppCompatTextView? = null
    private var trailerTipsText: AppCompatTextView? = null
    private var trailerCreateText: AppCompatTextView? = null
    private var trailerCreateRl: RelativeLayout? = null
    private var trailerTimeRl: RelativeLayout? = null
    private var pvTime: TimePickerView? = null
    private var onSelectTime: Long? = null
    private var progressDialog: ProgressDialog? = null
    var mHandler: Handler? = null

    override val contentId: Int
        get() = R.layout.im_group_trailer_activity

    override fun initView() {
        trailerEditText = findViewById(R.id.im_group_trailer_content_edit)
        timePikerSystem = findViewById(R.id.system_time_picker)
        datePikerSystem = findViewById(R.id.system_date_picker)
        defineDatePikerSystem = findViewById(R.id.define_date_picker)
        progressSystem = findViewById(R.id.system_process)
        trailerIconBack = findViewById(R.id.im_group_trailer_back)
        trailerIconDelete = findViewById(R.id.im_group_trailer_delete_icon)
        trailerTextCount = findViewById(R.id.im_group_trailer_content_edit_count)
        trailerTimeText = findViewById(R.id.im_group_trailer_time_tv) //        trailerTipsText = findViewById(R.id.im_group_trailer_tips_tv)
        trailerCreateText = findViewById(R.id.im_group_trailer_create_tv)
        trailerCreateRl = findViewById(R.id.im_group_trailer_create_rl)
        trailerTimeRl = findViewById(R.id.im_group_trailer_time_rl)
        pvTime = IMTrailerTimePickerUtils.initTimePicker(this, this)
    }

    override fun initData() {
        onSetNoDataView()
    }


    override fun initListener() {
        trailerEditText?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val textCount = p0?.length ?: 0
                trailerTextCount?.text = String.format("%1\$d/%2\$d", textCount, maxCount)
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        })
        trailerIconDelete?.setOnClickListener { //删除预告

        }
        datePikerSystem?.setOnClickListener { //系统日期选择器
            val calendar: Calendar = Calendar.getInstance()
            val datePickerDialog = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { datepicker, year, month, day ->
                val text = "你选择了：" + year + "年" + (month + 1) + "月" + day + "日"
                trailerTimeText?.text = text
                Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
            }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show()
        }
        timePikerSystem?.setOnClickListener { //系统日期选择器
            val calendar = Calendar.getInstance()
            TimePickerDialog(this, { timepicker, hourOfDay, minute ->
                val text = "你选择了" + hourOfDay + "时" + minute + "分"
                trailerTimeText?.text = text
                Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
            }, calendar[Calendar.HOUR_OF_DAY], calendar[Calendar.MINUTE], true).show()
        }
        trailerIconBack?.setOnClickListener { //返回上一级
            finish()
        }

        trailerTimeRl?.setOnClickListener { //时间选择器
            if (pvTime != null) pvTime?.show(it)
        }

        defineDatePikerSystem?.setOnClickListener { //时间选择器
            if (pvTime != null) pvTime?.show(it)
        }
        progressSystem?.setOnClickListener { //时间选择器
            showDialog()
        }


        mHandler = @SuppressLint("HandlerLeak") object : LifecycleHandler(this) {
            override fun handleMessage(msg: Message) {
                when (msg.what) {
                    0 -> {
                        progressDialog?.dismiss()
                    }
                    1->{
                        progressDialog?.progress = msg.arg1
                    }
                }
            }
        }

    }

    private fun showDialog() {
        progressDialog = ProgressDialog(this)
        progressDialog?.setTitle("下载中>>>>")
        progressDialog?.setMessage("请稍后")
        progressDialog?.setCancelable(true)
        progressDialog?.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL)
        progressDialog?.max = 100
        progressDialog?.show()

        Thread() {//三秒弹窗消失
            var i = 0
            run() {
                while (i in 0..100) {
                    i += 1
                    try {
                        Thread.sleep(100)
                    } catch (e: Exception) {

                    }
                    val msg = Message()
                    msg.arg1 =i
                    msg.what = 1
                    mHandler?.sendMessage(msg)
                }
                mHandler?.sendEmptyMessage(0)
            }
        }.start()
    }


    private fun onSetNoDataView() {
        trailerCreateText?.text = "创建"
        trailerTimeText?.text = "开始时间"
    }


    private fun getTime(time: Long, pattern: String): String {
        val date = Date(time)
        return dateFormat(date, pattern)
    }

    private fun dateFormat(date: Date, pattern: String): String {
        val format = SimpleDateFormat(pattern, Locale.getDefault())
        return format.format(date)
    }

    /**
     * 判断当前日期是星期几
     * @param  pTime     设置的需要判断的时间  //格式如2021-04-20
     * @return dayForweek 判断结果
     */
    @SuppressLint("SimpleDateFormat")
    fun getWeek(pTime: String): String {
        var week = "星期"
        val format = SimpleDateFormat("yyyy-MM-dd")
        val c = Calendar.getInstance()
        try {
            c.time = format.parse(pTime)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        if (c.get(Calendar.DAY_OF_WEEK) == 1) {
            week += "日"
        }
        if (c.get(Calendar.DAY_OF_WEEK) == 2) {
            week += "一"
        }
        if (c.get(Calendar.DAY_OF_WEEK) == 3) {
            week += "二"
        }
        if (c.get(Calendar.DAY_OF_WEEK) == 4) {
            week += "三"
        }
        if (c.get(Calendar.DAY_OF_WEEK) == 5) {
            week += "四"
        }
        if (c.get(Calendar.DAY_OF_WEEK) == 6) {
            week += "五"
        }
        if (c.get(Calendar.DAY_OF_WEEK) == 7) {
            week += "六"
        }
        return week
    }

    /**
     * 时间选择器动态赋值
     */
    override fun onTimeSelect(date: Date?, v: View?) {
        onSelectTime = date?.time
        trailerTimeText?.text = date?.time?.let { DateUserToStringYMDHM(it) + getWeek(DateUserToStringYMDHM(it)) }
    }

    /**
     * 格式成为完整时间
     */
    fun DateUserToStringYMDHM(time: Long): String {
        val date = Date(time)
        val sdr = SimpleDateFormat("yyyy-MM-dd   HH:mm   ", Locale.getDefault())
        return sdr.format(date)
    }

//    override fun onDestroy() {
//        super.onDestroy()
//        mHandler?.removeCallbacksAndMessages(null);
//    }

}