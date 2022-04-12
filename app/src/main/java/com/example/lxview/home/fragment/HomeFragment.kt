package com.example.lxview.home.fragment

import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.baselib.utils.startAct
import com.example.lxview.R
import com.example.lxview.base.BaseApplication
import com.example.lxview.base.BaseFragment
import com.example.lxview.base.adapter.RequestListDelegate
import com.example.lxview.base.adapter.RequestListHelper
import com.example.lxview.function.home.bean.ItemBean
import com.example.lxview.home.tools.NormalToolsActivity
import com.example.lxview.login.activity.LoginActivity


/**
 * author: 李 祥
 * date:   2022/3/31 3:39 下午
 * description:
 */
class HomeFragment : BaseFragment(), RequestListDelegate<ItemBean> {
    override val contentId: Int
        get() = R.layout.fg_home
    private var iconDrawable: AppCompatImageView? = null
    private var recycleViewMain: RecyclerView? = null
    private val requestListHelper = RequestListHelper(this)
    private var layoutManager: RecyclerView.LayoutManager? = null


    override fun initView() {
        recycleViewMain = mRootView.findViewById<RecyclerView>(R.id.home_main_function_rv)
        recycleViewMain?.let {
            requestListHelper.initView(it, null, loadData = true, hasLoadMore = true)
        }
        layoutManager = GridLayoutManager(context, 4)
        recycleViewMain?.layoutManager = layoutManager

    }

    override fun initListener() {
    }

    override fun initData() {
        requestListHelper.request(true)
        super.initData()
    }

    override fun itemLayoutId(data: ItemBean): Int {
        return R.layout.item_layout_home_func
    }

    override fun request(refresh: Boolean, response: (Boolean, List<ItemBean?>?) -> Unit) {
        val list = mutableListOf<ItemBean>()
        list.add(0, ItemBean().apply {
            this.title = "卡包"
            this.avatarRes = R.drawable.yinhangka
            this.introduction = "登录注册等流程"
            this.tag2 = "ZHIFUBAO"
        })
        list.add(1, ItemBean().apply {
            this.title = "简历"
            this.avatarRes = R.drawable.yang_2
            this.introduction = "登录注册等流程"
            this.tag2 = "HomeActivity"
        })
        list.add(1, ItemBean().apply {
            this.title = "简历"
            this.avatarRes = R.drawable.yang_3
            this.introduction = "登录注册等流程"
            this.tag2 = "HomeActivity"
        })
        list.add(1, ItemBean().apply {
            this.title = "简历"
            this.avatarRes = R.drawable.yang_4
            this.introduction = "登录注册等流程"
            this.tag2 = "HomeActivity"
        })

        response(true, list)
    }

    override fun bind(holder: RecyclerView.ViewHolder, position: Int, data: ItemBean) {
        with(holder.itemView) {
            val avatar: AppCompatImageView = findViewById(R.id.item_home_func_img)
            val title: AppCompatTextView = findViewById(R.id.item_home_func_title)

            avatar.let {
                com.bumptech.glide.Glide.with(avatar).load(data.avatarRes).placeholder(R.drawable.ic_launcher_foreground).circleCrop().into(avatar)
            }
            title.text = data.title

            this.setOnClickListener {
                if (data.tag2?.contains("LoginActivity") == true) {
                    startAct<LoginActivity>()
                } else if (data.tag2?.contains("HomeActivity") == true) {
                    startAct<NormalToolsActivity>()
                } else if (data.tag2?.contains("ZHIFUBAO") == true) {
                    val packageManager: PackageManager = BaseApplication.appContext.packageManager
                    var intent: Intent? = Intent() //跳转到下一页5 APP界面 //跳转到下一页5 APP界面
                    intent = packageManager.getLaunchIntentForPackage("com.eg.android.AlipayGphone")
                    startActivity(intent)
                } else startAct(data.tag2 ?: "")
            }
        }
    }
}