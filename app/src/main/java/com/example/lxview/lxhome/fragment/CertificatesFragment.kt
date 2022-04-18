package com.example.lxview.lxhome.fragment

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lxview.R
import com.example.lxview.base.adapter.BaseItemTypeAdapter
import com.example.lxview.base.fragment.BaseFragment
import com.example.lxview.base.route.RoutePath
import com.example.lxview.function.home.bean.ItemBean

/**
 * author: 李 祥
 * date:   2022/4/15 2:07 下午
 * description:
 */
class CertificatesFragment : BaseFragment() {

    override val contentId: Int
        get() = R.layout.fg_card_bag_certificates
    private var recyclerView: RecyclerView? = null
    private var itemAdapter:BaseItemTypeAdapter?=null
    private var data:MutableList<ItemBean>?=null

    override fun initView() {
        recyclerView = requireActivity().findViewById(R.id.card_bag_certificates_rv)

    }

    override fun initData() {
        data = ArrayList()
        data?.add(ItemBean().apply {
            this.avatarRes =R.drawable.avatar
            this.name = "李 祥"
            this.account = "510521199606261038"
            this.title = "身份证"
            this.tag2 = RoutePath.MainApp.TOOLS_NORMAL
            this.itemType = 2
            this.clickText1 = "查看证件照"
        })
        itemAdapter = BaseItemTypeAdapter(data)
        recyclerView?.layoutManager = LinearLayoutManager(context)
        recyclerView?.adapter =itemAdapter

    }

}