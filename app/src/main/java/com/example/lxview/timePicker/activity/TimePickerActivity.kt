package com.example.lxview.timePicker.activity

import android.annotation.SuppressLint
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.RelativeLayout
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import com.example.lxview.R
import com.example.lxview.base.BaseActivity
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
class TimePickerActivity : BaseActivity(), OnTimeSelectListener {


    companion object {
        const val maxCount = 2000
    }

    private var trailerEditText: AppCompatEditText? = null
    private var trailerIconBack: AppCompatImageView? = null
    private var trailerIconDelete: AppCompatImageView? = null
    private var trailerTextCount: AppCompatTextView? = null
    private var trailerTimeText: AppCompatTextView? = null
    private var trailerTipsText: AppCompatTextView? = null
    private var trailerCreateText: AppCompatTextView? = null
    private var trailerCreateRl: RelativeLayout? = null
    private var trailerTimeRl: RelativeLayout? = null
    private var pvTime: TimePickerView? = null
    private var onSelectTime: Long? = null

    override val contentId: Int
        get() = R.layout.im_group_trailer_activity

    override fun initView() {
        trailerEditText = findViewById(R.id.im_group_trailer_content_edit)
        trailerIconBack = findViewById(R.id.im_group_trailer_back)
        trailerIconDelete = findViewById(R.id.im_group_trailer_delete_icon)
        trailerTextCount = findViewById(R.id.im_group_trailer_content_edit_count)
        trailerTimeText = findViewById(R.id.im_group_trailer_time_tv)
//        trailerTipsText = findViewById(R.id.im_group_trailer_tips_tv)
        trailerCreateText = findViewById(R.id.im_group_trailer_create_tv)
        trailerCreateRl = findViewById(R.id.im_group_trailer_create_rl)
        trailerTimeRl = findViewById(R.id.im_group_trailer_time_rl)
        pvTime = IMTrailerTimePickerUtils.initTimePicker(this, this)
    }

    override fun initData() {
        onSetNoDataView()
    }


    override fun initListener() {
        trailerEditText?.addTextChangedListener(object :TextWatcher{
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
        trailerIconBack?.setOnClickListener { //返回上一级
            finish()
        }
        trailerTimeRl?.setOnClickListener { //时间选择器
            if (pvTime != null) pvTime?.show(it)
        }

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

}