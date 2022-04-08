package com.example.lxview.login.activity

import android.content.Intent
import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import com.example.lxview.R
import com.example.lxview.base.activity.BaseDataBindActivity

import com.example.lxview.base.ext.throttle
import com.example.lxview.base.utils.KeyboardUtils
import com.example.lxview.base.widget.VerifyEditText
import com.example.lxview.databinding.ActivityLoginInviteVerifyCodeBinding

/**
 * author: 李 祥
 * date:   2022/3/7 2:41 下午
 * description:
 */
class LoginInviteCodeActivity : BaseDataBindActivity<ActivityLoginInviteVerifyCodeBinding>() {
    override val layout: Int
        get() = R.layout.activity_login_invite_verify_code
    private val nextTv: AppCompatTextView by lazy { mBinding.loginInviteVerifyNextTv }
    private val editText: VerifyEditText by lazy { mBinding.loginEtInviteCode }
    private val closeIcon: AppCompatImageView by lazy { mBinding.loginVerifyBackImg }
    private var captcha: String = ""

    override fun initView() {
        KeyboardUtils.openKeyBoard(editText, this)
    }

    override fun initListener() {

        nextTv.setOnClickListener(View.OnClickListener {
            captcha = editText.content
//            if (captcha.length == 6) {
//                request({
//                    val result: ApiResult<Token> = UTownRepo.userApi.inviteCode(InviteBean(captcha))
//                    if (result is ApiResult.Success) {
                        val intent = Intent()
                        intent.setClass(this@LoginInviteCodeActivity, LoginSettingUserInfoActivity::class.java)
                        this@LoginInviteCodeActivity.startActivity(intent)
//                    } else {
//                        editText.editTextList.forEach { it.setBackgroundResource(R.drawable.rgb32363b_stroke03a42_r8_height50) }
//                        ViewShakeUtils.shakeAnimation(2, editText)
//                        ToastUtils.loginErrorToast(result, this@LoginInviteCodeActivity) {}
//                        editText.content = ""
//                    }
//                })
//            } else {
//                ToastUtils.showToast(this, getString(R.string.login_email_verify_input_error), color = R.color.login_tips_e03a42)
//            }
        }.throttle())

        closeIcon.setOnClickListener(View.OnClickListener { finish() }.throttle())

        editText.setInputCompleteListener(object : VerifyEditText.inputCompleteListener {
            override fun inputComplete(et: VerifyEditText?, content: String?) {
                nextTv.setBackgroundResource(R.drawable.rgbef7300_r8)
            }

            override fun inputNoComplete() {
            }

        })
    }
}