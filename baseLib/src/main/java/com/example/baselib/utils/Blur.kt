package com.example.baselib.utils

import android.content.Context
import android.graphics.*
import androidx.annotation.NonNull
import com.bumptech.glide.load.Key
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation
import java.security.MessageDigest



/**
 * 这个用来为Glide实现高斯模糊
 */
class Blur @JvmOverloads constructor(private val radius: Int = MAX_RADIUS, private val sampling: Int = DEFAULT_DOWN_SAMPLING) : BitmapTransformation() {


    override fun transform(pool: BitmapPool, toTransform: Bitmap, outWidth: Int, outHeight: Int): Bitmap {

        val width = toTransform.width
        val height = toTransform.height
        val scaledWidth = width / sampling
        val scaledHeight = height / sampling

        var bitmap = pool.get(scaledWidth, scaledHeight, Bitmap.Config.RGB_565)

        val canvas = Canvas(bitmap)
        canvas.scale(1 / sampling.toFloat(), 1 / sampling.toFloat())
        val paint = Paint()
        paint.flags = Paint.FILTER_BITMAP_FLAG
        val src = Rect(0, 0, toTransform.width, toTransform.height)

        canvas.drawBitmap(toTransform, src, src, paint)
        bitmap = FastBlur.doBlur(bitmap, radius, true)

        return bitmap    }


    override fun toString(): String {
        return "BlurTransformation(radius=$radius, sampling=$sampling)"
    }

    override fun equals(o: Any?): Boolean {
        return o is Blur &&
                o.radius == radius &&
                o.sampling == sampling
    }

    override fun hashCode(): Int {
        return ID.hashCode() + radius * 1000 + sampling * 10
    }

    override fun updateDiskCacheKey(@NonNull messageDigest: MessageDigest) {
        messageDigest.update((ID + radius + sampling).toByteArray(Key.CHARSET))
    }


    companion object {

        private val VERSION = 1
        private val ID = "jp.wasabeef.glide.transformations.BlurTransformation.$VERSION"

        private val MAX_RADIUS = 25
        private val DEFAULT_DOWN_SAMPLING = 10
    }
}
