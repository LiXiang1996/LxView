package com.example.lxview.login.activity

import android.content.Intent
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import com.example.baselib.utils.ToastUtils
import com.example.lxview.R
import com.example.lxview.base.activity.BaseDataBindActivity

import com.example.lxview.base.ext.throttle
import com.example.lxview.databinding.ActivityLoginInputPasswordBinding
import com.example.lxview.login.LXConstant
import com.example.lxview.login.utils.ViewShakeUtils


/**
 * author: 李 祥
 * date:   2022/3/7 2:41 下午
 * description:
 */
class LoginSetPasswordActivity : BaseDataBindActivity<ActivityLoginInputPasswordBinding>() {
    override val layout: Int
        get() = R.layout.activity_login_input_password
    private val nextTv: AppCompatTextView by lazy { mBinding.loginPasswordNextTv }
    private val passwordErrorTip: AppCompatTextView by lazy { mBinding.loginPasswordRuleTipTv }
    private val inputPasswordEdit: AppCompatEditText by lazy { mBinding.loginPasswordEdit }
    private val inputPasswordAgainEdit: AppCompatEditText by lazy { mBinding.loginPasswordAgainEdit }
    private val closeIcon: AppCompatImageView by lazy { mBinding.loginEmailBackImg }

    var email: String = ""
    private var isEmailEmpty = true
    private var isPasswordEmpty = true

    override fun initListener() {
        nextTv.setOnClickListener (View.OnClickListener {
            val checkInputLength = inputPasswordEdit.text?.length ?: 0
            val checkInputAgainLength = inputPasswordAgainEdit.text?.length ?: 0
            val consistencyResult = inputPasswordEdit.text.toString().trim() == inputPasswordAgainEdit.text.toString().trim()

            if (checkInputLength !in 8..16) {
                inputPasswordEdit.setBackgroundResource(R.drawable.rgbe03a42_strokee03a42_r8)
                ToastUtils.show10SpToast(this, getString(R.string.login_password_rule_tip), color = R.color.login_tips_e03a42)
                ViewShakeUtils.shakeAnimation(2, inputPasswordEdit)
            } else if (checkInputAgainLength !in 8..16) {
                inputPasswordAgainEdit.setBackgroundResource(R.drawable.rgbe03a42_strokee03a42_r8)
                ToastUtils.show10SpToast(this, getString(R.string.login_password_rule_tip), color = R.color.login_tips_e03a42)
                ViewShakeUtils.shakeAnimation(2, inputPasswordAgainEdit)
            } else if (!consistencyResult) {
                inputPasswordAgainEdit.setBackgroundResource(R.drawable.rgbe03a42_strokee03a42_r8)
                ToastUtils.show10SpToast(this, getString(R.string.toast_password_no_equals), color = R.color.login_tips_e03a42)
                ViewShakeUtils.shakeAnimation(2, inputPasswordAgainEdit)
            } else if (consistencyResult) {
                email = intent.getStringExtra(LXConstant.EMAIL).toString()
//                var result: ApiResult<User>?
//                request( {
//                    result = UserCenter.getInstance(this@LoginSetPasswordActivity).reg(email, inputPasswordAgainEdit.text.toString())
//                    if (result is ApiResult.Success) {
                        val intent = Intent()
                        intent.setClass(this@LoginSetPasswordActivity, LoginInviteCodeActivity::class.java)
                        this@LoginSetPasswordActivity.startActivity(intent)
//                    } else ToastUtils.loginErrorToast(result, this@LoginSetPasswordActivity){}
//                })
            }
        }.throttle())
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

        inputPasswordEdit.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (!p0.isNullOrEmpty()) {
                    isPasswordEmpty = false
                }
            }

            override fun afterTextChanged(p0: Editable?) {
                nextTv.isEnabled = !isEmailEmpty && !isPasswordEmpty
            }
        })
        inputPasswordAgainEdit.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (!p0.isNullOrEmpty()) {
                    isEmailEmpty = false
                }
            }

            override fun afterTextChanged(p0: Editable?) {
                nextTv.isEnabled = !isEmailEmpty && !isPasswordEmpty
            }
        })

        closeIcon.setOnClickListener { finish() }

    }

    private fun changeInputState(isRight: Boolean, view: View?) {
        if (!isRight) {
            view?.setBackgroundResource(R.drawable.rgbe03a42_strokee03a42_r8)
            passwordErrorTip.visibility = View.VISIBLE
        } else {
            view?.setBackgroundResource(R.drawable.rgb32363b_strokeef7300_r8)
            passwordErrorTip.visibility = View.GONE
        }
    }
}