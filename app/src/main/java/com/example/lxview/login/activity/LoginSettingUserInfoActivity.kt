package com.example.lxview.login.activity

import android.content.Intent
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.TextView
import com.example.lxview.R
import com.example.lxview.base.activity.BaseDataBindActivity

import com.example.lxview.base.ext.throttle
import com.example.lxview.databinding.ActivitySettingUserinfoBinding
import com.example.lxview.home.MainActivity
import com.example.lxview.login.UTownConstant
import com.example.lxview.login.data.RegisterUserInfoBean


/**
 * @author: JayQiu
 * @date: 2022/2/14
 * @description:
 */
class LoginSettingUserInfoActivity : BaseDataBindActivity<ActivitySettingUserinfoBinding>() {
    private val mTvNext: TextView by lazy { findViewById(R.id.login_setting_userinfo_tv_next) }
    private val mNickname: EditText by lazy { findViewById(R.id.login_setting_userinfo_nickname_et) }
    private val mRadioGroupSex: RadioGroup by lazy { findViewById(R.id.login_user_gender_rg_men) }
    private var selectSex = UTownConstant.MALE
    override val layout: Int = R.layout.activity_setting_userinfo
    var isNicknameEmpty = true

    override fun initListener() {

        mNickname.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (!p0.isNullOrEmpty()) {
                    isNicknameEmpty = false
                }
            }

            override fun afterTextChanged(p0: Editable?) {
                mTvNext.isEnabled = !isNicknameEmpty
            }

        })
        mTvNext.setOnClickListener(View.OnClickListener {
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            intent.setClass(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }.throttle())
        mRadioGroupSex.setOnCheckedChangeListener { _, id ->
            selectSex = when (id) {
                R.id.login_user_gender_rb_men -> UTownConstant.MALE
                R.id.login_user_gender_rb_women -> UTownConstant.FEMALE
                R.id.login_user_gender_rb_other -> UTownConstant.OTHER
                else -> UTownConstant.MALE
            }
        }
    }
}