package com.example.lxview.base.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.lxview.R


class MainBottomNavBar @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : ConstraintLayout(context, attrs, defStyleAttr) {

    private lateinit var videoContainer: ConstraintLayout
    private lateinit var navHome: LinearLayout
    private lateinit var navTools: LinearLayout
    private lateinit var navPlay: LinearLayout
    private lateinit var navMe: LinearLayout
    private lateinit var navRelax: LinearLayout
     var listener:ResultClick?=null

    init {
        initView()
    }
    private fun initView() {
        View.inflate(context, R.layout.main_act_bottom_nav_bar, this)
        videoContainer = findViewById(R.id.act_main_bottom_nav_ll)
        navHome = findViewById(R.id.bottom_bar_home)
        navTools = findViewById(R.id.bottom_bar_tools)
        navPlay = findViewById(R.id.bottom_bar_play)
        navMe = findViewById(R.id.bottom_bar_mine)
        navRelax = findViewById(R.id.bottom_bar_relax)

        navHome.setOnClickListener {
            listener?.click(0)
        }
        navTools.setOnClickListener {
            listener?.click(1)
        }
            navPlay.setOnClickListener {
            listener?.click(2)
        }
        navRelax.setOnClickListener {
            listener?.click(3)
        }
        navMe.setOnClickListener {
            listener?.click(4)
        }
    }
}

interface ResultClick{
    fun click(int: Int)
}