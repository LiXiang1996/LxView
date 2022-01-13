package com.example.baselib.utils

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import com.example.baselib.R
import com.example.baselib.common.BaseApplication

import kotlin.math.roundToInt


/**
 * author: lx
 * Description: toast工具类
 */
object ToastUtils {

    /**
     * 收藏和取消收藏的提示toast
     */
//    fun showMoreToast(context: Context, pic: Int, text: String) {
//        val inflater = LayoutInflater.from(BaseApplication.context)
//        val view = inflater.inflate(R.layout.common_more_toast_show_layout, null)
//        val mCommonMoreToastShowPic = view.findViewById<AppCompatImageView>(R.id.mCommonMoreToastShowPic)
//        val mCommonMoreToastShowTitle = view.findViewById<TextView>(R.id.mCommonMoreToastShowTitle)
//        mCommonMoreToastShowTitle.text = text
//        mCommonMoreToastShowPic.setImageResource(pic)
//        val toast = Toast(context.applicationContext)
//        toast.duration = Toast.LENGTH_SHORT
//        toast.setGravity(Gravity.CENTER, 0, 0)
//        toast.view = view
//        toast.show()
//    }


    /**
     * 金币排行提现toast
     */
    @SuppressLint("SetTextI18n")
//    fun showIntegralWallToast(context: Context, amount: Int) {
//        val inflater = LayoutInflater.from(BaseApplication.context)
//        val view = inflater.inflate(R.layout.common_integral_wall_toast_show_layout, null)
//        val mIntegralWallToastShowPic = view.findViewById<AppCompatImageView>(R.id.mIntegralWallToastShowPic)
//        val mIntegralWallToastShowTitle = view.findViewById<AppCompatTextView>(R.id.mIntegralWallToastShowTitle)
//        mIntegralWallToastShowTitle.text = "+ $amount clapcoins"
//        mIntegralWallToastShowPic.setImageResource(R.mipmap.ic_user_wallet_cc_money_logo)
//        val toast = Toast(context.applicationContext)
//        toast.duration = Toast.LENGTH_SHORT
//        toast.setGravity(Gravity.CENTER, 0, 0)
//        toast.view = view
//        toast.show()
//    }


    /**
     * 这里显示提示框
     */
//    fun showPromptBox(context: Activity, msg: String) {
//        WarningManager.create(context).setText(msg).setBackgroundColor(R.color.color_ff3f5a).show()
//    }

    /**
     * 显示账户提示
     */
    fun showAccountToast(context: Context, text: String) {
        val inflater = LayoutInflater.from(BaseApplication.context)
        val view = inflater.inflate(R.layout.common_account_toast, null)
        val accountToastTx = view.findViewById<TextView>(R.id.accountToastTx)
        accountToastTx.text = text
        val toast = Toast(context.applicationContext)
        toast.duration = Toast.LENGTH_SHORT
        toast.setGravity(Gravity.CENTER, 0, 0)
        toast.view = view
        toast.show()
    }

    fun showToast(context: Context, text: String, gravity: Int) {
        val inflater = LayoutInflater.from(BaseApplication.context)
        val view = inflater.inflate(R.layout.common_account_toast, null)
        val accountToastTx = view.findViewById<TextView>(R.id.accountToastTx)
        accountToastTx.text = text
        val toast = Toast(context.applicationContext)
        toast.duration = Toast.LENGTH_SHORT
        val offsetY = if (gravity == Gravity.BOTTOM) (80f * context.resources.displayMetrics.density).roundToInt() else 0
        toast.setGravity(gravity, 0, offsetY)
        toast.view = view
        toast.show()
    }

    /**
     * 显示账户提示
     */
    fun showAccountToast(context: Context, resId: Int) {
        showAccountToast(context, context.getString(resId))
    }

    /**
     * 宝箱奖励领取Toast
     */
//    fun showChestRewardToast(context: Context, pic: Int, text: CharSequence) {
//        val inflater = LayoutInflater.from(BaseApplication.context)
//        val view = inflater.inflate(R.layout.common_chest_reward_toast_layout, null)
//        val mCommonMoreToastShowPic = view.findViewById<AppCompatImageView>(R.id.mCommonMoreToastShowPic)
//        val mCommonMoreToastShowTitle = view.findViewById<TextView>(R.id.mCommonMoreToastShowTitle)
//        mCommonMoreToastShowTitle.text = text
//        mCommonMoreToastShowPic.setImageResource(pic)
//        val toast = Toast(context.applicationContext)
//        toast.duration = Toast.LENGTH_SHORT
//        toast.view = view
//        toast.setGravity(Gravity.BOTTOM, 0, (80f * context.resources.displayMetrics.density).roundToInt())
//        toast.show()
//    }

    /**
     * Vote页奖励领取Toast
     */
//    fun showVoteRewardToast(context: Context, pic: Int, text: CharSequence) {
//        val inflater = LayoutInflater.from(BaseApplication.context)
//        val view = inflater.inflate(R.layout.common_vote_reward_toast_layout, null)
//        val mCommonMoreToastShowPic = view.findViewById<AppCompatImageView>(R.id.mCommonMoreToastShowPic)
//        val mCommonMoreToastShowTitle = view.findViewById<TextView>(R.id.mCommonMoreToastShowTitle)
//        mCommonMoreToastShowTitle.text = text
//        mCommonMoreToastShowPic.setImageResource(pic)
//        val toast = Toast(context.applicationContext)
//        toast.duration = Toast.LENGTH_SHORT
//        //        toast.setGravity(Gravity.CENTER, 0, 0)
//        toast.view = view
//        toast.show()
//    }

}