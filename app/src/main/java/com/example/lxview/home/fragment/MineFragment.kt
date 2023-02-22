package com.example.lxview.home.fragment

import androidx.appcompat.widget.AppCompatImageView
import com.example.baselib.utils.ToastUtils
import com.example.baselib.utils.startAct
import com.example.lxview.R
import com.example.lxview.base.fragment.BaseFragment
import com.example.lxview.base.widget.LineControlView
import com.example.lxview.lxusercenter.activity.HandlerActivity

/**
 * author: 李 祥
 * date:   2022/3/31 3:41 下午
 * description:
 */
class MineFragment: BaseFragment() {

    override val contentId: Int
        get() = R.layout.user_fragment_user_info
    private var iconDrawable: AppCompatImageView?=null
    private var myThread: LineControlView?=null


    override fun initView() {
        iconDrawable = mRootView.findViewById(R.id.mUserInfoHeadPic)
        myThread = mRootView.findViewById(R.id.mUserCenterMyFavorites)

    }

    override fun initListener() {
        iconDrawable?.setOnClickListener {
            context?.let { it1 -> ToastUtils.showToast(it1,"你看你妈呢？") }
        }
        myThread?.setOnClickListener {
            startAct<HandlerActivity>()
        }
    }
}