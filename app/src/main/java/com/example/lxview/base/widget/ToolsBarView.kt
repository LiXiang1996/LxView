package com.example.lxview.base.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import com.example.lxview.R
import com.example.lxview.login.LXConstant

/**
 * author: 李 祥
 * date:   2022/4/13 5:23 下午
 * description:
 */
class ToolsBarView@JvmOverloads constructor(context: Context?, attrs: AttributeSet?=null, defStyleAttr: Int=0) : LinearLayout(context, attrs, defStyleAttr) {

    private var backIcon:AppCompatTextView?=null
    private var title:AppCompatTextView?=null
    private var rightText:AppCompatTextView?=null
    private var nextText:AppCompatTextView?=null
    private var rightImg:AppCompatImageView?=null


    init {
        initView()
    }

    private fun initView() {
        View.inflate(context, R.layout.toolbar_normal_layout,this)
        backIcon  = findViewById(R.id.toolbar_left)
        title  = findViewById(R.id.toolbar_title)
        rightText  = findViewById(R.id.toolbar_right)
        nextText  = findViewById(R.id.toolbar_right_text_next)
        rightImg  = findViewById(R.id.toolbar_right_image)
    }


    fun addListener(titleStr:String,backStr:String,result: (String)->Unit){
        backIcon?.setOnClickListener { result(LXConstant.TOOLBAR_LEFT) }
        rightText?.setOnClickListener { result(LXConstant.TOOLBAR_RIGHT) }
        title?.text = titleStr
        backIcon?.text = backStr
    }


    fun setBackIconVisibility(visible: Boolean){
        backIcon?.visibility = if (visible) View.VISIBLE else View.GONE
    }

    fun setRightTextIconVisibility(visible: Boolean){
        rightText?.visibility = if (visible) View.VISIBLE else View.GONE
    }

    fun setRightImgIconVisibility(visible: Boolean){
        rightImg?.visibility = if (visible) View.VISIBLE else View.GONE
    }

    fun  setTitleColor(resID:Int){
        title?.setTextColor(rootView.context.getColor(resID))
    }

}