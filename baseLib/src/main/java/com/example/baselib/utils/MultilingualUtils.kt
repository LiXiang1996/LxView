package com.example.baselib.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Build
import android.os.LocaleList
import android.util.Log
import android.view.View

import java.util.*

/*
多语言
 */
object MultilingualUtils {

    //英文1
    var en = "en"
    var en_type = 1

    //中文2
    var zh = "zh"
    var zh_type = 2

    //泰国3
    var th = "th"
    var th_type = 3

    //越南4
    var vi = "vi"
    var vi_type = 4

    //印尼5
    var id = "id"
    var id_type = 5

    //西班牙6
    var es = "es"
    var es_type = 6

    //葡萄牙7
    var pt = "pt"
    var pt_type = 7

    //俄国8
    var ru = "ru"
    var ru_type = 8

    //菲律宾9
    var tl = "tl"
    var tl_type = 9

    //默认语言，系统本地语言
    var def_type = en_type

    //使用本地
    var def_local_type = 0

    /**
     * 设置本地语言
     */
    private fun getLocaleDef(): Locale {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            val localeList = LocaleList.getDefault()
            localeList[0]
        } else {
            Locale.getDefault()
        }
    }

    /**
     * 获取默认的系统类型
     */
    fun getLocalDefType(): Int {
        val locale = getLocaleDef()
        val language = locale.language
        return getAppLocalTypeByLanguage(language)
    }

    /**
     * 是否是支持语言
     */
    fun isSupported(context: Context): Boolean {
        val langList = mutableListOf(en, zh, th, vi, id, es, pt, ru, tl) //系统语言
        val systemLocale = getSystemLocale(context).toLowerCase()
        return langList.contains(systemLocale)
    }

    /**
     * 获取系统的locale
     */
    fun getSystemLocale(context: Context): String {
        val locale = getLocaleDef()
        return locale.language
    }

    /**
     * 判断是否是相同语言
     */
    private fun isSameLanguage(type: Int): Boolean {
        return true
//        return LoginUtils.localeLanguage == type
    }

    /**
     * sp存储本地语言类型
     */
    fun putLanguageType(type: Int) {

//        LoginUtils.localeLanguage = type
    }

    /**
     * sp获取本地存储语言类型
     */
    fun getLanguageType(): Int {
//        return LoginUtils.localeLanguage
        return 1
    }

    /**
     * 跳转主页
     */
    private fun toRestartMainActivity(context: Activity) {
//        AppManager.instance.finishAllActivity()
//        context.startActAddFlags(RouterPath.MainApp.GUIDE_PATH, Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
    }

    fun toSetLanguage(context: Activity, type: Int,v:View?= null) {
        val sameLanguage = isSameLanguage(type)
        if (!sameLanguage) {
            putLanguageType(type)
            v?.postDelayed({
                toRestartMainActivity(context)},800)
        } else {
            putLanguageType(type)
            context.finish()
        }
    }

    /**
     * 获取本地存储的默认值
     */
    fun getAppLocalLanguageBySp(context: Context): String {
        return "1111"
//        return if (LoginUtils.localeLanguage == def_local_type) {
//            if (isSupported(context)) {
//                getSystemLocale(context)
//            } else {
//                getAppLocalLanguageByType(def_type)
//            }
//        } else {
//            getAppLocalLanguageByType(getLanguageType())
//        }
    }

    /**
     * 根据本地type获取对应语言标示
     */
    fun getAppLocalLanguageByType(type: Int): String {
        return when (type) {
            en_type -> en
            zh_type -> zh
            th_type -> th
            vi_type -> vi
            id_type -> id
            es_type -> es
            pt_type -> pt
            ru_type -> ru
            tl_type -> tl
            else -> en
        }
    }

    /**
     * 根据本地type获取对应语言标示
     */
    fun getAppLocalTypeByLanguage(name: String): Int {
        return when (name) {
            en -> en_type
            zh -> zh_type
            th -> th_type
            vi -> vi_type
            id -> id_type
            es -> es_type
            pt -> pt_type
            ru -> ru_type
            tl -> tl_type
            else -> en_type
        }
    }

    /**
     * 根据本地type获取对应语言标示
     */
    fun getAppLocalLanguageContentByType(context: Context): String {
        if (getLanguageType() == def_local_type) {
            val type = getSystemLocale(context)
            return getAppLocalLanguageContentByName(type)
        } else {
            return when (getLanguageType()) {
                en_type -> "English"
                zh_type -> "简体中文"
                th_type -> "ไทย"
                vi_type -> "Tiếng Việt"
                id_type -> "Indonesia"
                es_type -> "Español"
                pt_type -> "Português"
                ru_type -> "Pусский"
                tl_type -> "Tagalog"
                else -> "English"
            }
        }
    }

    private fun getAppLocalLanguageContentByName(name: String): String {
        return when (name) {
            en -> "English"
            zh -> "简体中文"
            th -> "ไทย"
            vi -> "Tiếng Việt"
            id -> "Indonesia"
            es -> "Español"
            pt -> "Português"
            ru -> "Pусский"
            tl -> "Tagalog"
            else -> "English"
        }
    }


    /**
     * by ZJJ ,Multi-language use, follow the new API ----------------------------
     * */

    fun onConfigurationChanged(context: Context): Configuration? {
        return try {
            val configuration = context.resources.configuration
//            val locale = Locale(RequestHeaderInfoUtils.getAppSystemLocale())
//            configuration.setLocale(locale);
                    configuration
        } catch (e: Exception) {
            Log.e("------", "onConfiguration change failed in onConfigurationChanged!!!")
            null
        }
    }

    fun attachBaseContext(ctx: Context): Context {
        return try {
            val configuration = ctx.resources.configuration
//            val locale = Locale(RequestHeaderInfoUtils.getAppSystemLocale())
//            configuration.setLocale(locale)
            ctx.createConfigurationContext(configuration)
        } catch (e: Exception) {
            Log.e("------", "onConfiguration change failed in attachBaseContext !!!")
            ctx
        }
    }

}