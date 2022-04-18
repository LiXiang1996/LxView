package com.example.lxview.lxhome.adapter

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.lxview.base.fragment.BaseFragment
import com.example.lxview.lxhome.fragment.CardVoucherFragment
import com.example.lxview.lxhome.fragment.CertificatesFragment
import com.example.lxview.lxhome.fragment.TicketFragment


/**
 * author: 李 祥
 * date:   2022/4/15 2:19 下午
 * description:
 */
class CardBagPageAdapter(fm: FragmentManager, num: Int, fragments: MutableList<BaseFragment>, titles:MutableList<String>) : FragmentPagerAdapter(fm,BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {



    private val num: Int = num
    private var fragments: List<Fragment>? = null

    private var mFragmentHashMap: HashMap<Int, Fragment?> = HashMap()


    private var titles:MutableList<String>

    init {
        this.fragments = fragments
        this.titles = titles
    }

    override fun getItem(position: Int): Fragment {
        return fragments?.get(position)?:CertificatesFragment()
    }


    override fun getCount(): Int {
        return num
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return titles[position]
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
