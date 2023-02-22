package com.example.lxview.function.animation.activity

import android.graphics.drawable.AnimationDrawable
import android.view.animation.*
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatTextView
import com.example.lxview.R
import com.example.lxview.base.activity.BaseActivity

/**
 * author: 李 祥
 * date:   2022/11/24 14:59
 * description:
 */


class TweenAnimationActivity : BaseActivity() {


    private var alphaImg: ImageView? = null
    private var rotateImg: ImageView? = null
    private var translateImg: ImageView? = null
    private var scaleImg: ImageView? = null
    private var animationCodeImg: ImageView? = null
    private var animRun: AnimationDrawable? = null
    private var animStart: AnimationDrawable? = null
    private var runStartOrStopTv: AppCompatTextView? = null
    private var runStartOrStopTv2: AppCompatTextView? = null
    private var alphaTV: AppCompatTextView? = null
    private var rotateTV: AppCompatTextView? = null
    private var translateTv: AppCompatTextView? = null
    private var scaleTV: AppCompatTextView? = null
    var animation: Animation? = null
    var animationRotate: Animation? = null
    override val contentId: Int
        get() = R.layout.animation_tween_activity_layout

    override fun initView() {
        alphaTV = findViewById(R.id.animation_to_alpha)
        rotateTV = findViewById(R.id.animation_to_ratate)
        translateTv = findViewById(R.id.animation_to_translate)
        scaleTV = findViewById(R.id.animation_to_scale)
        animationCodeImg = findViewById(R.id.animation_tween_code)
        alphaImg = findViewById(R.id.animation_tween_alpha)
        rotateImg = findViewById(R.id.animation_tween_rotate)
        translateImg = findViewById(R.id.animation_tween_translate)
        scaleImg = findViewById(R.id.animation_tween_scale)
        runStartOrStopTv = findViewById(R.id.animation_tween_run_control_tv)
        runStartOrStopTv2 = findViewById(R.id.animation_tween_rotate_control)

        animation = AnimationUtils.loadAnimation(this, R.anim.fade_in_10)
        alphaImg?.startAnimation(animation)
        animationRotate = AnimationUtils.loadAnimation(this, R.anim.img_rotate_animation)
        rotateImg?.startAnimation(animationRotate)

        val animationTranslate = AnimationUtils.loadAnimation(this, R.anim.slide_in_left_10)
        translateImg?.startAnimation(animationTranslate)

        val animationScale = AnimationUtils.loadAnimation(this, R.anim.scale_in)
        scaleImg?.startAnimation(animationScale)

    }

    override fun initData() {

    }

    override fun initListener() {
        //代码实现补间动画
        val animationCodeAlpha = AlphaAnimation(0f, 1f)
        animationCodeAlpha.duration = 3000 //动画执行世间
        animationCodeAlpha.fillAfter = false //是否保存最后状态，为true就会保存，false执行完后会恢复原样
        animationCodeAlpha.repeatCount = 2 //执行次数，-1  为无数次

        val animationCodeRotate = RotateAnimation(0f, 360f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)
        animationCodeRotate.duration = 3000
        animationCodeRotate.fillAfter = false

        val animationCodeTranslate = TranslateAnimation(-200f, 0F, 0f, -100f)
        animationCodeTranslate.duration = 3000
        animationCodeTranslate.fillAfter = false

        val animationCodeScale = ScaleAnimation(0f, 1f, 0f, 1f)
        animationCodeScale.duration = 3000
        animationCodeScale.fillAfter = false

        alphaTV?.setOnClickListener {
            animationCodeImg?.animation = animationCodeAlpha
            animationCodeAlpha.start()
        }
        rotateTV?.setOnClickListener {
            animationCodeImg?.animation = animationCodeRotate
            animationCodeRotate.start()
        }
        translateTv?.setOnClickListener {
            animationCodeImg?.animation = animationCodeTranslate
            animationCodeTranslate.start()
        }
        scaleTV?.setOnClickListener {
            animationCodeImg?.animation = animationCodeScale
            animationCodeScale.start()
        }


        runStartOrStopTv?.setOnClickListener {
            animation = AnimationUtils.loadAnimation(this, R.anim.fade_out)
            animation?.fillAfter = true
            alphaImg?.startAnimation(animation)
        }
        animationRotate?.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {
                runStartOrStopTv2?.text = "旋转开始了"
            }

            override fun onAnimationEnd(animation: Animation?) {
                runStartOrStopTv2?.text = "旋转结束了"
            }

            override fun onAnimationRepeat(animation: Animation?) {
            }

        })


    }

    override fun onDestroy() {
        super.onDestroy()
        animRun?.stop()
    }


}