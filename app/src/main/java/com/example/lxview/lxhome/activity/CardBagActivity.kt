package com.example.lxview.lxhome.activity

import androidx.viewpager.widget.ViewPager
import com.example.baselib.utils.ToastUtils
import com.example.lxview.R
import com.example.lxview.base.activity.BaseBindActivity
import com.example.lxview.base.fragment.BaseFragment
import com.example.lxview.base.widget.ToolsBarView
import com.example.lxview.databinding.LxhomeCardBagActivityLayoutBinding
import com.example.lxview.login.LXConstant
import com.example.lxview.lxhome.adapter.CardBagPageAdapter
import com.example.lxview.lxhome.fragment.CardVoucherFragment
import com.example.lxview.lxhome.fragment.CertificatesFragment
import com.example.lxview.lxhome.fragment.TicketFragment
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
    private var pagerAdapter: CardBagPageAdapter? = null
    private var fragments: MutableList<BaseFragment>? = null
    private var titles: MutableList<String>? = ArrayList()


    override fun initView() {
        toolsBarView = findViewById(R.id.card_bag_toolbar)
        toolsBarView?.setRightTextIconVisibility(false)
        tabLayout = findViewById(R.id.card_bag_tab_layout)
        viewPager = findViewById(R.id.card_bag_viewpager)

        tabLayout?.setupWithViewPager(viewPager) //将TabLayout与ViewPager绑定

        tabLayout?.tabMode = TabLayout.MODE_FIXED
        tabLayout?.tabGravity = TabLayout.GRAVITY_FILL
        tabLayout?.setTabTextColors(R.color.black, R.color.blue)
        with(tabLayout) { //在这儿不对tab的标题进行赋值，因为setupWithViewPager会把所以tittle清除掉，所以放在pageradapter中
            this?.addTab(newTab())
            this?.addTab(newTab())
            this?.addTab(newTab())
        }
        titles?.addAll(arrayOf("证件", "票", "卡券"))
        fragments = ArrayList()
        fragments?.add(CertificatesFragment())
        fragments?.add(TicketFragment())
        fragments?.add(CardVoucherFragment())
        pagerAdapter = fragments?.let { it ->
            titles?.let { it2 ->
                CardBagPageAdapter(supportFragmentManager, 3, it, it2)
            }
        }
        viewPager?.adapter = pagerAdapter
        toolsBarView?.setRightImgIconVisibility(false)
        viewPager?.currentItem = 0
    }


    override fun initListener() {
        toolsBarView?.addListener("卡包", "") { str ->
            when (str) {
                LXConstant.TOOLBAR_LEFT -> finish()
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