package com.example.lxview.jetpack.navigation

import androidx.appcompat.widget.AppCompatTextView
import androidx.navigation.Navigation
import com.example.lxview.R
import com.example.lxview.base.BaseActivity




/**
 * author: 李 祥
 * date:   2022/2/16 6:24 下午
 * description:
 */
class MyNavigationActivity:BaseActivity() {
    override val contentId: Int
        get() = R.layout.navigation_layout

    override fun onSupportNavigateUp(): Boolean {
        return Navigation.findNavController(this, R.id.nav_host_fragment).navigateUp()
    }

}