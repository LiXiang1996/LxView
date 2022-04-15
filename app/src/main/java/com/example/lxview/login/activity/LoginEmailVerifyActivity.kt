package com.example.lxview.login.activity

import android.content.Intent
import android.os.Build
import android.text.Html
import android.view.MotionEvent
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import com.example.baselib.utils.ToastUtils
import com.example.lxview.R
import com.example.lxview.base.activity.BaseDataBindActivity
import com.example.lxview.base.ext.throttle
import com.example.lxview.base.utils.KeyboardUtils
import com.example.lxview.base.widget.VerifyEditText
import com.example.lxview.databinding.ActivityLoginVerifyCodeBinding
import com.example.lxview.login.LXConstant
import com.example.lxview.login.LoginVerifyCountDownUtils
import com.example.lxview.base.ext.openActivity

/**
 * author: 李 祥
 * date:   2022/3/7 2:41 下午
 * description:邮箱验证界面
 */
class LoginEmailVerifyActivity : BaseDataBindActivity<ActivityLoginVerifyCodeBinding>(), LoginVerifyCountDownUtils.CountdownListener {
    override val layout: Int
        get() = R.layout.activity_login_verify_code
    private val nextTv: AppCompatTextView by lazy { mBinding.loginEmailVerifyNextTv }
    private val editText: VerifyEditText by lazy { mBinding.loginEmailEtInviteCode }
    private val tipText: AppCompatTextView by lazy { mBinding.loginEmailVerifyTipNoTv }
    private val closeIcon: AppCompatImageView by lazy { mBinding.loginVerifyBackImg }

    private var isRegistered = false //是否注册，false为未注册
    private var email: String = ""
    private var captcha: String = ""
    private var time: Long = 0
    private var enableRetry = true

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onResume() {
        if (LXConstant.expireTime > 0) LoginVerifyCountDownUtils.addObserverTime(LoginEmailVerifyActivity::class.java.toString(), this)
        else {
            val changedString = "<font color='#FEA30F'>${getString(R.string.login_email_tip_countdown_retry)}</font>"
            tipText.text = Html.fromHtml(getString(R.string.login_email_tip_countdown_no) + changedString,Html.FROM_HTML_MODE_COMPACT)
            enableRetry = true
        }
        KeyboardUtils.openKeyBoard(editText,this)
        super.onResume()
    }

    override fun initView() {
        email = intent.getStringExtra(LXConstant.EMAIL).toString()
    }

    override fun initListener() {
        closeIcon.setOnClickListener { finish() }
        nextTv.setOnClickListener(View.OnClickListener {
            isRegistered = intent.getBooleanExtra(LXConstant.IS_REGISTERED, false)
            captcha = editText.content
            if (!isRegistered && email.isNotEmpty() && captcha.length == 6) {  //未注册
                request({
//                    val result: ApiResult<SendEmailVerifyCodeResultBean> = UTownRepo.userApi.checkVerifyCode(SendEmailVerifyCodeBean(captchaType = LXConstant.SIGNUP_EMAIL, identifier = email, captcha))
//                    if (result is ApiResult.Success) {
                        val intent = Intent()
                        intent.putExtra(LXConstant.EMAIL, email)
                        intent.setClass(this@LoginEmailVerifyActivity, LoginSetPasswordActivity::class.java)
                        this@LoginEmailVerifyActivity.startActivity(intent)
//                    } else {
//                        ToastUtils.loginErrorToast(result, this@LoginEmailVerifyActivity){}
//                        editText.editTextList.forEach { it.setBackgroundResource(R.drawable.rgb32363b_stroke03a42_r8_height50) }
//                        ViewShakeUtils.shakeAnimation(2, editText)
//                        editText.content = ""
//                    }
                })
            } else if (isRegistered && email.isNotEmpty() && captcha.length == 6) { //更改密码
//                request({
//                    val result: ApiResult<SendEmailVerifyCodeResultBean> = UTownRepo.userApi.checkVerifyCode(SendEmailVerifyCodeBean(captchaType = LXConstant.FORGET_PASSWORD_EMAIL, identifier = email, captcha))
//                    if (result is ApiResult.Success) {
                        val intent = Intent()
                        intent.putExtra(LXConstant.EMAIL, email)
                        intent.setClass(this@LoginEmailVerifyActivity, LoginChangePasswordActivity::class.java)
                        this@LoginEmailVerifyActivity.startActivity(intent)
//                    } else {
//                        ToastUtils.loginErrorToast(result, this@LoginEmailVerifyActivity){}
//                        editText.editTextList.forEach { it.setBackgroundResource(R.drawable.rgb32363b_stroke03a42_r8_height50) }
//                        ViewShakeUtils.shakeAnimation(2, editText)
//                        editText.content = ""
//                    }
//                })
            } else if (captcha.length != 6) {
                ToastUtils.showToast(this, getString(R.string.login_email_verify_input_error), color = R.color.login_tips_e03a42)
            }
        }.throttle())

        editText.setInputCompleteListener(object : VerifyEditText.inputCompleteListener {
            override fun inputComplete(et: VerifyEditText?, content: String?) {
                nextTv.setBackgroundResource(R.drawable.rgbef7300_r8)
                nextTv.isEnabled = true
            }

            override fun inputNoComplete() {
                nextTv.setBackgroundResource(R.drawable.rgb999999_r8)
                nextTv.isEnabled = false
            }

        })

        tipText.setOnClickListener {
            if (enableRetry) {
//                isRegistered = intent.getBooleanExtra(LXConstant.IS_REGISTERED, false)
//                val types = if (isRegistered) LXConstant.FORGET_PASSWORD_EMAIL else LXConstant.SIGNUP_EMAIL
//                request({
//                    val result: ApiResult<SendEmailVerifyCodeResultBean> = UTownRepo.userApi.sendVerifyCode(SendEmailVerifyCodeBean(captchaType = types, identifier = email))
//                    if (result is ApiResult.Success) {
//                        val resendTime = TimeUtils.timeDifference(result.data.resendTime)
//                        if (resendTime != null) {
//                            LXConstant.expireTime = resendTime
//                            LoginVerifyCountDownUtils.addObserverTime(LoginEmailVerifyActivity::class.java.toString(), this)
//                        }
//                        ToastUtils.showToast(this@LoginEmailVerifyActivity, getString(R.string.login_email_verify_tip_send), color = R.color.teal_200)
//                    } else ToastUtils.loginErrorToast(result, this@LoginEmailVerifyActivity){}
//                })
            }
        }

    }


    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCountdown(remainingTime: Long) {
        time = remainingTime / 1000
        if (time >= 1) {
            enableRetry = false
            tipText.visibility = View.VISIBLE
            tipText.text = String.format(getString(R.string.login_email_tip_countdown), time.toString())
        } else {
            enableRetry = true
//            TimerManager.removeObserver(this, LoginEmailVerifyActivity::class.java.toString())
            val changedString = "<font color='#FEA30F'>${getString(R.string.login_email_tip_countdown_retry)}</font>"
            tipText.text = Html.fromHtml(getString(R.string.login_email_tip_countdown_no) +" "+ changedString,Html.FROM_HTML_MODE_COMPACT)
        }
    }

    override fun onPause() {
//        TimerManager.removeObserver(this, LoginEmailVerifyActivity::class.java.toString())
        super.onPause()
    }


    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when(event?.action){
            MotionEvent.BUTTON_BACK->{
                finish()
                openActivity(LoginWelcomeBackActivity::class.java)}
        }
        return super.onTouchEvent(event)
    }

}

