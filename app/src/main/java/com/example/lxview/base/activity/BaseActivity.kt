package com.example.lxview.base.activity

/**
 * author: 李 祥
 * date:   2021/12/10 11:54 上午
 * description:
 */
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.example.lxview.R
import com.gyf.immersionbar.ImmersionBar
import java.lang.ref.WeakReference


abstract class BaseActivity:AppCompatActivity() {

    private var weakReference: WeakReference<BaseActivity>? = null
    private var lifecycle: Lifecycle = Lifecycle.DESTROY
    var interceptAllTouchEvent: Boolean = false

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
        ImmersionBar.with(this).fullScreen(true).navigationBarColor(R.color.activity_color).statusBarColor(R.color.transparent).init()

        //        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        //        注意要清除 FLAG_TRANSLUCENT_STATUS flag
        //        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //        window.statusBarColor = resources.getColor(com.example.lxview.R.color.transparent);
        //        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;


        lifecycle = Lifecycle.CREATE
        preInitContent()
        setContentView(contentId)
        initView()
        initData()
        initListener()
        if (instanceState != null) onInstanceStateValid(instanceState)
    }

    override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View? {
//        //实现哀悼版主题，哈哈哈
//        try {
//            if ("FrameLayout" == name) {
//                val count: Int = attrs.attributeCount
//                for (i in 0 until count) {
//                    val attributeName: String = attrs.getAttributeName(i)
//                    val attributeValue: String = attrs.getAttributeValue(i)
//                    if (attributeName == "id") {
//                        val id = attributeValue.substring(1).toInt()
//                        val idVal = resources.getResourceName(id)
//                        if ("android:id/content" == idVal) {
//                            val grayFrameLayout = GrayFrameLayout(context, attrs)
//                            grayFrameLayout.setBackgroundDrawable(window.decorView.background)
//                            return grayFrameLayout
//                        }
//
//                    }
//                }
//            }
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }
        return super.onCreateView(name, context, attrs)
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

}