package com.example.lxview.base.utils

import android.util.Base64
import java.nio.charset.Charset
import java.nio.charset.StandardCharsets
import java.security.MessageDigest

object DigestUtils {

    fun decodeBase64(str:String,flag:Int):String{
        val decodeBytes = Base64.decode(str,flag)
        return String(decodeBytes, StandardCharsets.UTF_8)
    }

    fun decodeBase64(str:String):String{
        return decodeBase64(str,Base64.DEFAULT)
    }

    fun md5(str: String): String {
        val digest = MessageDigest.getInstance("MD5")
        val result = digest.digest(str.toByteArray())//对指定的字节数组进行hash计算
        //没转16进制之前是16位
        println("result${result.size}")
        //转成16进制后是32字节
        return toHex(result)
    }

    private fun toHex(byteArray: ByteArray): String {
        val result = with(StringBuilder()) {
            byteArray.forEach {
                val hex = it.toInt() and (0xFF)
                val hexStr = Integer.toHexString(hex)   //此方法的返回类型为String ，它返回给定参数的十六进制字符串
                if (hexStr.length == 1) {
                    this.append("0").append(hexStr)
                } else {
                    this.append(hexStr)
                }
            }
            this.toString()
        }
        //转成16进制后是32字节
        return result
    }

    /**
     * 加密算法比较：https://blog.csdn.net/chenze666/article/details/79730753?ops_request_misc=%257B%2522request%255Fid%2522%253A%2522164903891616780274192382%2522%252C%2522scm%2522%253A%252220140713.130102334.pc%255Fall.%2522%257D&request_id=164903891616780274192382&biz_id=0&utm_medium=distribute.pc_search_result.none-task-blog-2~all~first_rank_ecpm_v1~rank_v31_ecpm-1-79730753.142^v5^pc_search_result_cache,157^v4^control&utm_term=sha1%E5%92%8Csha256%E7%9A%84%E5%8C%BA%E5%88%AB&spm=1018.2226.3001.4187
     */

    fun sha1(str:String): String {
        val digest = MessageDigest.getInstance("SHA-1")
        val result = digest.digest(str.toByteArray())
        return toHex(result)
    }

    fun sha256(str:String): String {
        val digest = MessageDigest.getInstance("SHA-256")
        val result = digest.digest(str.toByteArray())
        return toHex(result)
    }
}