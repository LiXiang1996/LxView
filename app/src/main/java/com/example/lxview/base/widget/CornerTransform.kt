package com.example.lxview.base.widget

import android.content.Context
import android.graphics.*
import androidx.annotation.NonNull
import com.bumptech.glide.Glide
import com.bumptech.glide.load.Transformation
import com.bumptech.glide.load.engine.Resource
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool
import com.bumptech.glide.load.resource.bitmap.BitmapResource
import java.security.MessageDigest


/**
 * author: 李 祥
 * date:   2022/8/15 4:47 下午
 * description:
 */

@Suppress("unused")
class CornerTransform(context: Context, radius: Float) : Transformation<Bitmap?> {
    private val mBitmapPool: BitmapPool //bitmap池：Glide用于Bitmap复用
    private var radius: Float
    private var isLeftTop = false
    private var isRightTop = false
    private var isLeftBottom = false
    private var isRightBottom = false


    init {
        mBitmapPool = Glide.get(context).bitmapPool
        this.radius = radius

    }

    /**
     * 需要设置圆角的部分
     *
     * @param leftTop     左上角
     * @param rightTop    右上角
     * @param leftBottom  左下角
     * @param rightBottom 右下角
     */
    fun setNeedCorner(leftTop: Boolean, rightTop: Boolean, leftBottom: Boolean, rightBottom: Boolean) {
        isLeftTop = leftTop
        isRightTop = rightTop
        isLeftBottom = leftBottom
        isRightBottom = rightBottom
    }


    override fun transform(@NonNull context: Context, @NonNull resource: Resource<Bitmap?>, outWidth: Int, outHeight: Int): Resource<Bitmap?> {
        val source: Bitmap = resource.get()
        var finalWidth: Int
        var finalHeight: Int //输出目标的宽高或高宽比例
        var scale: Float
        if (outWidth > outHeight) { //如果 输出宽度 > 输出高度 求高宽比
            scale = outHeight.toFloat() / outWidth.toFloat()
            finalWidth = source.width //固定原图宽度,求最终高度
            finalHeight = (source.width.toFloat() * scale).toInt()
            if (finalHeight > source.height) { //如果 求出的最终高度 > 原图高度 求宽高比
                scale = outWidth.toFloat() / outHeight.toFloat()
                finalHeight = source.height //固定原图高度,求最终宽度
                finalWidth = (source.height.toFloat() * scale).toInt()
            }
        } else if (outWidth < outHeight) { //如果 输出宽度 < 输出高度 求宽高比
            scale = outWidth.toFloat() / outHeight.toFloat()
            finalHeight = source.height //固定原图高度,求最终宽度
            finalWidth = (source.height.toFloat() * scale).toInt()
            if (finalWidth > source.width) { //如果 求出的最终宽度 > 原图宽度 求高宽比
                scale = outHeight.toFloat() / outWidth.toFloat()
                finalWidth = source.width
                finalHeight = (source.width.toFloat() * scale).toInt()
            }
        } else { //如果 输出宽度=输出高度
            finalHeight = source.height
            finalWidth = finalHeight
        }

        //修正圆角
        radius *= finalHeight.toFloat() / outHeight.toFloat()
        val outBitmap: Bitmap = mBitmapPool[finalWidth, finalHeight, Bitmap.Config.ARGB_8888]
        val canvas = Canvas(outBitmap)
        val paint = Paint() //关联画笔绘制的原图bitmap
        val shader = BitmapShader(source, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP) //计算中心位置,进行偏移
        val width: Int = (source.width - finalWidth) / 2
        val height: Int = (source.height - finalHeight) / 2
        if (width != 0 || height != 0) {
            val matrix = Matrix()
            matrix.setTranslate((-width).toFloat(), (-height).toFloat())
            shader.setLocalMatrix(matrix)
        }
        paint.shader = shader
        paint.isAntiAlias = true
        val rectF = RectF(0.0f, 0.0f, canvas.width.toFloat(), canvas.height.toFloat())

        //先绘制圆角矩形
        canvas.drawRoundRect(rectF, radius, radius, paint)
        if (!isLeftTop) { //左上角不为圆角
            canvas.drawRect(0f, 0f, radius, radius, paint)
        } //右上角不为圆角
        if (!isRightTop) {
            canvas.drawRect(canvas.width - radius, 0f, canvas.width.toFloat(), radius, paint)
        } //左下角不为圆角
        if (!isLeftBottom) {
            canvas.drawRect(0f, canvas.height - radius, radius, canvas.height.toFloat(), paint)
        } //右下角不为圆角
        if (!isRightBottom) {
            canvas.drawRect(canvas.width - radius, canvas.height - radius, canvas.width.toFloat(), canvas.height.toFloat(), paint)
        }
        return BitmapResource.obtain(outBitmap, mBitmapPool)!!
    }

    override fun updateDiskCacheKey(@NonNull messageDigest: MessageDigest) {

    }

}
