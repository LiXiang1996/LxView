package com.example.baselib.utils

import android.Manifest
import android.net.Uri
import android.text.SpannableString
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import com.bumptech.glide.Glide
import com.example.baselib.R
import com.tbruyelle.rxpermissions2.RxPermissions

import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import rx.Observable
import rx.Subscriber
import rx.schedulers.Schedulers
import java.io.File
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*
import java.util.regex.Matcher
import java.util.regex.Pattern



/**
 * 扩展点击事件
 */
fun View.onClick(listener: View.OnClickListener) {
    this.setOnClickListener(listener)
}

/**
 * 扩展点击事件
 */
fun View.onClick(method: () -> Unit) {
    this.setOnClickListener { method() }
}


/**
 * 加载网络图片
 */
fun ImageView.loadUrl(url: String): ImageView {
    if (url.isNullOrEmpty()) {
        GlideUtils.loadUrlCenterCropImage(context, getCompleteImageUrl("headpic/default.png"), this)
    } else {
        GlideUtils.loadUrlCenterCropImage(context, if (isFullPath(url)) url else getCompleteImageUrl(url), this)
    }
    return this
}


fun ImageView.loadUrlForNotice(url: String): ImageView {
    if (url.isNullOrEmpty()) {
        GlideUtils.loadUrlCenterCropImages(context, getCompleteImageUrl("headpic/default.png"), this)
    } else {
        GlideUtils.loadUrlCenterCropImages(context, if (isFullPath(url)) url else getCompleteImageUrl(url), this)
    }
    return this
}

fun ImageView.loadGif(id: Int) {
    Glide.with(context).asGif().load(id).into(this)
}

fun ImageView.loadResId(id: Int) {
    Glide.with(context).load(id).into(this)
}

/**
 * h5加载全路径
 */
fun ImageView.loadAllUrl(url: String): ImageView {
    if (url.isNullOrEmpty()) {
        GlideUtils.loadUrlCenterCropImage(context, getCompleteImageUrl("headpic/default.png"), this)
    } else {
        GlideUtils.loadUrlCenterCropImage(context, url, this)
    }
    return this
}

fun isFullPath(s: String) = s.contains("http") || s.contains("https")

/**
 * 加载网络图片
 */
fun ImageView.loadVideoUrl(url: String): ImageView {
    GlideUtils.loadUrlCenterCropImage(context, if (isFullPath(url)) url else getCompleteImageUrl(url), this)
    return this
}


fun ImageView.loadCustomUrl(url: String, placeholder: Int, error: Int): ImageView {
    GlideUtils.loadUrlCustomImage(context, if (isFullPath(url)) url else getCompleteImageUrl(url), this, placeholder, error)
    return this
}

fun ImageView.loadCustomUrl(url: String): ImageView {
    GlideUtils.loadUrlCustomImage(context, if (isFullPath(url)) url else getCompleteImageUrl(url), this)
    return this
}

fun ImageView.loadCustomUrlAngel(url: String): ImageView {
    GlideUtils.loadUrlAngle(context, if (isFullPath(url)) url else getCompleteImageUrl(url), this)
    return this
}

fun ImageView.loadAuthAngel(url: String): ImageView {
    GlideUtils.loadUrlAuthAngle(context, if (isFullPath(url)) url else getCompleteImageUrl(url), this)
    return this
}

fun ImageView.loadAuthForUriAngel(url: Uri): ImageView {
    GlideUtils.loadAuthForURIAngle(context, url, this)
    return this
}

fun ImageView.loadUrlGameAngleUrl(url: String, placeholder: Int, error: Int): ImageView {
    GlideUtils.loadUrlGameAngle(context, if (isFullPath(url)) url else getCompleteImageUrl(url), this, placeholder, error)
    return this
}

/**
 * 加载网络图片
 */
fun ImageView.loadFitCenterUrl(url: String): ImageView {
    GlideUtils.loadUrlFitCenterImage(context, if (isFullPath(url)) url else getCompleteImageUrl(url), this)
    return this
}

/**
 * 加载网络图片
 */
fun ImageView.loadCenterCropImage(url: String): ImageView {
    GlideUtils.loadCenterCropImage(context, if (isFullPath(url)) url else getCompleteImageUrl(url), this)
    return this
}

/**
 * 加载网络图片
 */
fun ImageView.loadUrlGifImageUrl(url: String): ImageView {
    GlideUtils.loadUrlGifImage(context, if (isFullPath(url)) url else getCompleteImageUrl(url), this)
    return this
}

/**
 * 加载网络图片高斯模糊处理
 */
fun ImageView.loadUrlGS(url: String): ImageView {
    GlideUtils.loadUrlImageGS(context, if (isFullPath(url)) url else getCompleteImageUrl(url), this)
    return this
}

/**
 * 获取一个 file：// 开头的文件地址。
 * */
fun getFileStorageURI(s: String?): String {
    if (!s.isNullOrEmpty()) {
        val f = File(s)
        if (f.exists()) {
            s.removePrefix("file://")
            return "file://$s"
        }
    }
    return ""
}

/**
 * 这里来拼接网络请求图片的标准路径
 */
fun getCompleteImageUrl(url: String?): String {
    return when {
        url.isNullOrEmpty() -> ({
            url
        }).toString()
        File(url).exists() -> {
            getFileStorageURI(url)
        }
        else -> {
            url
        }
    }
}


/**
 * 扩展视图可见性
 */
fun View.setVisible(visible: Boolean) {
    this.visibility = if (visible) View.VISIBLE else View.GONE
}

fun View.setInVisible(visible: Boolean) {
    this.visibility = if (visible) View.VISIBLE else View.INVISIBLE
}

/**
 * 这个用来处理金币数增加分隔符
 */
fun addComma(d: Double): String {
    val enlocale = Locale("en", "US")
    val decimalFormat: DecimalFormat = NumberFormat.getNumberInstance(enlocale) as DecimalFormat
    decimalFormat.applyPattern(",###")
    return decimalFormat.format(d)
}

val m = 1000000
val k = 1000

fun formatChargeKOrM(num: Float): String {
    return when {
        num >= m -> {
            val show = num / m
            "${MoneyFormatUtils.getCashFormatPoint2(show)}M"
        }
        num >= k -> {
            val show = num / k
            "${MoneyFormatUtils.getCashFormatPoint2(show)}K"
        }
        else -> MoneyFormatUtils.getCashFormatPoint2(num)
    }
}

/**
 * 读写申请权限二次验证
 */

fun applicationStorageAuthority(act: FragmentActivity, next: () -> Unit) {
    RxPermissions(act).request(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE).subscribe(object : Observer<Boolean> {

        override fun onSubscribe(d: Disposable) {
        }

        override fun onNext(aBoolean: Boolean) {
            if (aBoolean) {
                next()
            } else {
                ToastUtils.showAccountToast(act, "Failure")
            }
        }

        override fun onError(e: Throwable) {
        }

        override fun onComplete() {
        }
    })
}

fun applicationRecordAuthority(act: FragmentActivity, next: () -> Unit) {
    RxPermissions(act).request(Manifest.permission.RECORD_AUDIO, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE).subscribe(object : Observer<Boolean> {

        override fun onSubscribe(d: Disposable) {
        }

        override fun onNext(aBoolean: Boolean) {
            if (aBoolean) {
                next()
            } else {
                ToastUtils.showAccountToast(act, "Failure")
            }
        }

        override fun onError(e: Throwable) {
        }

        override fun onComplete() {
        }
    })
}


fun applicationLiveAuthority(act: FragmentActivity, next: () -> Unit) {
    RxPermissions(act).request(Manifest.permission.RECORD_AUDIO, Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE).subscribe(object : Observer<Boolean> {

        override fun onSubscribe(d: Disposable) {
        }

        override fun onNext(aBoolean: Boolean) {
            if (aBoolean) {
                next()
            } else {
                ToastUtils.showAccountToast(act, "Failure")
            }
        }

        override fun onError(e: Throwable) {
        }

        override fun onComplete() {
        }
    })
}

fun applicationCameraAuthority(act: FragmentActivity, next: () -> Unit) {
    RxPermissions(act).request(Manifest.permission.CAMERA).subscribe(object : Observer<Boolean> {

        override fun onSubscribe(d: Disposable) {}

        override fun onNext(aBoolean: Boolean) {
            if (aBoolean) {
                next()
            } else {
                ToastUtils.showAccountToast(act, "Failure")
            }
        }

        override fun onError(e: Throwable) {}

        override fun onComplete() {}
    })
}

/**
 * 修改字体展示样式并且增加点击
 * @param original 展示全文字符串
 * @param nodes 差异色数组字符
 * @param next 对应后续执行方法
 * @return  {@link #AppCompatTextView}
 */
fun AppCompatTextView.setTextStyleClick(original: String, nodes: MutableList<String>, next: (content: String) -> Unit): AppCompatTextView {
    val spannableString = SpannableString(original)

    nodes.forEach {
        spannableString.setSpan(ForegroundColorSpan(ContextCompat.getColor(context, R.color.common_new_main_tone_fea30f)), spannableString.indexOf(it), spannableString.indexOf(it) + it.length, 0)

        this.movementMethod = LinkMovementMethod.getInstance()

        spannableString.setSpan(object : ClickableSpan() {
            override fun onClick(widget: View) {
                next(it)
            }

            override fun updateDrawState(ds: TextPaint) {
                ds.color = ContextCompat.getColor(context, R.color.common_new_main_tone_fea30f)
                ds.clearShadowLayer()
                ds.isUnderlineText = false
            }

        }, spannableString.indexOf(it), spannableString.indexOf(it) + it.length, 0)

        this.highlightColor = ContextCompat.getColor(context, android.R.color.transparent)
        this.text = spannableString
    }

    return this
}


/**
 * 去除多个连续换行符以及结尾换行符
 */
fun removeLineBreaksString(content: String): String {
    var result = ""
    if (content.isNullOrEmpty().not()) {
        val p: Pattern = Pattern.compile("(\r?\n(\\s*\r?\n)+)")
        val m: Matcher = p.matcher(content)
        result = m.replaceAll("\r\n")
    }
    return result.trim()
}