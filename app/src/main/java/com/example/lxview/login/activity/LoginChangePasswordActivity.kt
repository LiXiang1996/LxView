package com.example.lxview.login.activity

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import com.example.baselib.utils.ToastUtils
import com.example.lxview.R
import com.example.lxview.base.activity.BaseDataBindActivity
import com.example.lxview.base.ext.throttle
import com.example.lxview.databinding.ActivityLoginChangePasswordBinding
import com.example.lxview.login.UTownConstant
import com.example.lxview.login.utils.ViewShakeUtils


/**
 * author: 李 祥
 * date:   2022/3/7 2:41 下午
 * description:更改密码
 */
class LoginChangePasswordActivity : BaseDataBindActivity<ActivityLoginChangePasswordBinding>() {
    override val layout: Int
        get() = R.layout.activity_login_change_password
    private val changeTv: AppCompatTextView by lazy { mBinding.loginPasswordChangeTv }
    private val inputPasswordEdit: AppCompatEditText by lazy { mBinding.loginChangePasswordInputPasswordEdit }
    private val inputPasswordAgainEdit: AppCompatEditText by lazy { mBinding.loginChangePasswordInputPasswordAgainEdit }
    private val closeIcon: AppCompatImageView by lazy { mBinding.loginEmailBackImg }

    private var isRightInputPasswordEdit = false
    private var isRightInputPasswordAgainEdit = false
    var email: String = ""
    override fun initListener() {
        changeTv.setOnClickListener(View.OnClickListener {
            isRightInputPasswordEdit = ((inputPasswordEdit.text?.length ?: 0) in 8..16)
            isRightInputPasswordAgainEdit = ((inputPasswordAgainEdit.text?.length ?: 0) in 8..16)
            if (!isRightInputPasswordEdit) {
                inputPasswordEdit.setBackgroundResource(R.drawable.rgbe03a42_strokee03a42_r8)
                ToastUtils.show10SpToast(this, getString(R.string.login_password_rule_tip), color = R.color.login_tips_e03a42)
                ViewShakeUtils.shakeAnimation(2, inputPasswordEdit)
            } else if (!isRightInputPasswordAgainEdit) {
                inputPasswordAgainEdit.setBackgroundResource(R.drawable.rgbe03a42_strokee03a42_r8)
                ToastUtils.show10SpToast(this, getString(R.string.login_password_rule_tip), color = R.color.login_tips_e03a42)
                ViewShakeUtils.shakeAnimation(2, inputPasswordAgainEdit)
            } else if (isRightInputPasswordEdit && isRightInputPasswordAgainEdit) {
                if (inputPasswordAgainEdit.text.toString() == inputPasswordEdit.text.toString()) {
                    email = intent.getStringExtra(UTownConstant.EMAIL).toString()
//                    var result: ApiResult<Boolean>?
//                    request({
//                        result = UTownRepo.userApi.changePassword(RegisterBean("EMAIL", email, inputPasswordAgainEdit.text.toString()))
//                        if (result is ApiResult.Success) {
                            showDialog { b ->
                                if (b) {
                                    val intent = Intent()
                                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                                    intent.setClass(this@LoginChangePasswordActivity, LoginWelcomeBackActivity::class.java)
                                    this@LoginChangePasswordActivity.startActivity(intent)
                                }
                            }
//                        } else ToastUtils.loginErrorToast(result, this@LoginChangePasswordActivity){}
//                    })
                } else ToastUtils.showToast(this, getString(R.string.toast_password_no_equals), color = R.color.login_tips_e03a42)
            }
        }.throttle())

        closeIcon.setOnClickListener { finish() }
        inputPasswordEdit.setOnFocusChangeListener { _, b ->
            if (b) {
                inputPasswordEdit.setBackgroundResource(R.drawable.rgb32363b_strokeef7300_r8)
            } else {
                inputPasswordEdit.setBackgroundResource(R.drawable.rgb32363b_r8)
            }
        }
        inputPasswordAgainEdit.setOnFocusChangeListener { _, b ->
            if (b) {
                inputPasswordAgainEdit.setBackgroundResource(R.drawable.rgb32363b_strokeef7300_r8)
            } else {
                inputPasswordAgainEdit.setBackgroundResource(R.drawable.rgb32363b_r8)
            }
        }
    }

    @SuppressLint("InflateParams")
    private fun showDialog(onResult: (Boolean) -> Unit) {
        val dialog = Dialog(this, R.style.dialog_change_success) //设置一些属性
        val localView = LayoutInflater.from(this).inflate(R.layout.dialog_password_changge_success, null) //设置自定义的弹窗UI
        dialog.setContentView(localView)
        val window = dialog.window
        window?.setLayout(-1, -1)
        val localLayoutParams = window?.attributes
        localLayoutParams?.gravity = Gravity.CENTER
        window?.attributes = localLayoutParams

        val confirmTv = dialog.findViewById<AppCompatTextView>(R.id.dialog_change_success_confirm_tv)

        confirmTv.setOnClickListener {
            onResult(true)
            dialog.dismiss()
        }
        dialog.show() //展示弹窗
    }
}