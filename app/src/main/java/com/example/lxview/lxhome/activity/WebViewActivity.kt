package com.example.lxview.lxhome.activity

import com.example.lxview.R
import com.example.lxview.base.activity.BaseBindActivity
import com.example.lxview.databinding.WebviewActivityLayoutBinding
import android.view.KeyEvent
import android.view.ViewGroup
import android.widget.TextView
import android.content.DialogInterface

import android.annotation.SuppressLint
import android.app.AlertDialog

import android.view.View
import android.webkit.*
import android.widget.Button
import android.webkit.JavascriptInterface
import com.example.baselib.utils.ToastUtils


/**
 * author: 李 祥
 * date:   2022/4/21 8:18 下午
 * description:
 */
class WebViewActivity : BaseBindActivity<WebviewActivityLayoutBinding>() {

    override val layout: Int
        get() = R.layout.webview_activity_layout


    private var mWebView: WebView? = null
    var mWebSettings: WebSettings? = null
    var beginLoading: TextView? = null
    var endLoading: TextView? = null
    var loading: TextView? = null
    var mtitle: TextView? = null


    @SuppressLint("SetJavaScriptEnabled")
    override fun initView() {

        mWebView = findViewById(R.id.webView1)
        beginLoading = findViewById(R.id.text_beginLoading)
        endLoading = findViewById(R.id.text_endLoading)
        loading = findViewById(R.id.text_Loading)
        mtitle = findViewById(R.id.title)
        mWebSettings = mWebView?.settings
        mWebView?.loadUrl("https://blog.csdn.net/LoveFHM?type=blog")


        //设置不用系统浏览器打开,直接显示在当前WebView
        mWebView?.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                view.loadUrl(url)
                return true
            }
        }

        //设置WebChromeClient类
        mWebView?.webChromeClient = object : WebChromeClient() {
            //获取网站标题
            override fun onReceivedTitle(view: WebView, title: String) {
                println("标题在这里")
                mtitle?.text = title
            }

            //获取加载进度
            override fun onProgressChanged(view: WebView, newProgress: Int) {
                if (newProgress < 100) {
                    val progress = "$newProgress%"
                    loading?.text = progress
                } else if (newProgress == 100) {
                    val progress = "$newProgress%"
                    loading?.text = progress
                }
            }

        }

        //
        //        //设置WebViewClient类
        //        mWebView?.webViewClient = object : WebViewClient() {
        //            //设置加载前的函数
        //            override fun onPageStarted(view: WebView, url: String, favicon: Bitmap) {
        //                println("开始加载了")
        //                beginLoading?.text = "开始加载了"
        //            }
        //
        //            //设置结束加载函数
        //            override fun onPageFinished(view: WebView, url: String) {
        //                endLoading?.text = "结束加载了"
        //            }
        //        }
        val webSettings = mWebView?.settings


        // 设置与Js交互的权限
        webSettings?.javaScriptEnabled = true // 设置允许JS弹窗
        // 设置允许JS弹窗
        webSettings?.javaScriptCanOpenWindowsAutomatically = true


        //Web调Android 方法一
        // 通过addJavascriptInterface()将Java对象映射到JS对象
        //方法二 在Android通过WebViewClient复写shouldOverrideUrlLoading
        //参数1：Javascript对象名
        //参数2：Java对象名
        //方法三 通过 WebChromeClient 的onJsAlert()、onJsConfirm()、onJsPrompt（）方法回调拦截JS对话框alert()、confirm()、prompt（） 消息


        mWebView?.addJavascriptInterface(object: AndroidtoJs(){}, "test");//AndroidtoJS类对象映射到js的test对象



        // 先载入JS代码
        // 格式规定为:file:///android_asset/文件名.html
        mWebView?.loadUrl("file:///android_asset/javascript.html")

        val button = findViewById<View>(R.id.button_webview) as Button

        button.setOnClickListener { // 通过Handler发送消息
            mWebView?.post(Runnable { // 注意调用的JS方法名要对应上
                // android调用javascript的callJS()方法
                mWebView?.loadUrl("javascript:callJS()")
                // 只需要将第一种方法的loadUrl()换成下面该方法即可
//                mWebView?.evaluateJavascript("javascript:callJS()") { }
            })

        }


        // 由于设置了弹窗检验调用结果,所以需要支持js对话框
        // webview只是载体，内容的渲染需要使用webviewChromClient类去实现
        // 通过设置WebChromeClient对象处理JavaScript的对话框
        //设置响应js 的Alert()函数
        mWebView?.webChromeClient = object : WebChromeClient() {
            override fun onJsAlert(view: WebView, url: String, message: String, result: JsResult): Boolean {
                val b: AlertDialog.Builder = AlertDialog.Builder(this@WebViewActivity)
                b.setTitle("提示")
                b.setMessage(message)
                b.setPositiveButton(android.R.string.ok, DialogInterface.OnClickListener { dialog, which -> result.confirm() })
                b.setCancelable(false)
                b.create().show()
                return true
            }
        }


    }

    //点击返回上一页面而不是退出浏览器
    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK && mWebView?.canGoBack() == true) {
            mWebView?.goBack()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

    //销毁Webview
    override fun onDestroy() {
        if (mWebView != null) {
            mWebView?.loadDataWithBaseURL(null, "", "text/html", "utf-8", null)
            mWebView?.clearHistory()
            (mWebView?.parent as ViewGroup).removeView(mWebView)
            mWebView?.destroy()
            mWebView = null
        }
        super.onDestroy()
    }
}

// 继承自Object类
open class AndroidtoJs : Any() {
    // 定义JS需要调用的方法
    // 被JS调用的方法必须加入@JavascriptInterface注解
    @JavascriptInterface
    fun hello(msg: String?) {
//        ToastUtils.showToast(this,"JS调用了Android的hello方法")
    }
}


