package com.example.lxview.base.ext

import com.example.lxview.base.utils.DigestUtils


fun String.md5():String{
    return DigestUtils.md5(this)
}

fun String.sha1():String{
    return DigestUtils.sha1(this)
}

fun String.sha256():String{
    return DigestUtils.sha256(this)
}

fun String.decodeBase64(flag:Int):String{
    return DigestUtils.decodeBase64(this,flag)
}

fun String.decodeBase64():String{
    return DigestUtils.decodeBase64(this)
}