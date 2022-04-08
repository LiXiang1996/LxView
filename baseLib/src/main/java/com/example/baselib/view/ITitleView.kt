package com.example.baselib.view

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes

/**
 * @author: JayQiu
 * @date: 2022/3/17
 * @description:
 */
open interface ITitleView {
    fun setFullScreen(statusBarHight: Int)
    fun setCenterTitle(title: String?)
    fun setCenterTitleColor(@ColorInt color: Int)
    fun setCenterTitleColorStateList(@ColorRes color: Int)
    fun setCenterTitleSize(size: Float)
    fun setLeftText(text: String?)
    fun setLeftTextColor(@ColorInt color: Int)
    fun setLeftTextColorStateList(@ColorRes color: Int)
    fun setLeftImg(img: Drawable)
    fun setRightText(text: String?)
    fun setRightTextColor(@ColorInt color: Int)
    fun setBackgroundColor(@ColorInt color: Int)
    fun setRightTextColorStateList(@ColorRes color: Int)
    fun setRightEnable(enable: Boolean)
    fun setRightImg(img: Drawable)
    fun setTitleBackgroundColor(@ColorInt color: Int)
    fun setOnLeftClickListener(listener: View.OnClickListener?)
    fun setOnRightClickListener(listener: View.OnClickListener?)
    fun getLeftView(): View?
    fun getCenterView(): View?
    fun getRightView(): View?
    fun getRightImageView(): ImageView?
}