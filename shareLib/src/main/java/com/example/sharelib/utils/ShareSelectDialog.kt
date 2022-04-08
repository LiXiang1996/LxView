package com.example.sharelib.utils

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatTextView
import com.example.baselib.utils.setVisible
import com.example.sharelib.R
import com.example.baselib.utils.TextStyleUtils

/**
 * author: lx
 * Description:一个dialog
 */
open class ShareSelectDialog(context: Context, themeResId: Int, val state: Int, val isShow: Boolean) : Dialog(context, themeResId), View.OnClickListener {
    private lateinit var mShareDialogContent: LinearLayout
    private lateinit var share_facebook: LinearLayout
    private lateinit var share_messager: LinearLayout
    private lateinit var share_whatapp: LinearLayout
    private lateinit var share_instagram: LinearLayout
    private lateinit var share_snapchat: LinearLayout
    private lateinit var share_messages: LinearLayout
    private lateinit var share_more: LinearLayout
    private lateinit var share_download: LinearLayout
    private lateinit var share_copy_link: LinearLayout
    private lateinit var mShareTextInfo: AppCompatTextView
    private lateinit var mShareSelectDialogBg: View

    // 定义一个接口类型变量
    private var onSelectListener: OnSelectShareListener? = null

    // 定义一个 set 方法
    fun setOnSelectShareListener(e: OnSelectShareListener) {
        this.onSelectListener = e
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    private fun init() {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.share_dialog_fragment_layout, null)
        setCanceledOnTouchOutside(true)
        initView(view)
        initData()
        setListener()
        setContentView(view)
        val pl = window?.attributes
        pl?.gravity = Gravity.BOTTOM
        pl?.width = context.resources.displayMetrics.widthPixels
        pl?.height = WindowManager.LayoutParams.WRAP_CONTENT
        window?.decorView?.setPadding(0, 0, 0, 0)
        window?.attributes = pl
    }

    private fun initView(view: View) {
        mShareDialogContent = view.findViewById(R.id.common_pop_share_content)
        share_facebook = view.findViewById(R.id.share_facebook)
        share_messager = view.findViewById(R.id.share_messager)
        share_whatapp = view.findViewById(R.id.share_whatapp)
        share_instagram = view.findViewById(R.id.share_instagram)
        share_snapchat = view.findViewById(R.id.share_snapchat)
        share_messages = view.findViewById(R.id.share_messages)
        share_more = view.findViewById(R.id.share_more)
        mShareSelectDialogBg = view.findViewById(R.id.mShareSelectDialogBg)
        mShareTextInfo = view.findViewById(R.id.common_pop_share_tv_title)
        share_download = view.findViewById(R.id.share_download)
        share_copy_link = view.findViewById(R.id.share_copy_link)
    }

    private fun initData() {
        mShareTextInfo.text = TextStyleUtils.textColorToFEA30F("邀请你的好朋友","yaaaaaa")
        share_copy_link.setVisible(isShow)
        share_download.setVisible(isShow)
    }

    private fun setListener() {
        mShareDialogContent.setOnClickListener(this)
        share_facebook.setOnClickListener(this)
        share_messager.setOnClickListener(this)
        share_whatapp.setOnClickListener(this)
        share_instagram.setOnClickListener(this)
        share_snapchat.setOnClickListener(this)
        share_messages.setOnClickListener(this)
        share_more.setOnClickListener(this)
        mShareSelectDialogBg.setOnClickListener(this)
        share_download.setOnClickListener(this)
        share_copy_link.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.share_facebook -> if (onSelectListener != null) onSelectListener!!.selectShareType("share_facebook")

            R.id.share_messager -> if (onSelectListener != null) onSelectListener!!.selectShareType("share_messager")

            R.id.share_whatapp -> if (onSelectListener != null) onSelectListener!!.selectShareType("share_whatapp")

            R.id.share_instagram -> if (onSelectListener != null) onSelectListener!!.selectShareType("share_instagram")

            R.id.share_snapchat -> if (onSelectListener != null) onSelectListener!!.selectShareType("share_snapchat")

            R.id.share_messages -> if (onSelectListener != null) onSelectListener!!.selectShareType("share_messages")

            R.id.share_more -> if (onSelectListener != null) onSelectListener!!.selectShareType("share_more")

            R.id.share_download -> if (onSelectListener != null) onSelectListener!!.selectShareType("share_download")

            R.id.share_copy_link -> if (onSelectListener != null) onSelectListener!!.selectShareType("share_copy_link")

            R.id.common_pop_share_content -> {

            }
        }
        if (v.id != R.id.common_pop_share_content)
            dismiss()
    }

    interface OnSelectShareListener {
        fun selectShareType(type: String)
    }
}
