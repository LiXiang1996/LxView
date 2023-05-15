package com.example.lxview.base.utils

/**
 * 使用方法：
 * fun onClick(view: View) {
    if (DebounceClickHandler.isClickValid()) {
    // 执行点击事件
    }
    }
 */

object DebounceClickHandler {

    private const val DEFAULT_DELAY = 500L // 默认的点击延迟时间间隔
    private var lastClickTime: Long = 0

    /**
     * 判断点击事件是否有效
     *
     * @return true 表示点击事件有效，false 表示点击事件无效
     */
    fun isClickValid(): Boolean {
        return isClickValid(DEFAULT_DELAY)
    }

    /**
     * 判断点击事件是否有效
     *
     * @param delayMillis 延迟时间间隔，单位毫秒
     * @return true 表示点击事件有效，false 表示点击事件无效
     */
    fun isClickValid(delayMillis: Long): Boolean {
        val currentClickTime = System.currentTimeMillis()
        if (currentClickTime - lastClickTime >= delayMillis) {
            lastClickTime = currentClickTime
            return true
        }
        return false
    }
}
