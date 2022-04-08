package com.example.lxview.base.utils

import java.util.regex.Pattern

object CheckEditFormat {

    fun checkEditInput(string: String?): Boolean {//校验输入字符串格式是否正确
        val pattern = Pattern.compile("[A-Za-z\\d]+([-_.][A-Za-z\\d]+)*@([A-Za-z\\d]+[-.])+[A-Za-z\\d]{2,4}")
        return ((string?.length ?: 0) > 0) && pattern.matcher(string ?: "").matches()
    }
}