package com.example.lxview.function.myFreeView.View

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View
import android.graphics.RectF




/**
 * author: 李 祥
 * date:   2021/8/14 2:03 下午
 * description:
 */
class Pig @JvmOverloads constructor(
    context: Context?,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) :
    View(context, attrs, defStyleAttr) {

    private var paint:Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var path: Path = Path()
    private var path1: Path = Path()


    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        paint.strokeWidth = 3f
        paint.color = Color.RED
        paint.style = Paint.Style.STROKE

        //画脑壳
        //移动下一次操作的起点位置
        path.moveTo(166f,111f)
        //贝塞尔曲线 控制点坐标 和结束点 起点为166，111，画一个曲线到220，85
        path.quadTo(210f,112f,220f,85f)
        canvas?.drawPath(path,paint)

        //画鼻子
        //画布平移
        canvas?.translate(220f,85f)
        //逆时针20度 因为猪鼻子是斜着的
        canvas?.rotate(-20f)
        //画椭圆
        canvas?.drawOval(-15f,0f,15f,-55f,paint)
        //画鼻孔
        paint.strokeWidth = 8f
        canvas?.drawPoint(7f,-27f,paint)
        canvas?.drawPoint(-7f,-27f,paint)

        //设置画笔线粗细
        paint.strokeWidth = 3f
        //平移,此时绘制原点变成 220 30
        canvas?.translate(0f,-55f)
        //顺时针20度，画布回到水平
        canvas?.rotate(20f)
        path1.quadTo(156f-196,24f-27,127f-196,39f-27)
        //画右耳
        path1.moveTo(125f-196,46f-27)
        path1.quadTo(127f-196,4f-27,110f-196,0f-27)
        path1.quadTo(80f-196,12f-27,111f-196,50f-27)

        //继续画头轮廓
        path1.moveTo(112f-196,47f-27)
        path1.lineTo(92f-196,56f-27)

        //画左耳
        path1.moveTo(95f-196,63f-27)
        path1.cubicTo(80f-196,8f-27,48f-196,16f-27,78f-196,73f-27)

        //继续画头轮廓
        path1.moveTo(74f-196,70f-27)
        path1.quadTo(20f-196,180f-27,112f-196,206f-27)
        path1.quadTo(209f-196,201f-27,176f-196,95f-27)

        //画嘴
        path1.moveTo(113f-196,145f-27)
        path1.cubicTo(140f-196,176f-27,169f-196,171f-27,168f-196,130f-27)

        //画瞳孔
        paint.strokeWidth = 10f
        canvas?.drawPoint(119f-196,70f-27,paint)
        canvas?.drawPoint(150f-196,55f-27,paint)

        //画眼睛
        paint.strokeWidth = 3f
        canvas?.drawOval(106f-196,55f-27,132f-196,87f-27,paint)
        canvas?.drawCircle(150f-196,55f-27,10f,paint)

        //画身体
        path1.moveTo(70f-196,183f-27)
        path1.quadTo(40f-196,209f-27,39f-196,300f-27)
        path1.lineTo(204f-196,300f-27)
        path1.quadTo(203f-196,230f-27,171f-196,186f-27)

        //画手
        path1.moveTo(58f-196,207f-27)
        path1.lineTo(8f-196,233f-27)

        path1.moveTo(174f-196,200f-27)
        path1.lineTo(233f-196,233f-27)
        path1.moveTo(222f-196,240f-27)
        path1.quadTo(200f-196,213f-27,240f-196,215f-27)
        path1.moveTo(3f-196,217f-27)
        path1.quadTo(30f-196,217f-27,17f-196,240f-27)

        //画脚
        path1.moveTo(84f-196,300f-27)
        path1.lineTo(84f-196,332f-27)
        path1.moveTo(152f-196,300f-27)
        path1.lineTo(152f-196,332f-27)

        canvas?.drawPath(path1,paint)

        //还原坐标轴
        canvas?.translate(0f,55f)
        canvas?.translate(-220f,-85f)


        //画脚
        val path2 = Path()
        path2.quadTo(-4f, 5f, 0f, 10f)
        path2.lineTo(30f, 12f)
        path2.quadTo(36f, 5f, 30f, -2f)
        //使起点和终点连接起来，形成一个闭合区域
        path2.close()
        //对闭合区域填充
        paint.style = Paint.Style.FILL_AND_STROKE
        //画一只脚，移动到指定位置
        canvas?.translate(80f, 340f)
        canvas?.drawPath(path2, paint)
        //继续平移
        canvas?.translate(-75f, -330f)
        canvas?.translate(144f, 330f)
        canvas?.drawPath(path2, paint)
        //归位
        canvas?.translate(-144f, -330f)
        paint.style = Paint.Style.STROKE

        //尾巴
        paint.strokeWidth = 2f
        var path3: Path?
        canvas?.translate(40f, 262f)
        var start = 90
        for (i in 0..449) {
            path3 = Path()
            val r = RectF(-12f, -12f, 12f, 12f)
            path3.arcTo(r, start.toFloat(), 1f)
            start++
            canvas?.drawPath(path3, paint)
            canvas?.translate((-0.06).toFloat(), 0f)
        }
    }
}