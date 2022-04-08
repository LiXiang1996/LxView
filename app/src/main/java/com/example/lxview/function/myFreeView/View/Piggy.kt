package com.example.lxview.function.myFreeView.View

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View

/**
 * author: 李 祥
 * date:   2021/8/14 2:03 下午
 * description:
 */
class Piggy @JvmOverloads constructor(
    context: Context?,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) :
    View(context, attrs, defStyleAttr) {

    private var paint:Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var path: Path = Path()
    private var path1: Path = Path()


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        paint.strokeWidth = 3f
        paint.color = Color.RED
        paint.style = Paint.Style.STROKE

        //移动下一次操作的起点位置
        path.moveTo(166f,111f)
        //贝塞尔曲线 控制点坐标 和结束点 起点为166，111，画一个曲线到220，85
        path.quadTo(210f,112f,220f,85f)
        canvas?.drawPath(path,paint)

    }



}