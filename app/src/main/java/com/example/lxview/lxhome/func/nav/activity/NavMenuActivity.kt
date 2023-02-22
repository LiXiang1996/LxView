package com.example.lxview.lxhome.func.nav.activity

import android.content.res.ColorStateList
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.lxview.R
import com.example.lxview.base.activity.BaseBindActivity
import com.example.lxview.base.fragment.BaseFragment
import com.example.lxview.databinding.NavMenuActivityLayoutBinding
import com.example.lxview.home.fragment.*
import com.google.android.material.bottomnavigation.BottomNavigationView

/**
 * author: 李 祥
 * date:   2022/6/28 8:48 下午
 * description:
 */
class NavMenuActivity : BaseBindActivity<NavMenuActivityLayoutBinding>() {

    override val layout: Int
        get() = R.layout.nav_menu_activity_layout

    private lateinit var homeFragment: HomeFragment
    private lateinit var toolsFragment: ToolsFragment
    private lateinit var relaxFragment: RelaxFragment
    private lateinit var meFragment: MineFragment
    private lateinit var playFragment: PlayFragment
    private lateinit var fragments: Array<BaseFragment>
    private var mTextMessage: TextView? = null

    //监听Item
    private val mOnNavigationItemSelectedListener: BottomNavigationView.OnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                replaceFragment(fragments[0], fragments[0].tag.toString())
                mTextMessage?.text = "第1个fragment"
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_tools -> {
                replaceFragment(fragments[1], fragments[1].tag.toString())
                mTextMessage?.text = "第2个fragment"
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_play -> {
                replaceFragment(fragments[2], fragments[2].tag.toString())
                mTextMessage?.text = "第3个fragment"
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_relax -> {
                replaceFragment(fragments[3], fragments[3].tag.toString())
                mTextMessage?.text = "第4个fragment"
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_user_center -> {
                replaceFragment(fragments[4], fragments[4].tag.toString())
                mTextMessage?.text = "第5个fragment"
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun initView() {
        val navigation: BottomNavigationView = findViewById(R.id.nav_menu)
        mTextMessage = findViewById(R.id.nav_text)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        navigation.itemIconTintList = null
        navigation.itemTextColor = ColorStateList.valueOf(ContextCompat.getColor(this, R.color.black))
        homeFragment = HomeFragment()
        toolsFragment = ToolsFragment()
        relaxFragment = RelaxFragment()
        meFragment = MineFragment()
        playFragment = PlayFragment()
        fragments = arrayOf(homeFragment, toolsFragment, playFragment, relaxFragment, meFragment)
        fragments.forEach {
            addFragment(it, it.tag.toString())
            hideFragment(it)
        }
        showFragment(fragments[0])
    }

    //添加Fragment到FragmentList中
    private fun addFragment(fragment: Fragment, tag: String) {
        val fragmentManager = supportFragmentManager
        val transaction: FragmentTransaction = fragmentManager.beginTransaction()
        transaction.add(R.id.fragment_container, fragment, tag)
        transaction.commit()
    }

    // 清空fragmentList的所有Fragment，替换成新的Fragment，注意Fragment里面的坑
    private fun replaceFragment(fragment: Fragment, tag: String) {
        val fragmentManager = supportFragmentManager
        val transaction: FragmentTransaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment, tag)
        transaction.commit()
    }

    //把Fragment设置成显示状态，但是并没有添加到FragmentList中
    private fun showFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val transaction: FragmentTransaction = fragmentManager.beginTransaction()
        transaction.show(fragment)
        transaction.commit()
    }

    //把Fragment设置成显示状态，但是并没有添加到FragmentList中
    private fun hideFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val transaction: FragmentTransaction = fragmentManager.beginTransaction()
        transaction.hide(fragment)
        transaction.commit()
    }
}