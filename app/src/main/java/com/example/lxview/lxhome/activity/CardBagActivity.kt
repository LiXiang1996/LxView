package com.example.lxview.lxhome.activity

import androidx.viewpager.widget.ViewPager
import com.example.baselib.utils.ToastUtils
import com.example.lxview.R
import com.example.lxview.base.activity.BaseBindActivity
import com.example.lxview.base.widget.ToolsBarView
import com.example.lxview.databinding.LxhomeCardBagActivityLayoutBinding
import com.example.lxview.login.LXConstant
import com.example.lxview.lxhome.adapter.PageAdapter
import com.google.android.material.tabs.TabLayout


/**
 * author: 李 祥
 * date:   2022/4/13 8:07 下午
 * description:这个界面主要是实现的是tablayout+viewpager+fragmengt
 */
class CardBagActivity : BaseBindActivity<LxhomeCardBagActivityLayoutBinding>() {

    override val layout: Int
        get() = R.layout.lxhome_card_bag_activity_layout

    private var toolsBarView: ToolsBarView? = null
    private var tabLayout: TabLayout? = null
    private var viewPager: ViewPager? = null


    override fun initView() {
        toolsBarView = findViewById(R.id.card_bag_toolbar)
        tabLayout = findViewById(R.id.card_bag_tab_layout)
        viewPager = findViewById<ViewPager>(R.id.card_bag_viewpager)

        tabLayout?.tabMode = TabLayout.MODE_FIXED
        tabLayout?.tabGravity = TabLayout.GRAVITY_FILL
        with(tabLayout) {
            this?.addTab(newTab().setText("证件"))
            this?.addTab(newTab().setText("票"))
            this?.addTab(newTab().setText("卡券"))
        }
        viewPager?.adapter = PageAdapter(supportFragmentManager, 3)
        toolsBarView?.setRightImgIconVisibility(false)



    }

    override fun initListener() {
        toolsBarView?.addListener("卡包","") { str ->
            when (str) {
                LXConstant.TOOLBAR_LEFT-> finish()
                LXConstant.TOOLBAR_RIGHT -> ToastUtils.showToast(this, "下一页")
            }
        }

        //绑定tab点击事件
        tabLayout?.setOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager?.currentItem = tab.position
            }
            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })


    }


}