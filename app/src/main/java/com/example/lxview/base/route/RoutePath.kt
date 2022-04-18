package com.example.lxview.base.route

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat.startActivity
import com.example.baselib.utils.StartActivityUtils
import com.example.baselib.utils.ToastUtils
import com.example.baselib.utils.startAct
import com.example.lxview.base.BaseApplication
import com.example.lxview.login.activity.LoginActivity
import com.example.lxview.lxhome.activity.AccountsTotalActivity
import com.example.lxview.lxhome.activity.CardBagActivity
import com.example.lxview.lxtools.NormalToolsActivity

object RoutePath {


    class MainApp {

        companion object {
            const val TOOLS_NORMAL = "com.example.lxview.lxtools.NormalToolsActivity"
            const val CARD_BAG = "com.example.lxview.lxhome.activity.CardBagActivity"
            const val ACCOUNTS_TOTAL = ".lxhome.activity.AccountsTotalActivity"
        }
    }

    class UserCenter {
        companion object {
            const val LOGIN_ACTIVITY = "com.example.lxview.login.activity.LoginActivity"

        }
    }

    class OtherApp {
        companion object {
            const val ZHIFUBAO = "ZHIFUBAO"
        }
    }

    fun jumpAct(context: Context, string: String?) {
        val packageManager: PackageManager = context.packageManager
        string?.let {
            when {
                string.contains(MainApp.TOOLS_NORMAL) -> {
                    context.startAct<NormalToolsActivity>()
                }
                string.contains(UserCenter.LOGIN_ACTIVITY) -> context.startAct<LoginActivity>()
                string.contains(MainApp.CARD_BAG) -> context.startAct<CardBagActivity>()
                string.contains(MainApp.ACCOUNTS_TOTAL) -> context.startAct<AccountsTotalActivity>()
                string.contains(OtherApp.ZHIFUBAO) -> {
                    val intent: Intent? = packageManager.getLaunchIntentForPackage("com.eg.android.AlipayGphone")  //跳转到下一页5 APP界面
                    context.startActivity(intent)
                }
                else -> ToastUtils.showToast(context, string)
            }
        }
    }


}