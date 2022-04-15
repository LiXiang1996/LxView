package com.example.lxview.lxhome.fragment

import androidx.recyclerview.widget.RecyclerView
import com.example.lxview.R
import com.example.lxview.base.fragment.BaseFragment

/**
 * author: 李 祥
 * date:   2022/4/15 2:07 下午
 * description:
 */
class TicketFragment: BaseFragment() {

    override val contentId: Int
        get() = R.layout.fg_card_bag_ticket

    private var recyclerView: RecyclerView? = null
}