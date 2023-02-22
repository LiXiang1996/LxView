package com.example.lxview.home.fragment

import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.example.baselib.utils.startAct
import com.example.lxview.R
import com.example.lxview.base.fragment.BaseFragment
import com.example.lxview.base.adapter.RequestListDelegate
import com.example.lxview.base.adapter.RequestListHelper
import com.example.lxview.base.route.RoutePath
import com.example.lxview.function.home.bean.ItemBean
import com.example.lxview.lxtools.NormalToolsActivity
import com.example.lxview.login.activity.LoginActivity

/**
 * author: 李 祥
 * date:   2022/3/31 3:40 下午
 * description:
 */
class ToolsFragment : BaseFragment(), RequestListDelegate<ItemBean> {

    private var recyclerView: RecyclerView? = null
    private val requestListHelper = RequestListHelper(this)


    override val contentId: Int
        get() = R.layout.fg_tools
    override fun initView() {
        recyclerView = activity?.findViewById(R.id.tools_recycle)
        recyclerView?.let {
            requestListHelper.initView(it, null, loadData = true, hasLoadMore = true)
        }
    }

    override fun initListener() {

    }

    override fun initData() {
        requestListHelper.request(true)
        super.initData()
    }

    override fun itemLayoutId(data: ItemBean): Int {
        return R.layout.list_item_common
    }

    override fun request(refresh: Boolean, response: (Boolean, List<ItemBean?>?) -> Unit) {
        val list = mutableListOf<ItemBean>()
        list.add(0, ItemBean().apply {
            this.title = "登录"
            this.avatarRes = R.drawable.yang_1
            this.introduction = "登录注册等流程"
            this.route = "LoginActivity"
        })
        list.add(1, ItemBean().apply {
            this.title = "动画"
            this.avatarRes = R.drawable.yang_2
            this.introduction = "各类动画"
            this.route = RoutePath.MainApp.ANIMATION
        })
        list.add(2, ItemBean().apply {
            this.title = "心跳检测"
            this.avatarRes = R.drawable.yang_2
            this.introduction = ""
            this.route = ""
        })

        response(true, list)
    }

    override fun bind(holder: RecyclerView.ViewHolder, position: Int, data: ItemBean) {
        with(holder.itemView) {
            val avatar: AppCompatImageView = findViewById(R.id.im_item_admin_avatar)
            val title: AppCompatTextView = findViewById(R.id.im_item_admin_username)
            val tvDescription: AppCompatTextView = findViewById(R.id.im_item_admin_description)
            val btSet: AppCompatTextView = findViewById(R.id.im_item_admin_set_tv)

            avatar.let {
                com.bumptech.glide.Glide.with(avatar).load(data.avatar).placeholder(R.drawable.ic_launcher_foreground).circleCrop().into(avatar)
            }
            title.text = data.title
            tvDescription.text = data.introduction
            btSet.text = "查看"
            btSet.setOnClickListener {
                if (position == 2) {

                } else RoutePath.jumpAct(context, data.route)
            }
        }
    }
}