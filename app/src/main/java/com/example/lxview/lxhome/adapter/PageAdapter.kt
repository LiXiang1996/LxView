package com.example.lxview.lxhome.adapter

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.lxview.lxhome.fragment.CardVoucherFragment
import com.example.lxview.lxhome.fragment.CertificatesFragment
import com.example.lxview.lxhome.fragment.TicketFragment


/**
 * author: 李 祥
 * date:   2022/4/15 2:19 下午
 * description:
 */
class PageAdapter(fm: FragmentManager, num: Int) : FragmentPagerAdapter(fm,BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {



    private val num: Int = num
    private var fragments: List<Fragment>? = null
    private var titles: Array<String>?=null

    private val mFragmentHashMap: HashMap<Int, Fragment?> = HashMap()


    override fun getItem(position: Int): Fragment {
        return createFragment(position)
//        return fragments?.get(position)!!
    }

//    fun addFragments(fragments:List<Fragment>,titles:Array<String>){
//        this.fragments  =fragments
//        this.titles = titles
//    }

    override fun getCount(): Int {
        return num
    }

    private fun createFragment(pos: Int): Fragment {
        var fragment: Fragment? = mFragmentHashMap[pos]
        if (fragment == null) {
            when (pos) {
                0 -> {
                    fragment = CertificatesFragment()
                }
                1 -> {
                    fragment = TicketFragment()
                }
                2 -> {
                    fragment = CardVoucherFragment()
                }
            }
            mFragmentHashMap[pos] = fragment
        }
        return fragment?:CertificatesFragment()
    }

}
