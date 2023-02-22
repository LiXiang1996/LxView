package com.example.lxview.lxhome.img

import com.bumptech.glide.GenericTransitionOptions
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.baselib.utils.CornerTransform
import com.example.baselib.utils.GlideUtils
import com.example.baselib.utils.SizeUtils
import com.example.lxview.R
import com.example.lxview.base.activity.BaseBindActivity
import com.example.lxview.databinding.ActivityImgAnimateLayoutBinding
import com.example.lxview.databinding.ActivityImgShapeLayoutBinding

/**
 * author: 李 祥
 * date:   2022/9/22 6:02 下午
 * description:
 */
class ImgShapeActivity : BaseBindActivity<ActivityImgShapeLayoutBinding>() {


    override val layout: Int
        get() = R.layout.activity_img_shape_layout

    override fun initView() {
        Glide.with(this).load("https://img1.baidu.com/it/u=2345490792,1976023530&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=281")
            .transition(GenericTransitionOptions.with(R.anim.slide_in_left))
            .circleCrop()//设置成圆图
            .into(mBinding.imgShapeCircle)

        val transformation = CornerTransform(this, SizeUtils.dp2px(this, 12f).toFloat())

        Glide.with(this)
            .load("https://img1.baidu.com/it/u=2345490792,1976023530&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=281")
            .transform(transformation)
            .into(mBinding.imgShapeCorners)

        GlideUtils.loadUrlImageGS(this,"https://img1.baidu.com/it/u=2345490792,1976023530&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=281",mBinding.imgBlurAnimate)

        Glide.with(this)
            .load(R.drawable.big_bg)
            .transition(DrawableTransitionOptions.withCrossFade(8000)) //淡入淡出5s
            .into(mBinding.imgFadeAnimate)



    }
}