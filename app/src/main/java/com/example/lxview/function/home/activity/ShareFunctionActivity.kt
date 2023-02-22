package com.example.lxview.function.home.activity

import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.example.loading.BaseLoadingView
import com.example.lxview.R
import com.example.lxview.base.activity.BaseActivity
import com.example.lxview.base.adapter.RequestListDelegate
import com.example.lxview.base.adapter.RequestListHelper
import com.example.lxview.function.home.bean.ItemBean
import com.example.lxview.function.home.bean.RouteType
import com.example.sharelib.ShareFactory
import com.example.sharelib.SharePlatform
import com.example.sharelib.SharePopConfig
import com.example.sharelib.`interface`.CallBack
import com.example.sharelib.`interface`.IShare

/**
 * author: 李 祥
 * date:   2022/1/10 5:19 下午
 * description:
 */
class ShareFunctionActivity : BaseActivity(), RequestListDelegate<ItemBean> {

    override val contentId: Int
        get() = R.layout.home_function_activity_layout

    private var recyclerView: RecyclerView? = null
    private var textBack: AppCompatTextView? = null
    private val requestListHelper = RequestListHelper(this)
    private var blvLoading: BaseLoadingView? = null


    override fun initView() {
        super.initView()
        textBack = findViewById(R.id.toolbar_left)
        recyclerView = findViewById(R.id.home_function_rv)

        recyclerView?.let {
            requestListHelper.initView(it, blvLoading, loadData = true, hasLoadMore = true)
        }
    }

    override fun onResume() {
        super.onResume()
        requestListHelper.request(true)
    }

    override fun initData() {
        super.initData()
    }

    override fun initListener() {
        super.initListener()
    }

    override fun itemLayoutId(data: ItemBean): Int {
        return R.layout.list_item_common
    }

    override fun request(refresh: Boolean, response: (Boolean, List<ItemBean?>?) -> Unit) {
        val list = mutableListOf<ItemBean>()
        list.add(0,ItemBean().apply {
            this.title = "1"
            this.avatar = "ddd"
            this.introduction = "分享模块"
            this.tag = RouteType.SHARE_POP.type
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
                when(data.tag){
                    RouteType.SHARE_POP.type->{
                        val url = "www.baidu.com"
                        val shareConfig = SharePopConfig.createByIMClapHouse()

                        ShareFactory.getShareManager(context).setPlatform(SharePlatform.unkown.platform)
                            .setContent(url).setTitle("Share").setUrl(url).setCallBack(object :CallBack{
                                override fun onShareEnd(platform: Int, resultCode: Int) {
                                    if (resultCode == IShare.ERROR) return
                                }
                        }).showShareWindow()
                    }
                }
            }
        }
    }
}