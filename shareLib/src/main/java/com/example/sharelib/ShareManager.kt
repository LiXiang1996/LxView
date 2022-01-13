package com.example.sharelib

import android.annotation.SuppressLint
import android.app.Activity
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.StrictMode
import android.provider.MediaStore
import androidx.core.content.FileProvider
import com.example.baselib.utils.ClipboardHelper
import com.example.baselib.utils.ToastUtils
import com.example.sharelib.`interface`.*
import com.example.sharelib.`interface`.IShare.Companion.ERROR
import com.example.sharelib.ui.ShareActivity
import com.example.sharelib.ui.ShareActivityManager
import com.example.sharelib.ui.ShareDialogActivity
import java.io.File


class ShareManager private constructor(var context: Context) : IShare {
    val CODE = 101

    companion object {
        @SuppressLint("StaticFieldLeak") @Volatile var instance: ShareManager? = null

        fun getInstance(c: Context): ShareManager {
            if (instance == null) {
                synchronized(ShareManager::class) {
                    if (instance == null) {
                        instance = ShareManager(c.applicationContext)
                    }
                }
            }
            return instance!!
        }
    }

    var platform: Int = Platform.ANDROID
    var url: String = "http://www.gholl.com"
    var title: String = "title"
    var content: String = ""
    var mimeType: String = Type.TEXT
    var callBack: CallBack? = null
    var video: String? = null
    var intercept: Intercept? = null

    override fun setIntercept(intercept: Intercept): ShareManager {
        this.intercept = intercept
        return this
    }

    override fun setContent(content: String): IShare {
        this.content = content
        return this
    }

    override fun setPlatform(platform: Int): ShareManager {
        this.platform = platform
        return this
    }

    override fun setUrl(url: String): ShareManager {
        this.url = url
        return this
    }

    override fun setTitle(url: String): ShareManager {
        this.title = title
        return this
    }

    override fun setType(type: String): ShareManager {
        this.mimeType = type
        return this
    }

    override fun setCallBack(callBack: CallBack): ShareManager {
        this.callBack = callBack
        return this
    }

    override fun setVideo(video: String): ShareManager {
        this.video = video
        return this
    }

    private fun shareToIns(activity: Activity) {
        //        val task = DownloadTask.Builder(video!!, File(Utils.getFilePath(activity, "sv"))).setFilename("s.mp4")
        //            // the minimal interval millisecond for callback progress
        //            .setMinIntervalMillisCallbackProcess(30)
        //            // do re-download even if the task has already been completed in the past.
        //            .setPassIfAlreadyCompleted(false).build()
        //
        //        val listener = object : DownloadListener {
        //            override fun connectTrialEnd(task: DownloadTask, responseCode: Int, responseHeaderFields: MutableMap<String, MutableList<String>>) {
        //            }
        //
        //            override fun fetchEnd(task: DownloadTask, blockIndex: Int, contentLength: Long) {
        //            }
        //
        //            override fun downloadFromBeginning(task: DownloadTask, info: BreakpointInfo, cause: ResumeFailedCause) {
        //            }
        //
        //            override fun taskStart(task: DownloadTask) {
        //            }
        //
        //            override fun taskEnd(task: DownloadTask, cause: EndCause, realCause: java.lang.Exception?) {
        //                if (cause == EndCause.COMPLETED) {
        //                    val intent = Intent()
        //                    intent.action = Intent.ACTION_SEND
        //                    intent.type = Type.VIDEO // 设置分享内容的类型
        //                    intent.putExtra(Intent.EXTRA_SUBJECT, title) // 添加分享内容标题
        //                    intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(task.file)) // 添加分享内容
        //                    intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        //
        //                    val compoment = ComponentName("com.instagram.android", "com.instagram.share.handleractivity.ShareHandlerActivity")
        //                    intent.component = compoment
        //                    if (Build.VERSION.SDK_INT < 24) {
        //                        activity.startActivityForResult(intent, CODE)
        //                    } else {
        //                        val builder = StrictMode.VmPolicy.Builder()
        //                        StrictMode.setVmPolicy(builder.build())
        //                        builder.detectFileUriExposure()
        //                        val contentValues = ContentValues(1)
        //                        contentValues.put(MediaStore.Video.Media.DATA, task.file!!.absolutePath)
        //                        val uri = FileProvider.getUriForFile(context, context.packageName + ".fileprovider", task.file!!)
        //                        context.grantUriPermission(context.packageName, uri, Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
        //                        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        //                        intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
        //                        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri)
        //                        activity.startActivityForResult(intent, CODE)
        //                    }
        //                } else {
        //                    callBack?.onShareEnd(platform, ERROR)
        //                }
        //            }
        //
        //            override fun connectTrialStart(task: DownloadTask, requestHeaderFields: MutableMap<String, MutableList<String>>) {
        //            }
        //
        //            override fun downloadFromBreakpoint(task: DownloadTask, info: BreakpointInfo) {
        //            }
        //
        //            override fun fetchStart(task: DownloadTask, blockIndex: Int, contentLength: Long) {
        //            }
        //
        //            override fun fetchProgress(task: DownloadTask, blockIndex: Int, increaseBytes: Long) {
        //            }
        //
        //            override fun connectEnd(task: DownloadTask, blockIndex: Int, responseCode: Int, responseHeaderFields: MutableMap<String, MutableList<String>>) {
        //            }
        //
        //            override fun connectStart(task: DownloadTask, blockIndex: Int, requestHeaderFields: MutableMap<String, MutableList<String>>) {
        //            }
        //        }
        //        Thread(Runnable { task.execute(listener) }).start()
    }

    var time = 0L
    override fun doShare(context: Context) {
        val x = System.currentTimeMillis() - time
        if (x < 500 && platform != Platform.ADD_TO_FAVORITES) {
            return
        }
        var jump = false
        if (intercept != null) {
            jump = intercept!!.doShare(platform) // 此处拦截设计的有很大的瑕疵，仅为了接口需要而添加，后续完善
        }

        if (!jump) {
            when (platform) {
                Platform.ANDROID -> {
                    if (callBack != null) {
                        // 这里回调
                        callBack!!.onShareEnd(platform, 1)
                    }
                    var intent = Intent()
                    intent.action = Intent.ACTION_SEND
                    intent.type = mimeType // 设置分享内容的类型
                    intent.putExtra(Intent.EXTRA_SUBJECT, title) // 添加分享内容标题
                    intent.putExtra(Intent.EXTRA_TEXT, content) // 添加分享内容
                    // 创建分享的Dialog
                    intent = Intent.createChooser(intent, "share to")
                    context.startActivity(intent)
                }
                Platform.SHARE_COPY_LINK -> {
                    if (callBack != null) {
                        // 这里回调
                        callBack!!.onShareEnd(platform, 1)
                    }
                    ClipboardHelper.getInstance(context).copyText(url)
                    ToastUtils.showAccountToast(context, "复制成功")
                }
                Platform.SHARE_FUNCTION -> {
                    callBack?.onShareEnd(platform, 1)
                    ShareActivityManager.instance.finishAllActivity()
                }
                else -> // 启动一个透明activity 用于接收返回结果
                    context.startActivity(Intent(context, ShareActivity::class.java))
            }
        }

        if (intercept != null) {
            intercept = null
        }
    }

    fun exec(activity: Activity) {
        time = System.currentTimeMillis()
        try {
            //            if (platform == Platform.FACEBOOK) {
            //                ShareOpenGraphContent.Builder()
            //                val content = ShareLinkContent.Builder().setContentUrl(Uri.parse(url)).setQuote(content).build()
            //                ShareDialog.show(activity, content)
            //                this.content = ""
            //            } else
            if (platform == Platform.SNAPCHAT) {
                val intent = Intent()
                intent.action = Intent.ACTION_SEND
                intent.type = "text/plain" // 设置分享内容的类型
                intent.putExtra(Intent.EXTRA_SUBJECT, title) // 添加分享内容标题
                intent.putExtra(Intent.EXTRA_TEXT, content) // 添加分享内容
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                val compoment = ComponentName("com.snapchat.android", "com.snapchat.android.LandingPageActivity")
                intent.component = compoment
                if (isAvilible(context, "com.snapchat.android")) {
                    activity.startActivityForResult(intent, CODE)
                } else {
                    throw NullPointerException()
                }
            } else if (platform == Platform.WHATAPP) {
                val intent = Intent()
                intent.action = Intent.ACTION_SEND
                intent.type = mimeType // 设置分享内容的类型
                intent.putExtra(Intent.EXTRA_SUBJECT, title) // 添加分享内容标题
                intent.putExtra(Intent.EXTRA_TEXT, content) // 添加分享内容
                //intent.putExtra(Intent.EXTRA_STREAM,Uri.parse("https://img0.baidu.com/it/u=251614576,2693916083&fm=26&fmt=auto&gp=0.jpg"))
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                val compoment = ComponentName("com.whatsapp", "com.whatsapp.ContactPicker")
                intent.component = compoment
                if (isAvilible(context, "com.whatsapp")) {
                    activity.startActivityForResult(intent, CODE)
                } else {
                    throw NullPointerException()
                }
            } else if (platform == Platform.MESSAGE) {
                val smsToUri = Uri.parse("smsto:")
                val intent = Intent(Intent.ACTION_SENDTO, smsToUri)
                intent.putExtra("sms_body", content)
                try {
                    activity.startActivityForResult(intent, CODE)
                } catch (e: java.lang.Exception) {
                    throw NullPointerException()
                }

                //            } else if (platform == Platform.FBMESSAGER) {
                //                val intent = Intent()
                //                intent.action = Intent.ACTION_SEND
                //                intent.type = mimeType // 设置分享内容的类型
                //                intent.putExtra(Intent.EXTRA_SUBJECT, title) // 添加分享内容标题
                //                intent.putExtra(Intent.EXTRA_TEXT, content) // 添加分享内容
                //                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                //                val compoment = ComponentName("com.facebook.orca", "com.facebook.messenger.intents.ShareIntentHandler")
                //                intent.component = compoment
                //
                //                if (isAvilible(context, "com.facebook.orca")) {
                //                    try {
                //                        activity.startActivityForResult(intent, CODE)
                //                    } catch (e: Exception) {
                //                        e.printStackTrace()
                //                        val actionButton = ShareMessengerURLActionButton.Builder().setTitle(title).setUrl(Uri.parse(url)).build()
                //                        val genericTemplateElement = ShareMessengerGenericTemplateElement.Builder().setTitle(title).setButton(actionButton).build()
                //                        val genericTemplateContent = ShareMessengerGenericTemplateContent.Builder().setGenericTemplateElement(genericTemplateElement).build()
                //                        if (MessageDialog(activity).canShow(genericTemplateContent)) {
                //                            MessageDialog.show(activity, genericTemplateContent)
                //                        } else {
                //                            callBack?.onShareEnd(platform, ERROR)
                //                        }
                //                    }
                //                } else {
                //                    throw NullPointerException()
                //                }
            } else if (platform == Platform.INSTAGRAM) {
                if (video != null) {
                    shareToIns(activity)
                } else {
                    val intent = Intent()
                    intent.action = Intent.ACTION_SEND
                    intent.type = mimeType // 设置分享内容的类型
                    intent.putExtra(Intent.EXTRA_SUBJECT, title) // 添加分享内容标题
                    intent.putExtra(Intent.EXTRA_TEXT, content) // 添加分享内容
                    intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                    val compoment = ComponentName("com.instagram.android", "com.instagram.direct.share.handler.DirectShareHandlerActivity")
                    intent.component = compoment
                    if (isAvilible(context, "com.instagram.android")) {
                        activity.startActivityForResult(intent, CODE)
                    } else {
                        throw NullPointerException()
                    }
                }
            } else if (platform == Platform.FRIENDS) {
                val intent = Intent()
                intent.action = Intent.ACTION_SEND
                intent.type = mimeType // 设置分享内容的类型
                intent.putExtra(Intent.EXTRA_SUBJECT, title) // 添加分享内容标题
                intent.putExtra(Intent.EXTRA_TEXT, content) // 添加分享内容
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                val compoment = ComponentName("com.sanhe.clipclaps", "com.sanhe.messagecenter.ui.activity.RouteActivity")
                intent.component = compoment
                activity.startActivityForResult(intent, CODE)
            } else if (platform == Platform.PHONE_CONTACTS) {
                val intent = Intent()
                intent.action = Intent.ACTION_SEND
                intent.type = mimeType // 设置分享内容的类型
                intent.putExtra("sendTo", "MMS")
                val compoment = ComponentName("com.sanhe.clipclaps", "com.sanhe.browsecenter.ui.activity.SendToFriendActivity")
                intent.component = compoment
                activity.startActivityForResult(intent, CODE)
            }else if(platform==Platform.LINE){
                val build = StringBuilder("https://line.me/R/share?text=$url")
                val uri = Uri.parse(build.toString())
                activity.startActivityForResult( Intent(Intent.ACTION_VIEW,uri),CODE)
            }
        } catch (e: Exception) {
            showErrorToast(activity, platform)
            callBack?.onShareEnd(platform, ERROR)
        }
    }



    /**
     * 是否有指定报名
     */
    private fun isAvilible(context: Context, packageName: String): Boolean {
        val packageManager = context.packageManager
        // 获取所有已安装程序的包信息
        val pinfo = packageManager.getInstalledPackages(0)
        for (i in pinfo.indices) {

            // 循环判断是否存在指定包名
            if (pinfo[i].packageName.equals(packageName, ignoreCase = true)) {
                return true
            }

        }
        return false
    }


    override fun showShareWindow() {
        statrtAct(1, true)
    }

    /**
     * ptype  ：  4 为视频
     */
    private fun statrtAct(state: Int, isShow: Boolean) {
        val intent = Intent(context, ShareDialogActivity::class.java)
        intent.putExtra("share_style_state", state)
        intent.putExtra("share_style_ptype", isShow)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(intent)
    }

    /**
     * 这里用来判断您是否显示ins
     *
     * 1：表示显示
     * 2：表示不显示
     */
    override fun showShareWindow(state: Int, isShow: Boolean) {
        statrtAct(state, isShow)
    }

    override fun init(app: Context) {

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (callBack != null) {
            if (resultCode == -1) {
                //                when (platform) {
                //                    Platform.FACEBOOK -> NewVersionStatisticsUtils.share_fb_success()
                //                    Platform.FBMESSAGER -> NewVersionStatisticsUtils.share_messenger_success()
                //                    Platform.MESSAGE -> NewVersionStatisticsUtils.share_message_success()
                //                    Platform.WHATAPP -> NewVersionStatisticsUtils.share_whatsapp_success()
                //                    Platform.SNAPCHAT -> NewVersionStatisticsUtils.share_snapchat_success()
                //                    Platform.INSTAGRAM -> NewVersionStatisticsUtils.share_ins_success()
                //                }
            }
            // 这里回调
            callBack!!.onShareEnd(platform, resultCode)
        }
        ShareActivityManager.instance.finishAllActivity()
    }

    private fun showErrorToast(activity: Activity, platform: Int) {
        var platformStr = context.resources.getString(R.string.Select_App)
        when (platform) {
            Platform.WHATAPP -> {
                platformStr = context.resources.getString(R.string.Whatsapp)
            }
            Platform.FBMESSAGER -> {
                platformStr = context.resources.getString(R.string.Messenger)
            }
            Platform.FACEBOOK -> {
                platformStr = context.resources.getString(R.string.Facebook)
            }
            Platform.SNAPCHAT -> {
                platformStr = context.resources.getString(R.string.Snapchat)
            }
            Platform.INSTAGRAM -> {
                platformStr = context.resources.getString(R.string.Instagram)
            }
            Platform.LINE -> {
                platformStr = context.resources.getString(R.string.line)
            }
        }
        ToastUtils.showAccountToast(activity, String.format(activity.resources.getString(R.string.No_), platformStr))
        ShareActivityManager.instance.finishAllActivity()
    }
}
