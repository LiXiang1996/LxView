package com.example.sharelib.`interface`

import android.content.Context
import android.content.Intent

interface IShare {
    companion object {
        val ERROR = -10086
    }

    fun init(app: Context)
    fun doShare(context: Context)
    fun showShareWindow(state: Int, isShow: Boolean)
    fun showShareWindow()
    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?)

    fun setPlatform(platform: Int): IShare
    fun setUrl(url: String): IShare
    fun setContent(content: String): IShare
    fun setTitle(url: String): IShare
    fun setType(type: String): IShare
    fun setCallBack(callBack: CallBack): IShare
    /**
     * 用于Instagram等需要下载视频后分享的平台
     */
    fun setVideo(video: String): IShare

    /**
     * 分享动作拦截
     */
    fun setIntercept(intercept: Intercept): IShare
}
