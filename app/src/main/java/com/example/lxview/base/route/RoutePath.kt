package com.example.lxview.base.route

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import com.example.baselib.utils.ToastUtils
import com.example.baselib.utils.startAct
import com.example.lxview.function.animation.activity.AnimationGoActivity
import com.example.lxview.login.activity.LoginActivity
import com.example.lxview.lxhome.activity.*
import com.example.lxview.lxhome.func.nav.activity.NavMenuActivity
import com.example.lxview.lxhome.func.nav.activity.NavViewPagerRadioGroupActivity
import com.example.lxview.lxhome.func.nav.activity.NavViewPagerTabActivity
import com.example.lxview.lxhome.img.ImgAnimationActivity
import com.example.lxview.lxhome.img.ImgShapeActivity
import com.example.lxview.lxtools.NormalToolsActivity

object RoutePath {

    /**
     *
     * 使用这个路由，可以根据后台返回数据里的字段，来决定某跳转目标页面，实现动态配置
     * 但是界面里的跳转用这个方法还是不大方便，直接context.startAct<NormalToolsActivity>()更方便
     */

    class MainApp {
        companion object {
            const val TOOLS_NORMAL = "com.example.lxview.lxtools.NormalToolsActivity"
            const val CARD_BAG = "com.example.lxview.lxhome.activity.CardBagActivity"
            const val ACCOUNTS_TOTAL = ".lxhome.activity.AccountsTotalActivity"
            const val FUNC_SQL = ".lxhome.activity.SQLiteActivity"
            const val GAME_TEAM = ".lxhome.activity.GameTeamActivity"
            const val WEB_VIEW = "com.example.lxview.lxhome.activity.WebViewActivity"
            const val NAV_ACT = ".lxhome.activity.NavListActivity"
            const val NAV_MENU_ACT = "com.example.lxview.lxhome.func.nav.activity.NavMenuActivity"
            const val NAV_VIEWPAGER_TAB = "com.example.lxview.lxhome.func.nav.activity.NavViewPagerTabActivity"
            const val NAV_VIEWPAGER_GROUP_RADIO = "com.example.lxview.lxhome.func.nav.activity.NavViewPagerRadioGroupActivity"
            const val IMG_CENTER = "com.example.lxview.lxhome.activity.ImgListActivity"
            const val ANIMATION = "AnimationGoActivity"
        }
    }

    class UserCenter {
        companion object {
            const val LOGIN_ACTIVITY = "LoginActivity"

        }
    }

    class ImgCenter {
        companion object {
            const val IMG_ANIMATE = "ImgAnimationActivity"
            const val IMG_SHAPE = "ImgShapeActivity"

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
                string.contains(MainApp.TOOLS_NORMAL) -> { context.startAct<NormalToolsActivity>() }
                string.contains(UserCenter.LOGIN_ACTIVITY) -> context.startAct<LoginActivity>()
                string.contains(MainApp.ANIMATION) -> context.startAct<AnimationGoActivity>()
                string.contains(MainApp.WEB_VIEW) -> context.startAct<WebViewActivity>()
                string.contains(MainApp.CARD_BAG) -> context.startAct<CardBagActivity>()
                string.contains(MainApp.ACCOUNTS_TOTAL) -> context.startAct<AccountsTotalActivity>()
                string.contains(MainApp.FUNC_SQL) -> context.startAct<SQLiteActivity>()
                string.contains(MainApp.GAME_TEAM) -> context.startAct<GameTeamActivity>()
                string.contains(MainApp.NAV_ACT) -> context.startAct<NavListActivity>()
                string.contains(MainApp.NAV_MENU_ACT) -> context.startAct<NavMenuActivity>()
                string.contains(MainApp.NAV_VIEWPAGER_TAB) -> context.startAct<NavViewPagerTabActivity>()
                string.contains(MainApp.NAV_VIEWPAGER_GROUP_RADIO) -> context.startAct<NavViewPagerRadioGroupActivity>()
                string.contains(MainApp.IMG_CENTER) -> context.startAct<ImgListActivity>()
                string.contains(ImgCenter.IMG_ANIMATE) -> context.startAct<ImgAnimationActivity>()
                string.contains(ImgCenter.IMG_SHAPE) -> context.startAct<ImgShapeActivity>()
                string.contains(OtherApp.ZHIFUBAO) -> {
                    val intent: Intent? = packageManager.getLaunchIntentForPackage("com.eg.android.AlipayGphone")  //跳转到下一页5 APP界面
                    context.startActivity(intent)
                }
                else -> ToastUtils.showToast(context, string)
            }
        }
    }


}