package com.example.lxview.base

/**
 * author: 李 祥
 * date:   2021/12/10 11:54 上午
 * description:
 */
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import java.lang.ref.WeakReference

@Suppress("unused")
abstract class BaseActivity:AppCompatActivity() {

    private var weakReference: WeakReference<BaseActivity>? = null
    private var lifecycle: Lifecycle = Lifecycle.DESTROY
    var interceptAllTouchEvent: Boolean = false

    val weakContext: WeakReference<BaseActivity>
        get() {
            return weakReference ?: run { val wr = WeakReference(this);weakReference = wr;wr }
        }

    private enum class Lifecycle(var level: Int) {
        CREATE(-3), START(-2), RESUME(-1), PAUSE(1), STOP(2), DESTROY(3);
    }

    protected abstract val contentId: Int

    open fun preInitContent() {}
    open fun initView() {}
    open fun initData() {}
    open fun initListener() {}
    open fun onInstanceStateValid(inState: Bundle) {}

    @SuppressLint("InflateParams")
    override fun onCreate(instanceState: Bundle?) {
        super.onCreate(instanceState)
        if ((intent.flags and Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT) != 0) {
            finish()
            return
        }
        lifecycle = Lifecycle.CREATE
        preInitContent()
        setContentView(contentId)
        initView()
        initData()
        initListener()
        if (instanceState != null) onInstanceStateValid(instanceState)
    }

    @Suppress("UNCHECKED_CAST")
    protected fun <T : View?> find(id: Int): T? {
        return findViewById<View>(id) as? T
    }

    override fun onDestroy() {
        lifecycle = Lifecycle.DESTROY
        if (weakReference != null) weakReference?.clear()
        weakReference = null
        super.onDestroy()
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        return if (interceptAllTouchEvent) {
            false
        } else super.dispatchTouchEvent(ev)
    }

    override fun onResume() {
        super.onResume()
        lifecycle = Lifecycle.RESUME
    }

    override fun onPause() {
        lifecycle = Lifecycle.PAUSE
        super.onPause()
    }

    override fun onStart() {
        lifecycle = Lifecycle.START
        super.onStart()
    }

    override fun onStop() {
        lifecycle = Lifecycle.STOP
        super.onStop()
    }

    val isStart: Boolean
        get() = lifecycle.level >= Lifecycle.START.level && lifecycle.level <= Lifecycle.STOP.level

    val isDestroy: Boolean
        get() = lifecycle == Lifecycle.DESTROY

    val isResume: Boolean
        get() = lifecycle == Lifecycle.RESUME

    val isPause: Boolean
        get() = lifecycle.level >= Lifecycle.PAUSE.level

    val isStop: Boolean
        get() = lifecycle.level >= Lifecycle.STOP.level

    val isCreate: Boolean
        get() = lifecycle.level > Lifecycle.CREATE.level && lifecycle.level < Lifecycle.DESTROY.level

    /**
     * @return true If you want to set the font color of the status bar to black, the default is true
     * */
    protected open fun isWhiteStatus(): Boolean {
        return true
    }

    @Suppress("DEPRECATION")
    protected fun changeStatusBarFontColor(isDark: Boolean) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (isDark) {
                val v = window.decorView.systemUiVisibility
                window.decorView.systemUiVisibility = v or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            } else {
                val v = window.decorView.systemUiVisibility
                window.decorView.systemUiVisibility = v and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv()
            }
        }
    }

}