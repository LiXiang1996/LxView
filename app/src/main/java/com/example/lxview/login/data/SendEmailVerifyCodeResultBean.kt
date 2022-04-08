package com.example.lxview.login.data

data class SendEmailVerifyCodeResultBean(val captchaExpireTime: Long = 0, val resendTime: Long = 0, var signupStatus: String? = null)
