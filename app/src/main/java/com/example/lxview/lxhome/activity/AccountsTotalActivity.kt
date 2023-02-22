package com.example.lxview.lxhome.activity

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.baselib.utils.ToastUtils
import com.example.lxview.R
import com.example.lxview.base.activity.BaseBindActivity
import com.example.lxview.base.adapter.BaseItemTypeAdapter
import com.example.lxview.base.route.RoutePath
import com.example.lxview.base.widget.ToolsBarView
import com.example.lxview.databinding.ActivityAccountTotalLayoutBinding
import com.example.lxview.function.home.bean.ItemBean
import com.example.lxview.login.LXConstant

/**
 * author: 李 祥
 * date:   2022/4/16 4:37 下午
 * description:保存自己的账号
 */
class AccountsTotalActivity: BaseBindActivity<ActivityAccountTotalLayoutBinding>() {

    override val layout: Int
        get() = R.layout.activity_account_total_layout
    private lateinit var toolsBarView: ToolsBarView
    private lateinit var recyclerView: RecyclerView
    private var itemAdapter: BaseItemTypeAdapter?=null
    private var data:MutableList<ItemBean>?=null


    override fun initView() {
        toolsBarView = findViewById(R.id.account_toolbar_view)
        recyclerView = findViewById(R.id.account_total_rv)

    }

    override fun initListener() {
        toolsBarView.addListener("账号",""){
            when (it) {
                LXConstant.TOOLBAR_LEFT -> finish()
                LXConstant.TOOLBAR_RIGHT -> ToastUtils.showToast(this, "下一页")
            }
        }
    }
    override suspend fun handleData() {
        data = ArrayList()
        data?.add(ItemBean().apply {
            this.avatarRes =R.drawable.avatar
            this.name = "维毛小姐姐"
            this.account = "2459141797"
            this.password = "okokokok9"
            this.title = "QQ号"
            this.route = RoutePath.MainApp.TOOLS_NORMAL
            this.itemType = 3
            this.clickText1 = "查看证件照"
        })
        itemAdapter = BaseItemTypeAdapter(data)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter =itemAdapter
    }

}