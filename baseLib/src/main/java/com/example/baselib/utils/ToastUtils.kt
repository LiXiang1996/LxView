package com.example.baselib.utils

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.baselib.R
import kotlin.math.roundToInt


/**
 * author: lx
 * Description: toast工具类
 */
object ToastUtils {


    /**
     * 显示账户提示
     */
    fun showAccountToast(context: Context, text: String) {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.common_account_toast, null)
        val accountToastTx = view.findViewById<TextView>(R.id.accountToastTx)
        accountToastTx.text = text
        val toast = Toast(context.applicationContext)
        toast.duration = Toast.LENGTH_SHORT
        toast.setGravity(Gravity.CENTER, 0, 0)
        toast.view = view
        toast.show()
    }

    fun showToast(context: Context, text: String, gravity: Int = Gravity.CENTER) {
        val inflater = LayoutInflater.from(context.applicationContext)
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

    @SuppressLint("InflateParams")
    fun showToast(context: Context, text: String, gravity: Int = Gravity.CENTER, color: Int = R.color.white) {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.common_account_toast, null)
        val accountToastTx = view.findViewById<TextView>(R.id.accountToastTx)
        accountToastTx.text = text
        accountToastTx.setTextColor(context.resources.getColor(color))
        toast = Toast(context.applicationContext)
        toast?.duration = Toast.LENGTH_SHORT
        val offsetY = if (gravity == Gravity.BOTTOM) (80f * context.resources.displayMetrics.density).roundToInt() else 0
        toast?.setGravity(gravity, 0, offsetY)
        toast?.view = view
        toast?.show()
    }


    private var toast: Toast? = null


    @SuppressLint("InflateParams")
    fun show10SpToast(context: Context, text: String, gravity: Int = Gravity.CENTER, color: Int = R.color.white) {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.common_account_toast, null)
        val accountToastTx = view.findViewById<TextView>(R.id.accountToastTx)
        accountToastTx.text = text
        accountToastTx.textSize = 10F
        accountToastTx.setTextColor(context.resources.getColor(color))
        toast = Toast(context.applicationContext)
        toast?.duration = Toast.LENGTH_SHORT
        val offsetY = if (gravity == Gravity.BOTTOM) (80f * context.resources.displayMetrics.density).roundToInt() else 0
        toast?.setGravity(gravity, 0, offsetY)
        toast?.view = view
        toast?.show()
    }

//    fun loginErrorToast(result: ApiResult<Any>?, context: Context, callback: (code: Int?) -> Unit) =
        /**
         * 登录相关错误码
        12001 用户不存在
        12002 登录密码错误
        12003 头像不存在
        12010 该电子邮箱已注册
        12011 该电子邮箱未注册
        12012 验证码错误
        12013 验证码未过期
        12014 验证码已失效，请重新获取
        12015 邀请码无效
        12016 该电子邮箱已注册，请直接登录
         */
//        when (result) {
//            is ApiResult.ApiError -> {
//                showToast(context, "${result.msg}", color = R.color.login_tips_e03a42)
//                callback(result.code)
//            }
//            is ApiResult.NetworkError -> {
//                showToast(context, context.getString(R.string.network_error), color = R.color.login_tips_e03a42)
//            }
//            is ApiResult.UnKnowError -> {
//                showToast(context, context.getString(R.string.unknown_error), color = R.color.login_tips_e03a42)
//            }
//            else -> {
//
//            }
//        }

}

