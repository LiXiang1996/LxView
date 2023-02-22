package com.example.baselib.utils

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.RequestOptions.bitmapTransform
import com.example.baselib.R

import java.lang.Exception
import java.util.*


/**
 * Created by lixiang on 2022/2/28
 * Glide工具类
 */
object GlideUtils {
    /**
     * 当fragment或者activity失去焦点或者destroyed的时候，Glide会自动停止加载相关资源，确保资源不会被浪费
     */
    fun loadUrlCenterCropImage(context: Context, url: String, imageView: ImageView) {
        try {
            if (url.contains(".webp")) {
                val centerCrop = CenterCrop()
                Glide.with(context).load(url).optionalTransform(centerCrop).into(imageView)
            } else {
                val options = RequestOptions().placeholder(R.drawable.ic_glide_place_img).error(R.drawable.ic_glide_place_img).centerCrop()
                Glide.with(context).load(url).apply(options).into(imageView)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }



    fun loadUrlCenterCropImages(context: Context, url: String, imageView: ImageView) {
        try {
            if (url.contains(".webp")) {
                val centerCrop = CenterCrop()
                Glide.with(context).load(url).optionalTransform(centerCrop).into(imageView)
            } else {
                val options = RequestOptions().placeholder(R.drawable.ic_glide_place_img).error(R.drawable.ic_glide_place_img).centerCrop()
                Glide.with(context).load(url).apply(options).into(imageView)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


    fun loadCenterCropImage(context: Context, url: String, imageView: ImageView) {
        val options = RequestOptions().centerCrop()
        try {
            Glide.with(context).load(url).apply(options).into(imageView)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun loadUrlAngle(context: Context, url: String, imageView: ImageView) {
        val options = RequestOptions().placeholder(R.drawable.ic_glide_place_img).error(R.drawable.ic_glide_place_img).fitCenter()
        try {
            Glide.with(context).load(url).apply(options).skipMemoryCache(true).into(imageView)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun loadUrlAuthAngle(context: Context, url: String, imageView: ImageView) {
        val transformation = CornerTransform(context, SizeUtils.dp2px(context, 4f).toFloat())
        val options = RequestOptions().placeholder(R.drawable.ic_glide_place_img).error(R.drawable.ic_glide_place_img)
        try {
            Glide.with(context).load(url).skipMemoryCache(true).transform(transformation).apply(options).into(imageView)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun loadAuthForURIAngle(context: Context, url: Uri, imageView: ImageView) {
        val transformation = CornerTransform(context, SizeUtils.dp2px(context, 4f).toFloat())
        val options = RequestOptions().placeholder(R.drawable.ic_glide_place_img).error(R.drawable.ic_glide_place_img)
        try {
            Glide.with(context).load(url).skipMemoryCache(true).transform(transformation).apply(options).into(imageView)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * 测试一下
     */
    fun loadUrlGameAngle(context: Context, url: String, imageView: ImageView, placeholder: Int, error: Int) {
        val transformation = CornerTransform(context, SizeUtils.dp2px(context, 10f).toFloat())
        //只是绘制左上角和右上角圆角
        transformation.setExceptCorner(false, false, true, true)

        val options = RequestOptions().placeholder(placeholder).error(error)
        try {
            Glide.with(context).load(url).skipMemoryCache(true).transform(transformation).apply(options).into(imageView)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun loadUrlCustomImage(context: Context, url: String, imageView: ImageView, placeholder: Int, error: Int) {
        val options = RequestOptions().placeholder(placeholder).error(error).centerCrop()
        try {
            Glide.with(context).load(url).apply(options).into(imageView)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun loadUrlCustomImage(context: Context, url: String, imageView: ImageView) {
        val options = RequestOptions().centerCrop()
        try {
            Glide.with(context).load(url).apply(options).into(imageView)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun loadUrlFitCenterImage(context: Context, url: String, imageView: ImageView) {
        val options = RequestOptions().placeholder(R.drawable.ic_glide_place_img).error(R.drawable.ic_glide_place_img).fitCenter()
        try {
            Glide.with(context).load(url).apply(options).into(imageView)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * gif默认占位图
     */
    fun loadUrlGifImage(context: Context, url: String, imageView: ImageView) {
        val options = RequestOptions().error(R.drawable.ic_placeholder_loading).fitCenter()
        try {
            Glide.with(context).load(url).apply(options).thumbnail(Glide.with(context).load(R.drawable.ic_placeholder_loading)).thumbnail(0.1f).into(imageView)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun loadRoundCornerUrlImage(context: Context, url: String, imageView: ImageView, corner: Int) {
        val transformation = CornerTransform(context, SizeUtils.dp2px(context, corner.toFloat()).toFloat())
        try {
            Glide.with(context).load(url).transform(transformation).into(imageView)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    fun loadResUrlImage(context: Context, url: Int, imageView: ImageView, corner: Int) {
        val transformation = CornerTransform(context, SizeUtils.dp2px(context, corner.toFloat()).toFloat())
        try {
            Glide.with(context).load(url).transform(transformation).into(imageView)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    fun loadRoundCornerUrlImage2(context: Context, url: String, imageView: ImageView, corner: Int,width:Int,height:Int) {
        val transformation = CornerTransform(context, SizeUtils.dp2px(context, corner.toFloat()).toFloat())
        try {
            Glide.with(context).load(url).transform(transformation)
                .centerInside()
                .override(width,height)
                .into(imageView)

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * 加载高斯模糊
     */
    fun loadUrlImageGS(context: Context, url: String, imageView: ImageView) {
        val multi = MultiTransformation<Bitmap>(Blur(10))
        val options = RequestOptions().placeholder(R.drawable.ic_glide_place_img).error(R.drawable.ic_glide_place_img).skipMemoryCache(true)
        try {
            Glide.with(context).load(url).apply(options).apply(bitmapTransform(multi)).into(imageView)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}