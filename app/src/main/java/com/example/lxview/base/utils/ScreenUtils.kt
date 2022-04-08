package com.example.lxview.base.utils

import android.annotation.SuppressLint
import android.app.Activity
import android.app.KeyguardManager
import android.content.Context
import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.content.res.Resources
import android.graphics.Point
import android.os.Build
import android.view.Surface
import android.view.View
import android.view.Window
import android.view.WindowManager
import com.example.lxview.base.BaseApplication

object ScreenUtils {
    /**
     * Return the distance between the given View's X (start point of View's width) and the screen width.
     *
     * @return the distance between the given View's X (start point of View's width) and the screen width.
     */
    fun calculateDistanceByX(view: View): Int {
        val point: IntArray = IntArray(2)
        view.getLocationOnScreen(point)
        return screenWidth - point[0]
    }

    /**
     * Return the distance between the given View's Y (start point of View's height) and the screen height.
     *
     * @return the distance between the given View's Y (start point of View's height) and the screen height.
     */
    fun calculateDistanceByY(view: View): Int {
        val point: IntArray = IntArray(2)
        view.getLocationOnScreen(point)
        return screenHeight - point[1]
    }

    /**
     * Return the X coordinate of the given View on the screen.
     *
     * @return X coordinate of the given View on the screen.
     */
    fun getViewX(view: View): Int {
        val point: IntArray = IntArray(2)
        view.getLocationOnScreen(point)
        return point[0]
    }

    /**
     * Return the Y coordinate of the given View on the screen.
     *
     * @return Y coordinate of the given View on the screen.
     */
    fun getViewY(view: View): Int {
        val point: IntArray = IntArray(2)
        view.getLocationOnScreen(point)
        return point[1]
    }

    /**
     * Return the width of screen, in pixel.
     *
     * @return the width of screen, in pixel
     */
    val screenWidth: Int
        get() {
            val wm: WindowManager = BaseApplication.appContext.getSystemService(Context.WINDOW_SERVICE) as WindowManager ?: return -1
            val point: Point = Point()
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                wm.defaultDisplay.getRealSize(point)
            } else {
                wm.defaultDisplay.getSize(point)
            }
            return point.x
        }

    /**
     * Return the height of screen, in pixel.
     *
     * @return the height of screen, in pixel
     */
    val screenHeight: Int
        get() {
            val wm: WindowManager = BaseApplication.appContext.getSystemService(Context.WINDOW_SERVICE) as WindowManager ?: return -1
            val point: Point = Point()
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                wm.defaultDisplay.getRealSize(point)
            } else {
                wm.defaultDisplay.getSize(point)
            }
            return point.y
        }

    /**
     * Return the application's width of screen, in pixel.
     *
     * @return the application's width of screen, in pixel
     */
    val appScreenWidth: Int
        get() {
            val wm: WindowManager = BaseApplication.appContext.getSystemService(Context.WINDOW_SERVICE) as WindowManager ?: return -1
            val point: Point = Point()
            wm.defaultDisplay.getSize(point)
            return point.x
        }

    /**
     * Return the application's height of screen, in pixel.
     *
     * @return the application's height of screen, in pixel
     */
    val appScreenHeight: Int
        get() {
            val wm: WindowManager = BaseApplication.appContext.getSystemService(Context.WINDOW_SERVICE) as WindowManager ?: return -1
            val point: Point = Point()
            wm.defaultDisplay.getSize(point)
            return point.y
        }

    /**
     * Return the density of screen.
     *
     * @return the density of screen
     */
    val screenDensity: Float
        get() = Resources.getSystem().displayMetrics.density

    /**
     * Return the screen density expressed as dots-per-inch.
     *
     * @return the screen density expressed as dots-per-inch
     */
    val screenDensityDpi: Int
        get() = Resources.getSystem().displayMetrics.densityDpi

    /**
     * Return the exact physical pixels per inch of the screen in the Y dimension.
     *
     * @return the exact physical pixels per inch of the screen in the Y dimension
     */
    val screenXDpi: Float
        get() = Resources.getSystem().displayMetrics.xdpi

    /**
     * Return the exact physical pixels per inch of the screen in the Y dimension.
     *
     * @return the exact physical pixels per inch of the screen in the Y dimension
     */
    val screenYDpi: Float
        get() = Resources.getSystem().displayMetrics.ydpi

    /**
     * Set full screen.
     *
     * @param activity The activity.
     */
    fun setFullScreen(activity: Activity) {
        activity.window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
    }

    /**
     * Set non full screen.
     *
     * @param activity The activity.
     */
    fun setNonFullScreen(activity: Activity) {
        activity.window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
    }

    /**
     * Toggle full screen.
     *
     * @param activity The activity.
     */
    fun toggleFullScreen(activity: Activity) {
        val isFullScreen: Boolean = isFullScreen(activity)
        val window: Window = activity.window
        if (isFullScreen) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        } else {
            window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        }
    }

    /**
     * Return whether screen is full.
     *
     * @param activity The activity.
     * @return `true`: yes<br></br>`false`: no
     */
    fun isFullScreen(activity: Activity): Boolean {
        val fullScreenFlag: Int = WindowManager.LayoutParams.FLAG_FULLSCREEN
        return (activity.window.attributes.flags and fullScreenFlag) == fullScreenFlag
    }

    /**
     * Set the screen to landscape.
     *
     * @param activity The activity.
     */
    @SuppressLint("SourceLockedOrientationActivity")
    fun setLandscape(activity: Activity) {
        activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
    }

    /**
     * Set the screen to portrait.
     *
     * @param activity The activity.
     */
    @SuppressLint("SourceLockedOrientationActivity")
    fun setPortrait(activity: Activity) {
        activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
    }

    /**
     * Return whether screen is landscape.
     *
     * @return `true`: yes<br></br>`false`: no
     */
    val isLandscape: Boolean
        get() = (BaseApplication.appContext.resources.configuration.orientation === Configuration.ORIENTATION_LANDSCAPE)

    /**
     * Return whether screen is portrait.
     *
     * @return `true`: yes<br></br>`false`: no
     */
    val isPortrait: Boolean
        get() {
            return (BaseApplication.appContext.resources.configuration.orientation === Configuration.ORIENTATION_PORTRAIT)
        }

    /**
     * Return the rotation of screen.
     *
     * @param activity The activity.
     * @return the rotation of screen
     */
    fun getScreenRotation(activity: Activity): Int {
        return when (activity.windowManager.defaultDisplay.rotation) {
            Surface.ROTATION_0 -> 0
            Surface.ROTATION_90 -> 90
            Surface.ROTATION_180 -> 180
            Surface.ROTATION_270 -> 270
            else -> 0
        }
    }

    /**
     * Return whether screen is locked.
     *
     * @return `true`: yes<br></br>`false`: no
     */
    val isScreenLock: Boolean
        get() {
            val km: KeyguardManager = BaseApplication.appContext.getSystemService(Context.KEYGUARD_SERVICE) as KeyguardManager ?: return false
            return km.inKeyguardRestrictedInputMode()
        }
}