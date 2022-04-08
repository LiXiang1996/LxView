//package com.example.baselib.utils
//
//import android.content.Context
//import android.content.Intent
//import android.content.pm.PackageManager
//import android.net.Uri
//import android.os.Build
//import android.telephony.TelephonyManager
//import android.text.TextUtils
//import java.util.*
//
//
///**
// * author: lixiang
// * Date: 2022/2/28 19:06
// * Description: 接口头部请求工具类，用来处理公共类头部数据
// */
//object RequestHeaderInfoUtils {
//    val OPPO_MARKET = "com.oppo.market"
//    val XIAOMI_MARKET = "com.xiaomi.market"
//    val ANDROID_VENDING = "com.android.vending"
//
//    /**
//     * 获取当前手机系统版本号
//     */
//    fun getSystemVersion(): String {
//        return Build.VERSION.RELEASE
//    }
//
//    //获取版本号，非应用内版本号， 固定值，不能修改
////    fun getVersionCode(): String = ClipClapsConstant.CC_API_VERSION_CODE
//
//
//    // 获取版本名
//    fun getVersionName(): String {
//        return try {
//            context.packageManager.getPackageInfo(context.packageName, PackageManager.GET_CONFIGURATIONS).versionName
//        } catch (e: Exception) {
//            ""
//        }
//    }
//
//    // 获取报名
//    fun getAppPackageName(): String {
//        return try {
//            context.packageManager.getPackageInfo(context.packageName, PackageManager.GET_CONFIGURATIONS).packageName
//        } catch (e: Exception) {
//            ""
//        }
//    }
//
//    /**
//     * 获取时区
//     */
//    fun getTimezone(): String {
//        return (TimeZone.getDefault().getOffset(System.currentTimeMillis()) / (3600 * 1000)).toString()
//    }
//
//
//    /**
//     * 获取系统的locale
//     *
//     * @return Locale对象
//     */
//    fun getSystemLocale(context: Context): String {
//        return MultilingualUtils.getSystemLocale(context)
//    }
//
//    /**
//     * 获取app的本地语言
//     *
//     * @return Locale对象
//     */
//    fun getAppSystemLocale(): String {
////        val type = LoginUtils.localeLanguage
////        return if (type != MultilingualUtils.def_local_type) {
////            MultilingualUtils.getAppLocalLanguageByType(type)
////        } else {
////            getSystemLocale(context)
////        }
//        return "1"
//    }
//
//    /**
//     * 获取运营商
//     */
//    fun getOperatorName(): String? {
//        val telephonyManager = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
//        return telephonyManager.simOperatorName
//    }
//
//    /**
//     * 获取用户的ReqId
//     */
//    fun getReqId(): String {
//        return UUID.randomUUID().toString()
//    }
//
//    /**
//     * 获取渠道名称
//     */
//    fun getChannel(): String {
//        try {
//            val pm = context.packageManager
//            val appInfo = pm.getApplicationInfo(getAppPackageName(), PackageManager.GET_META_DATA)
//            return appInfo.metaData.getString("UMENG_CHANNEL") ?: "gg"
//        } catch (ignored: PackageManager.NameNotFoundException) {
//        }
//
//        return "gg"
//    }
//
//    /**
//     * 跳转商店
//     */
//    fun goPlay(context: Context) {
//        when (getChannel()) {
//            "gg" -> goGooglePlay(context)
//            "xiaomi" -> goXiaoMiPlay(context)
//            "oppo" -> goOppoPlay(context)
//            else -> goGooglePlay(context)
//        }
//    }
//
//    /**
//     * 跳转谷歌商店
//     */
//    private fun goGooglePlay(context: Context) {
//        if (isAvilible(context, ANDROID_VENDING)) {
//            launchAppDetail(context, getAppPackageName(), ANDROID_VENDING)
//        } else {
//            ToastUtils.showAccountToast(context, "edseded")
//        }
//    }
//
//    /**
//     * 跳转小米商店
//     */
//    private fun goXiaoMiPlay(context: Context) {
//        if (isAvilible(context, XIAOMI_MARKET)) {
//            launchAppDetail(context, getAppPackageName(), XIAOMI_MARKET)
//        } else {
//            ToastUtils.showAccountToast(context, "edseded")
//        }
//    }
//
//    /**
//     * 跳转oppo商店
//     */
//    fun goOppoPlay(context: Context) {
//        if (isAvilible(context, OPPO_MARKET)) {
//            launchAppDetail(context, getAppPackageName(), OPPO_MARKET)
//        } else {
//            ToastUtils.showAccountToast(context, "edseded")
//        }
//    }
//
//    /**
//     * 判断应用市场是否存在的方法
//     *
//     * @param context
//     * @param packageName
//     *
//     * 主流应用商店对应的包名
//     * com.android.vending    -----Google Play
//     * com.tencent.android.qqdownloader     -----应用宝
//     * com.qihoo.appstore    -----360手机助手
//     * com.baidu.appsearch    -----百度手机助
//     * com.xiaomi.market    -----小米应用商店
//     * com.wandoujia.phoenix2    -----豌豆荚
//     * com.huawei.appmarket    -----华为应用市场
//     * com.taobao.appcenter    -----淘宝手机助手
//     * com.hiapk.marketpho    -----安卓市场
//     * cn.goapk.market        -----安智市场
//     * com.oppo.market        -----oppo市场
//     */
//    fun isAvilible(context: Context, packageName: String): Boolean { // 获取packagemanager
//        val packageManager = context.packageManager // 获取所有已安装程序的包信息
//        val pinfo = packageManager.getInstalledPackages(0) // 用于存储所有已安装程序的包名
//        val pName = ArrayList<String>() // 从pinfo中将包名字取出
//        if (pinfo != null) {
//            for (i in pinfo.indices) {
//                val pf = pinfo[i].packageName
//                pName.add(pf)
//            }
//        } // 判断pName中是否有目标程序的包名，有true，没有false
//        return pName.contains(packageName)
//    }
//
//
//    /**
//     * 启动到应用商店app详情界面
//     *
//     * @param appPkg    目标App的包名
//     * @param marketPkg 应用商店包名 ,如果为""则由系统弹出应用商店列表供用户选择,否则调转到目标市场的应用详情界面
//     */
//    private fun launchAppDetail(mContext: Context, appPkg: String, marketPkg: String) {
//        try {
//            if (TextUtils.isEmpty(appPkg)) {
//                return
//            }
//
//            val uri = Uri.parse("market://details?id=$appPkg")
//            val intent = Intent(Intent.ACTION_VIEW, uri)
//            if (!TextUtils.isEmpty(marketPkg)) {
//                intent.setPackage(marketPkg)
//            }
//            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//            mContext.startActivity(intent)
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }
//    }
//}
