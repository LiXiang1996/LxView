package com.example.sharelib.ui

import android.app.Activity
import java.util.*

class ShareActivityManager private constructor() {
    private val activityStack: Stack<Activity> = Stack()

    companion object {
        val instance: ShareActivityManager by lazy { ShareActivityManager() }
    }

    /**
     * 入栈
     */
    fun addActivity(activity: Activity) {
        activityStack.add(activity)
    }

    /**
     * 清理栈
     */
    fun finishAllActivity() {
        for (activity in activityStack) {
            activity.finish()
        }
        activityStack.clear()
    }

}
