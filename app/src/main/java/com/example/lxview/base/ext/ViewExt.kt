package com.example.lxview.base.ext

import android.view.View
import androidx.lifecycle.ViewTreeLifecycleOwner
import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method
import java.lang.reflect.Proxy
import java.util.*
import kotlin.collections.HashMap

/**
 * @author: JayQiu
 * @date: 2022/3/9
 * @description:
 */
/**
 * @param during 防抖时间间隔
 * @param combine 一个接口中的多个回调方法是否共用防抖时间
 */
fun <T> T.throttle(during: Long = 500L, combine: Boolean = true): T {
    return Proxy.newProxyInstance(this!!::class.java.classLoader, this!!::class.java.interfaces,
        object : InvocationHandler { //.InvocationHandler接口是proxy代理实例的调用处理程序实现的一个接口，每一个proxy代理实例都有一个关联的调用处理程序；
        // 在代理实例调用方法时，方法调用被编码分派到调用处理程序的invoke方法。
        private val map = HashMap<Method?, Long>()
        override fun invoke(proxy: Any, method: Method, args: Array<out Any>?): Any? {
            val current = System.currentTimeMillis()
            return if (current - (map[if (combine) null else method] ?: 0) > during) {
                map[if (combine) null else method] = current
                method.invoke(this@throttle, *args.orEmpty())
            } else {
                resolveDefaultReturnValue(method)
            }
        }
    }) as T
}

private fun resolveDefaultReturnValue(method: Method): Any? {
    return when (method.returnType.name.lowercase(Locale.US)) {
        Void::class.java.simpleName.lowercase(Locale.US) -> null
        else -> throw IllegalArgumentException("无法正确对返回值不为空的回调进行节流")
    }
}

/**
 * infix中缀函数
 * infix函数（中缀方法）需要几个条件:
 *只有一个参数
 *在方法前必须加infix关键字
 *必须是成员方法或者扩展方法
 */
infix fun View?.visibleBy(boolean: Boolean) {
    this?.visibility = if (boolean) View.VISIBLE else View.GONE
}

infix fun View?.visibleWith(other: View?) {
    this?.visibility = other?.visibility ?: View.GONE
}

val View.lifecycleOwner get() = ViewTreeLifecycleOwner.get(this)
