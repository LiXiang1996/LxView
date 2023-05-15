package com.example.lxview.base.utils

import android.view.View
import android.view.View.OnClickListener

/**
 * 使用方法：
 *
 * mBinding.mainAppLockCl.setOnClickListener(ClickProxy {
    startActivity(Intent(this, LockActivity::class.java))
    })
 *
 */
class ClickProxy(
    private var internal: Long = 500,
    private val onClickListener: OnClickListener
) : OnClickListener {

    private var preTime: Long = 0
    override fun onClick(v: View?) {
        val curTimes = System.currentTimeMillis()
        if (curTimes - preTime > internal) {
            preTime = curTimes
            onClickListener.onClick(v)
        }
    }
}