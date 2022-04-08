package com.example.lxview.base.widget

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Switch
import android.widget.TextView
import com.example.lxview.R

/**
 * 个人中心用于条目的布局，这里显示的条布局数据用来点击跳转相关页面
 */
class LineControlView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
        LinearLayout(context, attrs, defStyleAttr) {
    private var title: String? = null
    private var content: String? = null
    private var canNav: Boolean = false
    private var openSwitch: Boolean = false
    private var maxLength: Int = 0
    private var IconRes: Int = 0
    private var textContentColor: Int = 0
    private var hasContentIcon: Boolean = false
    private var titleView: TextView? = null
    private var contentView: TextView? = null
    private var IconView: ImageView? = null
    private var navigationView: ImageView? = null
    private var contentIcon: ImageView? = null
    private var mSwitch: Switch? = null
    private var isNull: Boolean = false

    var iconRes: Int
        get() = IconRes
        set(iconRes) {
            IconRes = iconRes
            IconView!!.setImageResource(iconRes)
        }

    init {
        val ta = context.obtainStyledAttributes(attrs, R.styleable.MeLineView, 0, 0)
        try {
            title = ta.getString(R.styleable.MeLineView_LineViewTitle)
            content = ta.getString(R.styleable.MeLineView_LineViewContent)
            canNav = ta.getBoolean(R.styleable.MeLineView_canNavition, true)
            openSwitch = ta.getBoolean(R.styleable.MeLineView_openSwitch, false)
            isNull = ta.getBoolean(R.styleable.MeLineView_isNull, false)
            maxLength = ta.getInteger(R.styleable.MeLineView_maxLength, -1)
            IconRes = ta.getResourceId(R.styleable.MeLineView_iconRes, -1)
            textContentColor = ta.getColor(R.styleable.MeLineView_textContentColor, Color.parseColor("#FF3F4F"))
            hasContentIcon = ta.getBoolean(R.styleable.MeLineView_hasContentIcon, false)
            initView()
        } finally {
            ta.recycle()
        }
    }

    private fun initView() {
        titleView = TextView(context)
        contentView = TextView(context)
        IconView = ImageView(context)
        navigationView = ImageView(context)
        mSwitch = Switch(context)
        gravity = Gravity.CENTER_VERTICAL
        orientation = HORIZONTAL
        setPadding(dp2px(18f), dp2px(23f), dp2px(18f), dp2px(23f))
        titleView?.setTextColor(Color.parseColor("#333333"))
        titleView?.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15f)
        contentView?.gravity = Gravity.RIGHT
        contentView?.setTextColor(Color.parseColor("#FEA30F"))
        contentView?.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12f)
        setTitle(title)
        setContent(content)
        if (maxLength == -1) {
            contentView!!.maxWidth = 16
        } else {
            contentView!!.maxWidth = maxLength
        }
        navigationView!!.setImageResource(R.mipmap.ic_all_back)

        val imgParams = LayoutParams(dp2px(23f), dp2px(23f))

        val contentParams = LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT)
        contentParams.weight = 1f
        contentParams.rightMargin = dp2px(12f)
        val navigationParams = LayoutParams(dp2px(23f), dp2px(23f))

        val titleParams = LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        if (IconRes != -1) {
            iconRes = IconRes
            titleParams.leftMargin = dp2px(12f)
            addView(IconView, imgParams)
        }
        addView(titleView, titleParams)
        addView(contentView, contentParams)
        if (hasContentIcon) {
            contentIcon = ImageView(context)
            addView(contentIcon, ViewGroup.LayoutParams(dp2px(23f), ViewGroup.LayoutParams.MATCH_PARENT))
        }
        if (openSwitch) {
            addView(mSwitch, titleParams)
        }
        if (canNav) {
            addView(navigationView, navigationParams)
        }
    }

    fun getisNull(): Boolean {
        return isNull
    }

    fun setIsNull(isNull: Boolean?) {
        this.isNull = isNull!!
        when (isNull) {
            true -> {
                titleView!!.setTextColor(textContentColor)
                contentView!!.setTextColor(textContentColor)
            }

            false -> {
                titleView!!.setTextColor(Color.parseColor("#333543"))
                contentView!!.setTextColor(Color.parseColor("#999AA1"))
            }
        }
    }

    fun setContentStyle(color: String, text: String, isBold: Boolean) {
        contentView!!.setTextColor(textContentColor)
        contentView!!.setTextColor(Color.parseColor(color))
        contentView!!.text = text
        val tp = contentView!!.paint
        tp.isFakeBoldText = isBold
    }

    fun getTitle(): String {
        return titleView!!.text.toString()
    }

    fun setTitle(title: String?) {
        this.title = title
        titleView!!.text = this.title
    }

    fun setCanNavition(canNavition: Boolean) {
        if (!canNavition) removeView(navigationView)
    }

    fun getContent(): String {
        return contentView!!.text.toString()
    }

    fun setContent(content: String?) {
        this.content = content
        contentView!!.text = this.content
    }

    private fun dp2px(dpValue: Float): Int {
        val scale = context.resources.displayMetrics.density
        return (dpValue * scale + 0.5f).toInt()
    }

    private fun px2sp(pxValue: Float): Int {
        val fontScale = context.resources.displayMetrics.scaledDensity
        return (pxValue / fontScale + 0.5f).toInt()
    }

    fun getContentIcon(): ImageView? {
        return if (contentIcon == null) {
            null
        } else contentIcon
    }
}
