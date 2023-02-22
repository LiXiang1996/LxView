package com.example.lxview.lxhome.func.nav.activity

import android.content.res.ColorStateList
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.FragmentTransaction
import androidx.viewpager.widget.ViewPager
import com.example.lxview.R
import com.example.lxview.base.activity.BaseBindActivity
import com.example.lxview.base.fragment.BaseFragment
import com.example.lxview.databinding.NavMenuActivityLayoutBinding
import com.example.lxview.databinding.NavViewpagerTablayoutActivityLayoutBinding
import com.example.lxview.home.fragment.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.tabs.TabLayout

/**
 * author: 李 祥
 * date:   2022/6/28 8:48 下午
 * description:
 */
class NavViewPagerTabActivity : BaseBindActivity<NavViewpagerTablayoutActivityLayoutBinding>() {

    override val layout: Int
        get() = R.layout.nav_viewpager_tablayout_activity_layout

    private lateinit var homeFragment: HomeFragment
    private lateinit var toolsFragment: ToolsFragment
    private lateinit var relaxFragment: RelaxFragment
    private lateinit var viewPager: ViewPager
    private lateinit var tabLayout: TabLayout
    private lateinit var meFragment: MineFragment
    private lateinit var playFragment: PlayFragment
    private lateinit var fragments: Array<BaseFragment>
    private val titles = arrayOf("首页", "视频", "用户")


    override fun initView() {
        initFragment()
        viewPager = mBinding.fragmentContainerViewpager
        tabLayout = mBinding.navTablayout
        tabLayout.setupWithViewPager(viewPager) //        for (element in titles) tabLayout.addTab(tabLayout.newTab())
        viewPager.adapter = object : FragmentPagerAdapter(supportFragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
            override fun getCount(): Int {
                return fragments.size
            }

            override fun getItem(position: Int): Fragment {
                return fragments[position]
            }

            override fun getPageTitle(position: Int): CharSequence {
                return titles[position]
            }
        }
    }

    override fun initListener() {

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })
    }

    private fun initFragment() {
        homeFragment = HomeFragment()
        toolsFragment = ToolsFragment()
        relaxFragment = RelaxFragment()
        meFragment = MineFragment()
        playFragment = PlayFragment() //        fragments = arrayOf(homeFragment, toolsFragment, playFragment, relaxFragment, meFragment)
        fragments = arrayOf(homeFragment, toolsFragment, meFragment)
    }
}