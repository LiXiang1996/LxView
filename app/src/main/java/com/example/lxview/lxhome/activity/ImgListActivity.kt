package com.example.lxview.lxhome.activity

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lxview.R
import com.example.lxview.base.activity.BaseBindActivity
import com.example.lxview.base.adapter.HomeFucAdapter
import com.example.lxview.base.route.RoutePath
import com.example.lxview.databinding.BaseListActivityLayoutBinding
import com.example.lxview.function.home.bean.ItemBean

/**
 * author: 李 祥
 * date:   2022/9/22 2:16 下午
 * description:对图片的各种处理
 */
class ImgListActivity : BaseBindActivity<BaseListActivityLayoutBinding>() {

    var recyclerView: RecyclerView? = null
    var listAdapter: HomeFucAdapter? = null
    override val layout: Int
        get() = R.layout.base_list_activity_layout


    override fun initView() {
        recyclerView = findViewById(R.id.base_list_rv)
        recyclerView?.layoutManager = LinearLayoutManager(this)
        listAdapter = HomeFucAdapter(R.layout.item_layout_home_func)
        recyclerView?.adapter = listAdapter
    }

    override suspend fun handleData() {
        val navList = mutableListOf(ItemBean().apply {
            title = "图片加动画";avatarRes = R.drawable.yang_1;route = RoutePath.ImgCenter.IMG_ANIMATE
        }, ItemBean().apply {
            title = "图片的展示";avatarRes = R.drawable.yang_2;route = RoutePath.ImgCenter.IMG_SHAPE
        }, ItemBean().apply {
            title = "BottomNavigationView+ViewPager";avatarRes = R.drawable.yang_3
        }, ItemBean().apply {
            title = "BottomNavigationView+RadioButton";avatarRes = R.drawable.yang_4
        }, ItemBean().apply {
            title = "TabLayout+Viewpager";avatarRes = R.drawable.yang_5
        })
        listAdapter?.setNewData(navList)
    }

}