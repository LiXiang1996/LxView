package com.example.lxview.login.activity

import android.content.Intent
import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import com.example.lxview.R
import com.example.lxview.base.activity.BaseDataBindActivity
import com.example.lxview.base.ext.throttle
import com.example.lxview.databinding.ActivityLoginBinding
import com.example.lxview.login.UTownConstant


/**
 * @author: JayQiu
 * @date: 2022/2/14
 * @description:
 */
class LoginActivity : BaseDataBindActivity<ActivityLoginBinding>() {
    override val layout: Int = R.layout.activity_login
    private val loginTv: AppCompatTextView by lazy { mBinding.loginCountTv }
    private val registerTv: AppCompatTextView by lazy { mBinding.loginCreateCountTv }
    private val meetingTv: AppCompatTextView by lazy { mBinding.loginJoinMeetingTv }
    private val policyTV: AppCompatTextView by lazy { mBinding.loginPrivatePolicyTv }
    override fun initListener() {
        loginTv.setOnClickListener(View.OnClickListener {
            this.startActivity(Intent(this, LoginWelcomeBackActivity::class.java))
        }.throttle())
        registerTv.setOnClickListener(View.OnClickListener {
            UTownConstant.expireTime = 0
            this.startActivity(Intent(this, LoginRegisterActivity::class.java))
        }.throttle())

        meetingTv.setOnClickListener(View.OnClickListener {
        }.throttle())

        policyTV.setOnClickListener(View.OnClickListener {
        }.throttle())

    }
}