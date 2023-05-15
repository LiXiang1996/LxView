package com.example.lxview.function.myfreeview.view

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import android.graphics.PorterDuff

import android.graphics.ComposeShader

import android.graphics.Shader

import android.graphics.BitmapShader

import android.graphics.RectF

import android.graphics.RadialGradient

import android.graphics.Bitmap




/**
 * author: 李 祥
 * date:   2021/8/13 8:13 下午
 * description:
 */
class Doraemon @JvmOverloads constructor(context: Context?, attrs: AttributeSet?=null, defStyleAttr: Int = 0) :
    View(context, attrs, defStyleAttr) {

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }
    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
    }

    private var radialGradient:RadialGradient?=null
    var paint:Paint?=null
    var painteye:Paint?=null
    var paintnose:Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    var paintbear:Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    var paintMouth:Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    var paintMouth2:Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    //画一个黑色外框，里面白的眼眶
    var rectF :RectF=RectF(248f,218f,400f,452f)
    var rectF2 :RectF = RectF(400f,218f,552f,452f)
    var rectF3:RectF = RectF(250f,220f,398f,450f)
    var rectF4:RectF = RectF(402f,220f,550f,450f)

    init {
        paint = Paint(Paint.ANTI_ALIAS_FLAG)
        painteye = Paint(Paint.ANTI_ALIAS_FLAG)

    }
    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        /**
         * 添加着色器
        着色器，画笔的shader定义的就是图形的着色和外观
        这么说可能不好理解，个人理解其实就是画渐变色！
         */
        //头
        radialGradient = RadialGradient(400f,500f,300f, Color.parseColor("#89D4FC"), Color.parseColor("#457A97"), Shader.TileMode.REPEAT)
        paint?.shader = radialGradient
        canvas?.drawCircle(400f,500f,300f, paint!!)
        //脸
        val radialGradient2  = RadialGradient(400f,500f,300f, Color.parseColor("#F8F7F7"), Color.parseColor("#E7E7E8"), Shader.TileMode.REPEAT)
        paint?.shader = radialGradient2
        canvas?.drawCircle(400f,560f,240f,paint!!)

        //眼眶，眼白
        painteye?.color = Color.BLACK
        painteye?.let { canvas?.drawOval(rectF, it) }
        painteye?.let { canvas?.drawOval(rectF2, it) }

        painteye?.color = Color.WHITE
        val radialGradient3 = RadialGradient(400f,315f,300f,Color.parseColor("#E7E7E8"),Color.WHITE,Shader.TileMode.REPEAT)
        painteye?.shader = radialGradient3
        canvas?.drawOval(rectF3,painteye!!)
        canvas?.drawOval(rectF4,painteye!!)

        //眼珠子
        painteye?.shader = null
        painteye?.color = Color.BLACK
        canvas?.drawCircle(375f,315f,20f,painteye!!)
        canvas?.drawCircle(425f,315f,20f,painteye!!)

        val radialGradient4 = RadialGradient(390f,520f,60f,Color.parseColor("#FBD7D3"),
            Color.parseColor("#F91E08"),
            Shader.TileMode.REPEAT)
        paintnose.shader = radialGradient4
        canvas?.drawCircle(400f,425f,30f,paintnose)

        paintbear.color = Color.BLACK
        paintbear.strokeWidth = 8f
        //中间的线
        canvas?.drawLine(400f,455f,400f,700f,paintbear)
        //左边胡子
        canvas?.drawLine(50f, 390f, 380f, 490f, paintbear)
        canvas?.drawLine(50f, 520f, 380f, 520f, paintbear)
        canvas?.drawLine(50f, 670f, 380f, 550f, paintbear)

        //右边胡子
        canvas?.drawLine(420f, 490f, 750f, 390f, paintbear)
        canvas?.drawLine(420f, 520f, 750f, 520f, paintbear)
        canvas?.drawLine(420f, 550f, 750f, 670f, paintbear)

        //指定宽高图片
        val bitmap :Bitmap = Bitmap.createBitmap(361,151,Bitmap.Config.ARGB_8888)
        //着色器中心坐标x,y 半径，中心颜色，边缘颜色
        /**
         * Shader.TileMode
         * CLAMP表示，当所画图形的尺寸大于Bitmap的尺寸的时候，会用Bitmap四边的颜色填充剩余空间。
         * REPEAT表示，当我们绘制的图形尺寸大于Bitmap尺寸时，会用Bitmap重复平铺整个绘制的区域。
         * 与REPEAT类似，当绘制的图形尺寸大于Bitmap尺寸时，MIRROR也会用Bitmap重复平铺整个绘图区域，与REPEAT不同的是，两个相邻的Bitmap互为镜像。
         */
        //颜色渐变  嘴巴
        val radialGradient5 = RadialGradient(251f,180f,200f,Color.BLACK,Color.RED,Shader.TileMode.CLAMP)
        paintMouth.shader = radialGradient5
        val canvasMouth1 = Canvas(bitmap)
        canvasMouth1.drawCircle(180f,-30f,180f,paintMouth)
        val bitmapShader = BitmapShader(bitmap,Shader.TileMode.CLAMP,Shader.TileMode.CLAMP)
        paintMouth2.shader = bitmapShader
        //把当前的画布原点平移到制定坐标，这一步是把画的嘴移动下来
        canvas?.translate(220f,600f)
        canvas?.drawRect(0f,0f,361f,151f,paintMouth2)

        //舌头
        val bitmap2 = Bitmap.createBitmap(361, 151, Bitmap.Config.ARGB_8888)
        val mouthPaint2 = Paint()
        //颜色渐变
        mouthPaint2.shader = RadialGradient(
            361f, 151f, 200f,
            Color.parseColor("#FBD7D3"),
            Color.parseColor("#FB7F11"),
            Shader.TileMode.CLAMP
        )
        val mouthCanvas2 = Canvas(bitmap2)
        val rectF = RectF(150f, 50f, 450f, 300f)
        mouthCanvas2.drawOval(rectF, mouthPaint2)
        val bitmapShader2 = BitmapShader(bitmap2, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
        //组合渲染
        val composeShader = ComposeShader(bitmapShader, bitmapShader2, PorterDuff.Mode.SRC_IN)
        paintMouth2.shader = composeShader
        canvas!!.drawRect(100f, 0f, 361f, 151f, paintMouth2)
    }

}