package com.example.lxview.login.activity

import android.content.Intent
import android.text.Editable
import android.text.TextWatcher
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import com.example.baselib.utils.ToastUtils
import com.example.lxview.R
import com.example.lxview.base.activity.BaseDataBindActivity

import com.example.lxview.base.ext.throttle
import com.example.lxview.base.utils.CheckEditFormat
import com.example.lxview.databinding.ActivityLoginAlreadyRegBinding

import com.example.lxview.login.utils.ViewShakeUtils
import com.example.lxview.base.ext.openActivity
import com.example.lxview.home.MainActivity
import com.example.lxview.login.LXConstant

/**
 * @author: lixiang
 * @date: 2022/3/7
 * @description:
 */
class LoginWelcomeBackActivity : BaseDataBindActivity<ActivityLoginAlreadyRegBinding>() {
    override val layout: Int = R.layout.activity_login_already_reg
    private val forgetTv: AppCompatTextView by lazy { mBinding.loginForgetPassword }
    private val emailEdit: AppCompatEditText by lazy { mBinding.loginInputEmailEdit }
    private val passwordEdit: AppCompatEditText by lazy { mBinding.loginInputPasswordEdit }
    private val loginTv: AppCompatTextView by lazy { mBinding.loginPasswordNextTv }
    private val closeIcon: AppCompatImageView by lazy { mBinding.loginEmailBackImg }
    private var isRight = false
    private var isEmailEmpty = true
    private var isPasswordEmpty = true


    override fun initListener() {

        forgetTv.setOnClickListener(View.OnClickListener {
            val email: String = emailEdit.text.toString().trim()
            if (emailEdit.text?.length == 0) {
                emailEdit.setBackgroundResource(R.drawable.rgbe03a42_strokee03a42_r8)
                ToastUtils.showToast(this, getString(R.string.login_email_empty), color = R.color.login_tips_e03a42)
                ViewShakeUtils.shakeAnimation(2, emailEdit)
            } else if (!isRight) {
                emailEdit.setBackgroundResource(R.drawable.rgbe03a42_strokee03a42_r8)
                ToastUtils.showToast(this, getString(R.string.login_email_format_error), color = R.color.login_tips_e03a42)

                ViewShakeUtils.shakeAnimation(2, emailEdit)
            } else {
//                request({
//                    val result: ApiResult<SendEmailVerifyCodeResultBean> = UTownRepo.userApi.sendVerifyCode(SendEmailVerifyCodeBean(captchaType = LXConstant.FORGET_PASSWORD_EMAIL, identifier = email))
//                    if (result is ApiResult.Success) {
//                        val a = result.data.resendTime
//                        LXConstant.expireTime = TimeUtils.timeDifference(a) ?: 0
//                        val intent = Intent()
                        intent.putExtra(LXConstant.IS_REGISTERED, true)
                        intent.putExtra(LXConstant.FORGET_PASSWORD_EMAIL, true)
                        intent.putExtra(LXConstant.EMAIL, email)
                        intent.setClass(this@LoginWelcomeBackActivity, LoginEmailVerifyActivity::class.java)
                        this@LoginWelcomeBackActivity.startActivity(intent)
//                    } else {
//                        ToastUtils.loginErrorToast(result, this@LoginWelcomeBackActivity) {
//                            when (it) {
//                                12001, 12011 -> {
//                                    ViewShakeUtils.shakeAnimation(2, emailEdit)
//                                    emailEdit.setBackgroundResource(R.drawable.rgbe03a42_strokee03a42_r8)
//                                }
//                                12002 -> {
//                                    ViewShakeUtils.shakeAnimation(2, passwordEdit)
//                                    passwordEdit.setBackgroundResource(R.drawable.rgbe03a42_strokee03a42_r8)
//                                }
//                            }
//                        }
//                    }
//                })
            }
        }.throttle())
        closeIcon.setOnClickListener {
            finish()
            openActivity(LoginActivity::class.java)
        }

        passwordEdit.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (!p0.isNullOrEmpty()) {
                    isPasswordEmpty = false
                }
            }

            override fun afterTextChanged(p0: Editable?) {
                loginTv.isEnabled = !isEmailEmpty && !isPasswordEmpty
            }
        })
        emailEdit.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (!p0.isNullOrEmpty()) {
                    isEmailEmpty = false
                }
            }

            override fun afterTextChanged(p0: Editable?) {
                isRight = CheckEditFormat.checkEditInput(p0.toString())
                loginTv.isEnabled = !isEmailEmpty && !isPasswordEmpty
            }
        })

        passwordEdit.setOnFocusChangeListener { view, b ->
            if (b) {
                view.setBackgroundResource(R.drawable.rgb32363b_strokeef7300_r8)
            } else {
                view.setBackgroundResource(R.drawable.rgb32363b_r8)
            }
        }
        emailEdit.setOnFocusChangeListener { view, b ->
            if (b) {
                view.setBackgroundResource(R.drawable.rgb32363b_strokeef7300_r8)
            } else {
                view.setBackgroundResource(R.drawable.rgb32363b_r8)
            }
        }


        loginTv.setOnClickListener {
            val email: String = emailEdit.text.toString()
            if (emailEdit.text?.length == 0) {
                emailEdit.setBackgroundResource(R.drawable.rgbe03a42_strokee03a42_r8)
                ToastUtils.showToast(this, getString(R.string.login_email_empty), color = R.color.login_tips_e03a42)
                ViewShakeUtils.shakeAnimation(2, emailEdit)
            } else if (!isRight) {
                emailEdit.setBackgroundResource(R.drawable.rgbe03a42_strokee03a42_r8)
                ToastUtils.showToast(this, getString(R.string.login_email_format_error), color = R.color.login_tips_e03a42)
                ViewShakeUtils.shakeAnimation(2, emailEdit)
            } else if (!isPasswordEmpty && !isEmailEmpty) {
                val intent = Intent()
                intent.setClass(this, MainActivity::class.java) //
                this.startActivity(intent)
            }
        }

    }

//    private fun goSignStatusJump(result: ApiResult.Success<User>) {
//        when (result.data.profile.signupStatus) {
//            LXConstant.SIGNUP_STATUS_ACCEPT_INVITE -> {
//                val intent = Intent()
//                intent.setClass(this, LoginInviteCodeActivity::class.java)
//                this.startActivity(intent)
//            }
//            LXConstant.SIGNUP_STATUS_USER_INFO -> {
//                val intent = Intent()
//                intent.setClass(this, LoginSettingUserInfoActivity::class.java)
//                this.startActivity(intent)
//            }
//            LXConstant.SIGNUP_STATUS_FACE_DATA -> {
//                openUnityWithScene(SceneModel.SceneEmptyWithAreaName(SceneModel.SceneEmptyAreaFace))
//            }
//            LXConstant.SIGNUP_STATUS_FINISHED -> {
//                openUnityWithScene(SceneModel.SceneWorld)
//            }
//        }
//    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event?.action) {
            MotionEvent.BUTTON_BACK ->{
                finish()
                openActivity(LoginActivity::class.java)
            }
        }
        return super.onTouchEvent(event)
    }
}