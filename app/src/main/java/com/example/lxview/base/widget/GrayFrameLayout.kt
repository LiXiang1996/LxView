package com.example.lxview.base.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.graphics.Paint
import android.util.AttributeSet
import android.widget.FrameLayout

/**
 * author: 李 祥
 * date:   2022/6/22 6:04 下午
 * description: 实现app黑白色主题
 */
class GrayFrameLayout(context: Context, attrs: AttributeSet?) : FrameLayout(context, attrs) {
    private val mPaint: Paint = Paint()
    override fun dispatchDraw(canvas: Canvas) {
        canvas.saveLayer(null, mPaint, Canvas.ALL_SAVE_FLAG)
        super.dispatchDraw(canvas)
        canvas.restore()
    }

    override  fun draw(canvas: Canvas) {
        canvas.saveLayer(null, mPaint, Canvas.ALL_SAVE_FLAG)
        super.draw(canvas)
        canvas.restore()
    }

    init {
        val cm = ColorMatrix()
        cm.setSaturation(0F)
        mPaint.colorFilter = ColorMatrixColorFilter(cm)
    }
}
