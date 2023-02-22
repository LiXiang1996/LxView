package com.example.lxview.lxhome.img

import com.bumptech.glide.GenericTransitionOptions
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.baselib.utils.GlideUtils
import com.example.lxview.R
import com.example.lxview.base.activity.BaseBindActivity
import com.example.lxview.databinding.ActivityImgAnimateLayoutBinding

/**
 * author: 李 祥
 * date:   2022/9/22 6:02 下午
 * description:
 */
class ImgAnimationActivity : BaseBindActivity<ActivityImgAnimateLayoutBinding>() {


    override val layout: Int
        get() = R.layout.activity_img_animate_layout

    override fun initView() {
        Glide.with(this).load("https://img1.baidu.com/it/u=2345490792,1976023530&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=281")
            .transition(GenericTransitionOptions.with(R.anim.slide_in_left))
            .skipMemoryCache(false)
            .into(mBinding.imgAnimate)
        Glide.with(this)
            .load("https://img1.baidu.com/it/u=2345490792,1976023530&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=281")
            .transition(GenericTransitionOptions.with(R.anim.scale_in))
            .skipMemoryCache(true)//跳过缓存，下次还是有动画
            .into(mBinding.imgScaleAnimate)

        GlideUtils.loadUrlImageGS(this,"https://img1.baidu.com/it/u=2345490792,1976023530&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=281",mBinding.imgBlurAnimate)

        Glide.with(this)
            .load(R.drawable.big_bg)
            .transition(DrawableTransitionOptions.withCrossFade(8000)) //淡入淡出5s
            .skipMemoryCache(true)
            .into(mBinding.imgFadeAnimate)

    }
}