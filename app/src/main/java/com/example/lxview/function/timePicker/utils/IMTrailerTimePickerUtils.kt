package com.example.lxview.function.timePicker.utils

import android.content.Context
import android.view.Gravity
import android.view.ViewGroup
import android.widget.FrameLayout
import com.example.lxview.R
import com.example.lxview.function.timePicker.builder.TimePickerBuilder
import com.example.lxview.function.timePicker.listener.OnTimeSelectListener
import com.example.lxview.function.timePicker.view.TimePickerView
import java.util.*

object IMTrailerTimePickerUtils {

    /**
     * 初始化时间滚动选择
     */
    fun initTimePicker(context: Context, listener: OnTimeSelectListener): TimePickerView { // Dialog 模式下，在底部弹出
        val pvTime = TimePickerBuilder(context, listener).setType(booleanArrayOf(true, true, true, true, true, false)).setRangDate(getStartDate(), getEndDate()).setDate(getStartDate()).isDialog(true) // 默认设置false ，内部实现将DecorView 作为它的父控件。
            .build()
        if (pvTime != null) {
            val mDialog = pvTime.dialog
            if (mDialog != null) {
                val params = FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.BOTTOM)
                params.leftMargin = 0
                params.rightMargin = 0
                pvTime.dialogContainerLayout.layoutParams = params

                val dialogWindow = mDialog.window
                if (dialogWindow != null) {
                    dialogWindow.setWindowAnimations(R.style.picker_view_slide_anim) // 修改动画样式
                    dialogWindow.setGravity(Gravity.BOTTOM) // 改成Bottom,底部显示
                }
            }
        }
        return pvTime
    }


    private fun getEndDate(): Calendar {
        val startDate = Calendar.getInstance()
        startDate.set(2040, 12, 31)
        return startDate

    }

    private fun getStartDate(): Calendar {
        return Calendar.getInstance()
    }
}
