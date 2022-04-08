package com.example.lxview.login.utils

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.view.View
import android.view.animation.Animation
import android.view.animation.CycleInterpolator
import android.view.animation.OvershootInterpolator
import android.view.animation.TranslateAnimation

/**
 * author: 李 祥
 * date:   2022/3/8 6:13 下午
 * description:
 */
object ViewShakeUtils {

    /**
     * 抖动动画 缩放
     */
    fun jiggleAnimation(view: View) {
        val animatorSet = AnimatorSet()
        val scaleXAnimator = ObjectAnimator.ofFloat(view, "scaleX", 1f, 0.95f, 1f)
        val scaleYAnimator = ObjectAnimator.ofFloat(view, "scaleY", 1f, 0.95f, 1f) // 添加动画
        animatorSet.play(scaleXAnimator).with(scaleYAnimator) // 设置时间等
        animatorSet.duration = 200
        animatorSet.start()
    }


    /**
     * 晃动动画
     *
     * @param counts 0.5秒钟晃动多少下 横向抖动
     * @return
     */
    fun shakeAnimation(counts: Int, view: View) {
        val translateAnimation: Animation = TranslateAnimation(-1f, 1f, 0f, 0f) //设置一个循环加速器，使用传入的次数就会出现摆动的效果。
        translateAnimation.interpolator = CycleInterpolator(counts.toFloat())
        translateAnimation.duration = 200
        translateAnimation.repeatCount = counts
        view.startAnimation(translateAnimation)
    }

    /**
     * 0.1秒钟摇动3下 纵向抖动
     * @param counts
     * @param view
     */
    fun overshootAnimation(counts: Int, view: View) {
        val animation = TranslateAnimation(0f, 0f, -2f, 2f)
        animation.interpolator = OvershootInterpolator()
        animation.duration = 200
        animation.repeatCount = counts
        animation.repeatMode = Animation.REVERSE
        view.startAnimation(animation)
    }

}