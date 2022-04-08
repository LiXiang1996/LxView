package com.example.baselib.view

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import android.os.Build
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.res.ResourcesCompat
import com.example.baselib.R

/**
 * @author: JayQiu
 * @date: 2022/3/17
 * @description:
 */
class TitleView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, def: Int = 0) : ConstraintLayout(context, attrs, def), ITitleView {
    private val tvLeft: TextView
    private val tvCenter: TextView
    private val tvRight: TextView
    private val imRight: ImageView
    private val viewRoot: View

    init {
        inflate(context, R.layout.view_title_toolbar, this)
        tvLeft = findViewById(R.id.tv_left)
        tvCenter = findViewById(R.id.tv_center)
        tvRight = findViewById(R.id.tv_right)
        viewRoot = findViewById(R.id.view_root)
        imRight = findViewById(R.id.iv_right)
        initAttrs(context, attrs)
    }

    private fun initAttrs(context: Context, attrs: AttributeSet?) {
        val array = context.obtainStyledAttributes(attrs, R.styleable.TitleView)
        setCenterTitle(array.getString(R.styleable.TitleView_centerTitle))
        setLeftText(array.getString(R.styleable.TitleView_leftTitle))
        setRightText(array.getString(R.styleable.TitleView_rightTitle))
        array.getDrawable(R.styleable.TitleView_leftImg)?.let { setLeftImg(it) }
        array.getDrawable(R.styleable.TitleView_rightImg)?.let { setRightImg(it) }
        setCenterTitleColor(array.getColor(R.styleable.TitleView_centerTitleColor, getColor(context, R.color.white)))
        setLeftTextColor(array.getColor(R.styleable.TitleView_leftTitleColor, getColor(context, R.color.white)))
        setRightTextColor(array.getColor(R.styleable.TitleView_rightTitleColor, getColor(context, R.color.white)))
        setCenterTitleSize(array.getDimensionPixelSize(R.styleable.TitleView_centerTitleSize, 17).toFloat())
        viewRoot.setBackgroundColor(array.getColor(R.styleable.TitleView_backgroundColor, getColor(context, R.color.activity_color)))
        array.recycle()
    }

    override fun setFullScreen(statusBarHight: Int) {
        viewRoot.setPadding(0, statusBarHight, 0, 0)
    }

    override fun setCenterTitle(title: String?) {
        tvCenter.text = title
    }

    override fun setCenterTitleColor(@ColorInt color: Int) {
        tvCenter.setTextColor(color)
    }

    override fun setCenterTitleColorStateList(@ColorRes color: Int) {
        tvCenter.setTextColor(getColorStateList(context, color))
    }

    override fun setCenterTitleSize(size: Float) {
        tvCenter.textSize = size
    }

    override fun setLeftText(text: String?) {
        tvLeft.text = text
    }

    override fun setLeftTextColor(@ColorInt color: Int) {
        tvLeft.setTextColor(color)
    }

    override fun setLeftTextColorStateList(@ColorRes color: Int) {
        tvLeft.setTextColor(getColorStateList(context, color))
    }

    override fun setLeftImg(img: Drawable) {
        tvLeft.setCompoundDrawablesWithIntrinsicBounds(img, null, null, null)
    }

    override fun setRightText(text: String?) {
        tvRight.text = text
    }

    override fun setRightTextColor(@ColorInt color: Int) {
        tvRight.setTextColor(color)
    }

    override fun setBackgroundColor(@ColorInt color: Int) {
        viewRoot.setBackgroundColor(color)
    }

    override fun setRightTextColorStateList(@ColorRes color: Int) {
        tvRight.setTextColor(getColorStateList(context, color))
    }

    override fun setRightEnable(enable: Boolean) {
        tvRight.isEnabled = enable
    }

    override fun setRightImg(img: Drawable) {
        tvRight.setCompoundDrawablesWithIntrinsicBounds(null, null, img, null)
    }

    override fun setTitleBackgroundColor(color: Int) {
        viewRoot.setBackgroundColor(color)
    }

    override fun setOnLeftClickListener(listener: OnClickListener?) {
        tvLeft.setOnClickListener(listener)
    }

    override fun setOnRightClickListener(listener: OnClickListener?) {
        tvRight.setOnClickListener(listener)
    }

    override fun getLeftView(): View {
        return tvLeft
    }

    override fun getCenterView(): View {
        return tvCenter
    }

    override fun getRightView(): View {
        return tvRight
    }

    override fun getRightImageView(): ImageView {
        return imRight
    }

    private fun getColorStateList(context: Context, @ColorRes id: Int): ColorStateList? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            context.resources.getColorStateList(id, context.theme)
        } else ResourcesCompat.getColorStateList(resources, id, context.theme)
    }

    @ColorInt
    private fun getColor(context: Context, @ColorRes id: Int): Int {
        return if (Build.VERSION.SDK_INT >= 23) context.resources.getColor(id, context.theme) else ResourcesCompat.getColor(resources, id, context.theme)
    }


}