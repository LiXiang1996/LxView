package com.example.lxview.home.fragment

import android.annotation.SuppressLint
import android.view.MotionEvent
import android.view.View
import android.widget.ScrollView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.baselib.utils.ToastUtils
import com.example.lxview.R
import com.example.lxview.base.adapter.BaseItemTypeAdapter
import com.example.lxview.base.fragment.BaseFragment
import com.example.lxview.data.LocaleData


/**
 * author: 李 祥
 * date:   2022/3/31 3:43 下午
 * description:
 */
class RelaxFragment : BaseFragment() {
    override val contentId: Int
        get() = R.layout.fg_relax
    private var iconDrawable: AppCompatTextView? = null
    private var recyclerView: RecyclerView? = null
//    private var scrollView: ScrollView? = null
    private var mAdapter: BaseItemTypeAdapter? = null


    override fun initView() {
        iconDrawable = mRootView.findViewById(R.id.fg_relax_img)
        recyclerView = mRootView.findViewById(R.id.fg_relax_rv)
//        scrollView = mRootView.findViewById(R.id.fg_relax_scroll)
        recyclerView?.layoutManager = LinearLayoutManager(requireContext())
        mAdapter = BaseItemTypeAdapter(LocaleData.getTestLocaleData())
        recyclerView?.adapter = mAdapter
    }

    override fun initListener() {
        iconDrawable?.setOnClickListener {
            context?.let { it1 -> ToastUtils.showToast(it1, "你看你妈呢？") }
        }
    }

    override fun initData() {
        super.initData()
    }
}