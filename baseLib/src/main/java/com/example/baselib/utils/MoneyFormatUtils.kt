package com.example.baselib.utils

import java.math.RoundingMode
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*

/**
 * author: lx
 * Date: 2018/12/7 13:09
 * Description: 金币展示格式化工具类，用于折线图上的展示
 */
object MoneyFormatUtils {

    /**
     * 这里防止数据四舍五入，以及转换过程中存在失真的问题。这里先地位取值，高位截取
     */
    fun getCashFormatPoint2(price: Float): String {
        val enLocale = Locale("en", "US")
        val decimalFormat: DecimalFormat = NumberFormat.getNumberInstance(enLocale) as DecimalFormat
        decimalFormat.applyPattern("0.00")
        return decimalFormat.format(price)
    }


    /**
     * 向下取整
     */
    fun getCashFormatPoint3(price: Float): String {
        val enLocale = Locale("en", "US")
        val decimalFormat: DecimalFormat = NumberFormat.getNumberInstance(enLocale) as DecimalFormat
        decimalFormat.roundingMode = RoundingMode.DOWN
        decimalFormat.applyPattern("0.00")
        return decimalFormat.format(price)
    }

    fun getCashFormatPoint4(price: Float): String {
        val enLocale = Locale("en", "US")
        val decimalFormat: DecimalFormat = NumberFormat.getNumberInstance(enLocale) as DecimalFormat
        decimalFormat.roundingMode = RoundingMode.DOWN
        decimalFormat.applyPattern("0")
        return decimalFormat.format(price)
    }


    /**
     * 截取小数点后一位，这里来做小数点展示
     * <p> 结果返回为小数点后一位
     */
    fun getCashFormatPointOne(price: Float): String {
        val enLocale = Locale("en", "US")
        val decimalFormat: DecimalFormat = NumberFormat.getNumberInstance(enLocale) as DecimalFormat
        decimalFormat.applyPattern("0.0")
        return decimalFormat.format(price)
    }

    /**
     * 针对游戏展示，结果为小数点后一位。拼接0
     */
    fun getGameExchangeRuleFormat(price: Float): String {
        return "${getCashFormatPointOne(price)}0"
    }

    /**
     * 这里进行字符串截取
     */
    fun firstIndexOf(str: String, pattern: String): Int {
        for (i in 0 until str.length - pattern.length) {
            var j = 0
            while (j < pattern.length) {
                if (str[i + j] != pattern[j])
                    break
                j++
            }
            if (j == pattern.length)
                return i
        }
        return -1
    }

    /**
     * 通过比例来换算显示的当地货币
     */
//    fun getTwoCurrencyDollarsByUs(currencyRate: Float, ratioOfCoinToCent: Int): String = getTwoDecimalDollarsByCents(((LoginUtils.coinBalance.toFloat() / ratioOfCoinToCent)) * currencyRate)


    /**
     * 获取当前美金对应的其他货币
     */
//    fun getTwoDollarsByUs(currencyRate: Float, cents: Int): String = getTwoDecimalDollarsByCents(cents * currencyRate)

    /**
     * 通过美分值获取到小数点后保留两位的美元
     */
//    fun getTwoDecimalDollarsByCents(centsValue: Int) = getCashFormatPoint2(CashToCoinsUtils.byCentGetCash(centsValue))

    /**
     * 通过美分值获取到小数点后保留两位的美元
     */
//    fun getTwoDecimalDollarsByCents(centsValue: Float) = getCashFormatPoint2(CashToCoinsUtils.byCentGetCash(centsValue))


    /**
     * 通过美分值获取到小数点后保留两位的美元 向下取整
     */
//    fun getTwoDecimalDollarsByCentss(centsValue: Float) = getCashFormatPoint3(CashToCoinsUtils.byCentGetCash(centsValue))


    /**
     * 通过美金获取美分
     */
//    fun getCentsByUSD(usd: Float) = CashToCoinsUtils.byCashGetCent(usd)
}
