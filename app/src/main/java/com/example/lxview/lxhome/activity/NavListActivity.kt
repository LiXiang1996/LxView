package com.example.lxview.lxhome.activity

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lxview.R
import com.example.lxview.base.activity.BaseBindActivity
import com.example.lxview.base.adapter.BaseAdapter
import com.example.lxview.base.route.RoutePath
import com.example.lxview.databinding.BaseListActivityLayoutBinding
import com.example.lxview.function.home.bean.ItemBean

/**
 * author: 李 祥
 * date:   2022/6/28 2:16 下午
 * description:
 */
class NavListActivity : BaseBindActivity<BaseListActivityLayoutBinding>() {

    var recyclerView: RecyclerView? = null
    var listAdapter: BaseAdapter? = null
    override val layout: Int
        get() = R.layout.base_list_activity_layout


    override fun initView() {
        recyclerView = findViewById(R.id.base_list_rv)
        recyclerView?.layoutManager = LinearLayoutManager(this)
        listAdapter = BaseAdapter(R.layout.item_layout_home_func)
        recyclerView?.adapter = listAdapter
    }

    override suspend fun handleData() {
        val navList = mutableListOf(ItemBean().apply {
            title = "BottomNavigationView+Menu+Fragment";avatarRes = R.drawable.yang_1;route = RoutePath.MainApp.NAV_MENU_ACT
        }, ItemBean().apply {
            title = "BottomNavigationView+自定义控件+Fragment";avatarRes = R.drawable.yang_2
        }, ItemBean().apply {
            title = "BottomNavigationView+ViewPager";avatarRes = R.drawable.yang_3
        }, ItemBean().apply {
            title = "ViewPager+RadioButton+Fragment";avatarRes = R.drawable.yang_4;route =RoutePath.MainApp.NAV_VIEWPAGER_GROUP_RADIO
        }, ItemBean().apply {
            title = "TabLayout+Viewpager";avatarRes = R.drawable.yang_5;route = RoutePath.MainApp.NAV_VIEWPAGER_TAB
        })
        listAdapter?.setNewData(navList)
    }

}