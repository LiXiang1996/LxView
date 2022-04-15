package com.example.lxview.home.fragment

import androidx.appcompat.widget.AppCompatImageView
import com.example.baselib.utils.ToastUtils
import com.example.lxview.R
import com.example.lxview.base.fragment.BaseFragment

/**
 * author: 李 祥
 * date:   2022/3/31 3:43 下午
 * description:
 */
class RelaxFragment: BaseFragment() {
    override val contentId: Int
        get() = R.layout.fg_relax
    private var iconDrawable: AppCompatImageView?=null


    override fun initView() {
        iconDrawable = mRootView.findViewById(R.id.fg_relax_img)

    }

    override fun initListener() {
        iconDrawable?.setOnClickListener {
            context?.let { it1 -> ToastUtils.showToast(it1,"你看你妈呢？") }
        }
    }
}