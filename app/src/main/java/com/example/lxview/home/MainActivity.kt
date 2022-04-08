package com.example.lxview.home

import android.widget.FrameLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.FragmentManager
import com.example.lxview.R
import com.example.lxview.base.BaseActivity
import com.example.lxview.base.BaseFragment
import com.example.lxview.base.widget.MainBottomNavBar
import com.example.lxview.home.fragment.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.lxview.base.widget.ResultClick


/**
 * author: 李 祥
 * date:   2022/3/31 1:57 下午
 * description:
 */
class MainActivity :BaseActivity(),ResultClick{
    override val contentId: Int
        get() = R.layout.activity_main

    private var indicatorView: MainBottomNavBar? = null
    private lateinit var fragments: Array<BaseFragment>
    private lateinit var curSelectId: String
    private lateinit var homeFragment: HomeFragment
    private lateinit var toolsFragment: ToolsFragment
    private lateinit var relaxFragment: RelaxFragment
    private lateinit var meFragment: MineFragment
    private lateinit var playFragment: PlayFragment
    private lateinit var container: FrameLayout


    override fun initView() {
        indicatorView = findViewById(R.id.navigation)
        indicatorView?.listener = this
        container = findViewById(R.id.fragment_container)
        homeFragment = HomeFragment()
        toolsFragment = ToolsFragment()
        relaxFragment = RelaxFragment()
        meFragment = MineFragment()
        playFragment = PlayFragment()
        curSelectId = homeFragment.id.toString()
        fragments = arrayOf(homeFragment, toolsFragment, playFragment, relaxFragment, meFragment)
        fragments.forEach {
            addFragment(it,it.tag.toString())
        }
        showFragment(homeFragment)

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

    //移除指定的Fragment
    private fun removeFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val transaction: FragmentTransaction = fragmentManager.beginTransaction()
        transaction.remove(fragment)
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

    // 效果和show相近，创建视图，添加到containerid指定的Added列表，FragmentList依然保留，但是会引起生命周期的变化
    private fun attachFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val transaction: FragmentTransaction = fragmentManager.beginTransaction()
        transaction.attach(fragment)
        transaction.commit()
    }

    // 效果和hide相近，清除视图，从containerid指定的Added列表移除，FragmentList依然保留，但是会引起生命周期的变化
    private fun detachFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val transaction: FragmentTransaction = fragmentManager.beginTransaction()
        transaction.detach(fragment)
        transaction.commit()
    }

    override fun click(int: Int) {
        replaceFragment(fragments[int],fragments[int].tag.toString())
    }

}