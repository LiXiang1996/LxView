package com.example.lxview.login.activity

import android.content.Intent
import android.os.Build
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.WindowManager
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import com.example.baselib.utils.ToastUtils
import com.example.lxview.R
import com.example.lxview.base.activity.BaseDataBindActivity

import com.example.lxview.base.ext.throttle
import com.example.lxview.databinding.ActivityLoginRegisterForEmailBinding
import com.example.lxview.login.UTownConstant
import com.example.lxview.login.LoginVerifyCountDownUtils
import com.example.lxview.login.utils.ViewShakeUtils
import java.util.regex.Pattern

/**
 * author: 李 祥
 * date:   2022/3/7 2:41 下午
 * description:
 */
class LoginRegisterActivity : BaseDataBindActivity<ActivityLoginRegisterForEmailBinding>(), LoginVerifyCountDownUtils.CountdownListener {
    override val layout: Int
        get() = R.layout.activity_login_register_for_email
    private val nextTv: AppCompatTextView by lazy { mBinding.loginEmailClickTv }
    private val emailEdit: AppCompatEditText by lazy { mBinding.loginEmailEdit }
    private val closeIcon: AppCompatImageView by lazy { mBinding.loginEmailBackImg }
    private var isRight = false
    private var time: Long = 0

    override fun onResume() {
        if (UTownConstant.expireTime > 0) {
            LoginVerifyCountDownUtils.addObserverTime(LoginRegisterActivity::class.java.toString(), this)
        } else {
            nextTv.text = getString(R.string.email_verify_code)
        }
        super.onResume()
    }

    override fun initView() {
    }

    override fun initListener() {

        emailEdit.setOnFocusChangeListener { view, b ->
            if (b) {
                view.setBackgroundResource(R.drawable.rgb32363b_strokeef7300_r8)
            } else {
                view.setBackgroundResource(R.drawable.rgb32363b_r8)
            }
        }
        emailEdit.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
                val pattern = Pattern.compile("[A-Za-z\\d]+([-_.][A-Za-z\\d]+)*@([A-Za-z\\d]+[-.])+[A-Za-z\\d]{2,4}")
                isRight = ((emailEdit.text?.length ?: 0) > 0) && pattern.matcher(emailEdit.text ?: "").matches()
                changeInputState(isRight, nextTv)
            }

        })

        closeIcon.setOnClickListener {
            finish()
        }
        nextTv.setOnClickListener(View.OnClickListener {
            val pattern = Pattern.compile("[A-Za-z\\d]+([-_.][A-Za-z\\d]+)*@([A-Za-z\\d]+[-.])+[A-Za-z\\d]{2,4}")
            isRight = ((emailEdit.text?.length ?: 0) > 0) && pattern.matcher(emailEdit.text ?: "").matches()
            if (isRight) {
                val email = emailEdit.text.toString().trim()
                //                request( { //                    val result: ApiResult<SendEmailVerifyCodeResultBean> = UTownRepo.userApi.sendVerifyCode(SendEmailVerifyCodeBean(captchaType = UTownConstant.SIGNUP_EMAIL, identifier = email))
                //                    if (result is ApiResult.Success) {
                //                        val resendTime = TimeUtils.timeDifference(result.data.resendTime)
                //                        if (resendTime != null) { UTownConstant.expireTime = resendTime }
                val intent = Intent()
                UTownConstant.expireTime = 60000
                intent.putExtra(UTownConstant.IS_REGISTERED, false)
                intent.putExtra(UTownConstant.SIGNUP_EMAIL, true)
                intent.putExtra(UTownConstant.EMAIL, email)
                intent.setClass(this@LoginRegisterActivity, LoginEmailVerifyActivity::class.java) //
                this@LoginRegisterActivity.startActivity(intent)
            //                    } else {
                //                        ViewShakeUtils.shakeAnimation(2, emailEdit)
                ////                        ToastUtils.loginErrorToast(result, this@LoginRegisterActivity){}
                //                    }
                //                })
            } else {
                emailEdit.setBackgroundResource(R.drawable.rgbe03a42_strokee03a42_r8)
                ViewShakeUtils.shakeAnimation(2, emailEdit)
                ToastUtils.showToast(this, getString(R.string.login_email_format_error), color = R.color.login_tips_e03a42)
            }
        }.throttle())
    }

    private fun changeInputState(isRight: Boolean, view: View?) {
        if (!isRight) {
            view?.setBackgroundResource(R.drawable.rgb999999_r8)
        } else view?.setBackgroundResource(R.drawable.rgbef7300_r8)
    }


    override fun onCountdown(remainingTime: Long) {
        time = remainingTime / 1000
        if (time >= 1) {
            nextTv.text = String.format(getString(R.string.email_verify_code_countdown), time.toString())
        } else { //            TimerManager.removeObserver(this, LoginRegisterActivity::class.java.toString())
            nextTv.text = getString(R.string.email_verify_code)
            if (isRight) nextTv.setBackgroundResource(R.drawable.rgbef7300_r8)
        }
    }

    override fun onPause() { //        TimerManager.removeObserver(this, LoginRegisterActivity::class.java.toString())
        super.onPause()
    }
}