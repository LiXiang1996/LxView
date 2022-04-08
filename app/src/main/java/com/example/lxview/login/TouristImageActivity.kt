//package com.example.lxview.login
//
//import android.view.View
//import com.example.lxview.R
//import com.example.lxview.base.activity.BaseDataBindActivity //import com.gyf.immersionbar.ImmersionBar
//
//import com.example.lxview.login.activity.LoginActivity
//import com.example.lxview.base.ext.openActivity
//
///**
// * @author: JayQiu
// * @date: 2022/3/17
// * @description:
// */
//class TouristImageActivity : BaseDataBindActivity<ActivityTouristImageBinding>() {
//    override val layout: Int = R.layout.activity_tourist_image
//    override fun initView() {
//        super.initView()
////        mBinding.viewTitle.setFullScreen(ImmersionBar.getStatusBarHeight(this))
//        mBinding.viewTitle.getRightView().visibility = View.VISIBLE
//        mBinding.viewTitle.setRightText(getString(R.string.login))
//    }
//
//    override fun initListener() {
//        super.initListener()
//        mBinding.viewTitle.getLeftView().setOnClickListener {
//            finish()
//        }
//        mBinding.viewTitle.getRightView()
//            .setOnClickListener { openActivity(LoginActivity::class.java) }
//        mBinding.touristTvNext.setOnClickListener { //to 设置头像
//        }
//    }
//}