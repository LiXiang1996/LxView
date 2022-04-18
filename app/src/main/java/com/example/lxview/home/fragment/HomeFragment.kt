package com.example.lxview.home.fragment

import android.content.Intent
import android.content.pm.PackageManager
import android.content.res.AssetManager
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.baselib.utils.startAct
import com.example.lxview.R
import com.example.lxview.base.BaseApplication
import com.example.lxview.base.fragment.BaseFragment
import com.example.lxview.base.adapter.BaseAdapter
import com.example.lxview.base.adapter.RequestListDelegate
import com.example.lxview.base.adapter.RequestListHelper
import com.example.lxview.function.home.bean.ItemBean
import com.example.lxview.lxtools.NormalToolsActivity
import com.example.lxview.login.activity.LoginActivity
import com.example.lxview.lxhome.activity.CardBagActivity

import android.media.MediaPlayer
import com.example.lxview.base.route.RoutePath
import com.example.lxview.login.LXConstant
import java.io.IOException

/**
 * author: 李 祥
 * date:   2022/3/31 3:39 下午
 * description:
 */
class HomeFragment : BaseFragment(), RequestListDelegate<ItemBean> {
    override val contentId: Int
        get() = R.layout.fg_home
    private var nazimieDrawable: AppCompatImageView? = null
    private var recycleViewMain: RecyclerView? = null
    private var recycleViewSecond: RecyclerView? = null
    private val requestListHelper = RequestListHelper(this)
    private var recycleViewSecondAdapter:BaseAdapter ?=null
    private var layoutManager: RecyclerView.LayoutManager? = null
    private var layoutManager2: RecyclerView.LayoutManager? = null
    //实例化播放内核
    private var mediaPlayer  = MediaPlayer() //获得播放源访问入口
    //获得播放源访问入口
    private var am: AssetManager ?=null


    override fun initView() {
        recycleViewMain = mRootView.findViewById<RecyclerView>(R.id.home_main_function_rv)
        recycleViewMain?.let {
            requestListHelper.initView(it, null, loadData = true, hasLoadMore = true)
        }
        nazimieDrawable = mRootView.findViewById(R.id.big_bg)

        recycleViewSecond = mRootView.findViewById<RecyclerView>(R.id.home_second_rv)
        recycleViewSecond?.let {
            recycleViewSecondAdapter = BaseAdapter(R.layout.item_layout_normal,it)
            it.adapter = recycleViewSecondAdapter
        }
        layoutManager = GridLayoutManager(context, 4)
        layoutManager2 = GridLayoutManager(context, 5)

        recycleViewMain?.layoutManager = layoutManager
        recycleViewSecond?.layoutManager = layoutManager2

    }

    override fun initListener() {
        nazimieDrawable?.setOnClickListener {
            if (!LXConstant.isPlay) {
                try {
                    am = requireActivity().assets
                    val afd = am?.openFd("nazimie.mp3")  //给MediaPlayer设置播放源
                    if (afd != null) {
                        mediaPlayer?.setDataSource(afd.fileDescriptor, afd.startOffset, afd.length)
                    }
                } catch (e: IOException) {
                    e.printStackTrace()
                } //设置准备就绪状态监听

                mediaPlayer.setOnPreparedListener { // 开始播放
                    mediaPlayer!!.start()
                } //准备播放
                mediaPlayer.prepareAsync()
                LXConstant.isPlay = true
            }else {
                mediaPlayer.reset()
                LXConstant.isPlay = false
            }
        }
    }

    override fun initData() {
        requestListHelper.request(true)
        val secondListData = mutableListOf<ItemBean>()
        secondListData.add(0, ItemBean().apply {
            this.title = "文件"
            this.avatarRes = R.drawable.yinhangka
            this.introduction = "登录注册等流程"
            this.tag2 = "ZHIFUBAO"
        })
        secondListData.add(1, ItemBean().apply {
            this.title = "日历"
            this.avatarRes = R.drawable.yang_2
            this.introduction = "登录注册等流程"
            this.tag2 = "HomeActivity"
        })
        secondListData.add(2, ItemBean().apply {
            this.title = "相册"
            this.avatarRes = R.drawable.yang_3
            this.introduction = "登录注册等流程"
            this.tag2 = "HomeActivity"
        })
        secondListData.add(3, ItemBean().apply {
            this.title = "通信录"
            this.avatarRes = R.drawable.yang_4
            this.introduction = "登录注册等流程"
            this.tag2 = "HomeActivity"
        })
        secondListData.add(4, ItemBean().apply {
            this.title = "通信录"
            this.avatarRes = R.drawable.yang_5
            this.introduction = "登录注册等流程"
            this.tag2 = "HomeActivity"
        })
        secondListData.add(5, ItemBean().apply {
            this.title = "more"
            this.avatarRes = R.drawable.yang_6
            this.introduction = "登录注册等流程"
            this.tag2 = "HomeActivity"
        })
        recycleViewSecondAdapter?.setNewData(secondListData)
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
            this.introduction = "卡包功能"
            this.tag2 = RoutePath.MainApp.CARD_BAG
        })
        list.add(1, ItemBean().apply {
            this.title = "简历"
            this.avatarRes = R.drawable.yang_2
            this.introduction = "登录注册等流程"
            this.tag2 = RoutePath.MainApp.TOOLS_NORMAL
        })
        list.add(2, ItemBean().apply {
            this.title = "账号"
            this.avatarRes = R.drawable.yang_3
            this.introduction = "打开账号界面"
            this.tag2 = "AccountsTotalActivity"
        })
        list.add(3, ItemBean().apply {
            this.title = "学习"
            this.avatarRes = R.drawable.yang_4
            this.introduction = "登录注册等流程"
            this.tag2 = RoutePath.MainApp.TOOLS_NORMAL
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
                RoutePath.jumpAct(context,data.tag2)
            }
        }
    }


}