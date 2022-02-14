package com.example.lxview.base.activity

import android.app.AlertDialog
import android.app.Dialog
import android.view.Gravity
import android.view.LayoutInflater
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import com.example.lxview.R
import com.example.lxview.base.BaseActivity
import com.example.lxview.base.dialog.PictureDialog
import com.example.lxview.base.dialog.ViewLoading
import com.example.lxview.base.pop.PickHistorySelectPop

/**
 * author: 李 祥
 * date:   2022/2/13 12:29 上午
 * description:
 */
class SampleDialogAndPopActivity : BaseActivity() {


    private var alertDialogTv: AppCompatTextView? = null
    private var easyDialogTv: AppCompatTextView? = null
    private var easyPopTv: AppCompatTextView? = null
    private var dialogViewLoadingTv: AppCompatTextView? = null
    private var pictureLoadingTv: AppCompatTextView? = null

    override val contentId: Int
        get() = R.layout.dialog_and_pop_activity_layout


    override fun initView() {
        alertDialogTv = findViewById(R.id.sample_alert_dialog_easy)
        easyDialogTv = findViewById(R.id.sample_dialog_easy)
        easyPopTv = findViewById(R.id.sample_pop_easy)
        dialogViewLoadingTv = findViewById(R.id.sample_dialog_view_loading)
        pictureLoadingTv = findViewById(R.id.picture_dialog_view_loading)
    }


    override fun initListener() {
        alertDialogTv?.setOnClickListener {
            val dialog1: AlertDialog.Builder = AlertDialog.Builder(this);
            dialog1.setTitle("提示").setMessage("你的金额不足！").setNegativeButton("关闭", null).create().show();
        }

        easyDialogTv?.setOnClickListener {
            val dialog = Dialog(this, R.style.jz_style_dialog_progress) //设置一些属性
            val localView = LayoutInflater.from(this).inflate(R.layout.common_global_volume_dialog, null) //设置自定义的弹窗UI
            dialog.setContentView(localView)
            val window = dialog.window
//            window!!.addFlags(Window.FEATURE_ACTION_BAR)
//            window.addFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL)
//            window.addFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
            window?.setLayout(-2, -2) //-2 其实就是WRAP_CONTENT
            val localLayoutParams = window?.attributes
            localLayoutParams?.gravity = Gravity.BOTTOM
            window?.attributes = localLayoutParams

            val imageV: AppCompatImageView = dialog.findViewById(R.id.volume_image_tip)
            val cancelTv = dialog.findViewById<AppCompatTextView>(R.id.volume_tv_cancel)
            val confirmTv = dialog.findViewById<AppCompatTextView>(R.id.volume_tv_confirm)
            imageV.setOnClickListener {
                dialog.dismiss()
            }
            cancelTv.setOnClickListener {
                Toast.makeText(it.context, "Cancel", Toast.LENGTH_SHORT).show()
                dialog.dismiss()
            }
            confirmTv.setOnClickListener {
                Toast.makeText(it.context, "Confirm", Toast.LENGTH_LONG).show()
                dialog.dismiss()
            }

            dialog.show() //展示弹窗
        }

        easyPopTv?.setOnClickListener {
            PickHistorySelectPop.show(it, true) { type ->
                when (type) {
                    0 -> {

                    }
                    1 -> {

                    }
                    3 -> {

                    }
                    else -> {

                    }
                }
            }
        }
        dialogViewLoadingTv?.setOnClickListener {
            ViewLoading.show(this, "Loading", true)
        }

        pictureLoadingTv?.setOnClickListener {
            val picDialog:PictureDialog= PictureDialog(it.context)
            picDialog.show()
        }
    }
}