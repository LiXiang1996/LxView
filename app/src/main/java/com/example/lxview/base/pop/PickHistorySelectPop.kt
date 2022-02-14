package com.example.lxview.base.pop

import android.annotation.SuppressLint
import android.app.Activity
import android.graphics.drawable.ColorDrawable
import android.view.*
import android.widget.PopupWindow
import androidx.appcompat.widget.AppCompatTextView
import com.example.lxview.R


@SuppressLint("InflateParams")
class PickHistorySelectPop private constructor(v: View, val listener: (Int?) -> Unit?, private val focusAble: Boolean = true) {

    var popWindow: PopupWindow? = null

    companion object {

        fun show(v: View, focusAble: Boolean? = true, listener: (Int?) -> Unit?): PickHistorySelectPop {
            return PickHistorySelectPop(v, listener, focusAble ?: true)
        }

    }

    private lateinit var replied: AppCompatTextView
    private lateinit var reply: AppCompatTextView
    private lateinit var refused: AppCompatTextView
    private lateinit var all: AppCompatTextView

    init {
        show(v, listener)
    }

    enum class SelectType(val type: Int) {
        REPLIED(1), NO_REPLY(0), REFUSED(3), ALL(-1)
    }

    private fun show(v: View, onResult: (Int?) -> Unit?) {
        popWindow = PopupWindow(v.context)
        val view = View.inflate(v.context, R.layout.select_pop_content, null)
        popWindow?.contentView = view
        replied = view.findViewById(R.id.pick_history_filter_replied)
        reply = view.findViewById(R.id.pick_history_filter_no_reply)
        refused = view.findViewById(R.id.pick_history_filter_refused)
        all = view.findViewById(R.id.pick_history_filter_all)
        popWindow?.setBackgroundDrawable(ColorDrawable())
        popWindow?.isOutsideTouchable = true
        popWindow?.isTouchable = true
        popWindow?.isFocusable = true
        replied.setOnClickListener {
            onResult(SelectType.REPLIED.type)
            popWindow?.dismiss()
        }
        reply.setOnClickListener {
            onResult(SelectType.NO_REPLY.type)
            popWindow?.dismiss()
        }
        refused.setOnClickListener {
            onResult(SelectType.REFUSED.type)
            popWindow?.dismiss()
        }
        all.setOnClickListener {
            onResult(SelectType.ALL.type)
            popWindow?.dismiss()
        }
        popWindow?.showAtLocation(v, Gravity.END or Gravity.TOP, 50, 200)
    }

}