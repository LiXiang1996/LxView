package com.sanhe.baselibrary.utils

import android.graphics.Color
import android.graphics.Typeface
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan


/**
 * author: lx
 * Date: 2018/11/19 17:11
 * Description: 处理字符串工具类，用来处理字符串特定位置的颜色，字体变化
 */
object TextStyleUtils {


    /**
     * 将节点文字该变成h灰色
     */
    fun textColorToBlack(original: String, node: String): SpannableString {
        val spannableString = SpannableString(original)
        if (original.contains(node)) {
            spannableString.setSpan(ForegroundColorSpan(Color.parseColor("#626262")), spannableString.indexOf(node),
                    spannableString.indexOf(node) + node.length, 0)
        }
        return spannableString
    }


    /**
     * 将节点文字该变成黄色
     */
    fun textColorToF8E71C(original: String, node: String): SpannableString {
        val spannableString = SpannableString(original)
        if (original.contains(node)) {
            spannableString.setSpan(ForegroundColorSpan(Color.parseColor("#f8e71c")), spannableString.indexOf(node),
                    spannableString.indexOf(node) + node.length, 0)
        }
        return spannableString
    }


    /**
     * 转换成红色
     */
    fun textColorToF30E0E(original: String, node: String): SpannableString {
        val spannableString = SpannableString(original)
        if (original.contains(node)) {
            spannableString.setSpan(ForegroundColorSpan(Color.parseColor("#F30E0E")), spannableString.indexOf(node),
                    spannableString.indexOf(node) + node.length, 0)
        }
        return spannableString
    }

    /**
     * 转换成橙色
     */
    fun textColorToFEA30F(original: String, node: String): SpannableString {
        val spannableString = SpannableString(original)
        if (original.contains(node)) {
            spannableString.setSpan(ForegroundColorSpan(Color.parseColor("#FEA30F")), spannableString.indexOf(node),
                    spannableString.indexOf(node) + node.length, 0)
        }
        return spannableString
    }


    /**
     * 加粗黄色
     */
    fun textBoldColorToYellow(original: String, color: String, bold: String): SpannableString {
        val spannableString = SpannableString(original)
        if (original.contains(color)) {
            spannableString.setSpan(ForegroundColorSpan(Color.parseColor("#FCB714")), spannableString.indexOf(color),
                    spannableString.indexOf(color) + color.length, 0)
        }
        if (original.contains(bold)) {
            spannableString.setSpan(StyleSpan(Typeface.BOLD), spannableString.indexOf(bold),
                    spannableString.indexOf(bold) + bold.length, 0)
        }

        return spannableString
    }

    /**
     * 节点变黄色
     */
    fun textBoldColorTof8e71c(original: String, colorList: MutableList<String>): SpannableString {
        val spannableString = SpannableString(original)
        colorList.forEach {
            if (original.contains(it)) {
                spannableString.setSpan(ForegroundColorSpan(Color.parseColor("#f8e71c")), spannableString.indexOf(it),
                        spannableString.indexOf(it) + it.length, 0)
            }
        }
        return spannableString
    }

    /**
     * 节点变黑色
     */
    fun textBoldColorTo333333(original: String, colorList: MutableList<String>): SpannableString {
        val spannableString = SpannableString(original)
        colorList.forEach {
            if (original.contains(it)) {
                spannableString.setSpan(ForegroundColorSpan(Color.parseColor("#333333")), spannableString.indexOf(it),
                        spannableString.indexOf(it) + it.length, 0)
            }
        }
        return spannableString
    }

}