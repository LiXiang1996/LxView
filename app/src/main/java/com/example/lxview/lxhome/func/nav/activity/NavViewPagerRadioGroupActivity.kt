package com.example.lxview.lxhome.func.nav.activity

import android.widget.RadioGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.lxview.R
import com.example.lxview.base.activity.BaseBindActivity
import com.example.lxview.base.fragment.BaseFragment
import com.example.lxview.databinding.NavViewpagerRadiogroupActivityLayoutBinding
import com.example.lxview.home.fragment.*
/**
 * author: 李 祥
 * date:   2022/6/28 8:48 下午
 * description:
 */
class NavViewPagerRadioGroupActivity : BaseBindActivity<NavViewpagerRadiogroupActivityLayoutBinding>() {

    override val layout: Int
        get() = R.layout.nav_viewpager_radiogroup_activity_layout

    private lateinit var homeFragment: HomeFragment
    private lateinit var toolsFragment: ToolsFragment
    private lateinit var relaxFragment: RelaxFragment
    private lateinit var viewPager: ViewPager
    private lateinit var radioGroup: RadioGroup
    private lateinit var meFragment: MineFragment
    private lateinit var playFragment: PlayFragment
    private lateinit var fragments: Array<BaseFragment>
    private val titles = arrayOf("首页", "视频", "用户", "音乐", "我的")


    override fun initView() {
        initFragment()
        viewPager = mBinding.fragmentContainerViewpager
        radioGroup = mBinding.navRadiogroup //绑定fragment列表
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

        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) { //点击切换viewpager页面
                R.id.nav_radio_button_1 -> {
                    viewPager.currentItem = 0
                }
                R.id.nav_radio_button_2 -> {
                    viewPager.currentItem = 1
                }
                R.id.nav_radio_button_3 -> {
                    viewPager.currentItem = 2
                }
                R.id.nav_radio_button_4 -> {
                    viewPager.currentItem = 3
                }
                R.id.nav_radio_button_5 -> {
                    viewPager.currentItem = 4
                }
            }
        }
        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) { //当页面左右滑动发生改变时，radiogroup也改变子项的选择状态
                when (position) {
                    0 -> radioGroup.check(R.id.nav_radio_button_1)
                    1 -> radioGroup.check(R.id.nav_radio_button_2)
                    2 -> radioGroup.check(R.id.nav_radio_button_3)
                    3 -> radioGroup.check(R.id.nav_radio_button_4)
                    4 -> radioGroup.check(R.id.nav_radio_button_5)
                }
            }

            override fun onPageScrollStateChanged(state: Int) {
            }

        })
    }

    private fun initFragment() {
        homeFragment = HomeFragment()
        toolsFragment = ToolsFragment()
        relaxFragment = RelaxFragment()
        meFragment = MineFragment()
        playFragment = PlayFragment()
        fragments = arrayOf(homeFragment, toolsFragment, playFragment, relaxFragment, meFragment)
    }
}