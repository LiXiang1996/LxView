package com.example.lxview.login

/**
 * author: 李 祥
 * date:   2022/3/7 5:31 下午
 * description:UTown 常量
 */
class UTownConstant {

    companion object{
        const val IS_REGISTERED ="isRegistered" //true已经注册
        const val EMAIL ="email"
        const val INTRODUCTION ="introduction"
        const val MALE ="MALE"
        const val FEMALE ="FEMALE"
        const val OTHER ="OTHER"
        const val UN_KNOWN ="UN_KNOWN"
        const val SIGNUP_EMAIL ="SIGNUP_EMAIL"
        const val SIGNUP_STATUS ="signup_status"
        const val FORGET_PASSWORD_EMAIL ="FORGET_PASSWORD_EMAIL"
        const val SIGNUP_STATUS_SET_PASSWORD ="SET_PASSWORD"
        const val SIGNUP_STATUS_ACCEPT_INVITE ="ACCEPT_INVITE"
        const val SIGNUP_STATUS_USER_INFO ="USER_INFO"
        const val SIGNUP_STATUS_FACE_DATA ="FACE_DATA"
        const val SIGNUP_STATUS_FINISHED ="FINISHED"
        var expireTime :Long = 0
    }
}