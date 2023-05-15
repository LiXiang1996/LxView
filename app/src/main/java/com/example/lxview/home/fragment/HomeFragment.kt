package com.example.lxview.home.fragment

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.res.AssetManager
import android.media.MediaPlayer
import android.text.InputFilter
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import android.widget.EditText
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.lxview.R
import com.example.lxview.base.adapter.HomeFucAdapter
import com.example.lxview.base.adapter.RequestListDelegate
import com.example.lxview.base.adapter.RequestListHelper
import com.example.lxview.base.fragment.BaseFragment
import com.example.lxview.base.route.RoutePath
import com.example.lxview.function.home.bean.ItemBean
import com.example.lxview.login.LXConstant
import java.io.IOException
import java.util.regex.Matcher
import java.util.regex.Pattern


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
    private var recycleViewSecondAdapter: HomeFucAdapter? = null
    private var layoutManager: RecyclerView.LayoutManager? = null
    private var layoutManager2: RecyclerView.LayoutManager? = null
    private var searchEdit: EditText? = null

    //实例化播放内核
    private var mediaPlayer = MediaPlayer() //获得播放源访问入口

    //获得播放源访问入口
    private var am: AssetManager? = null

    //动画
    var imgAnimation: Animation? = null
    var lin: LinearInterpolator? = null
    var animtorAlpha: ObjectAnimator? = null


    @SuppressLint("ObjectAnimatorBinding")
    override fun initView() {
        searchEdit = mRootView.findViewById(R.id.home_search) //对输入的每一个字符进行判断
        val typeFilter = InputFilter { source, start, end, dest, dstart, dend -> //限制只能输入中文，英文，数字
            //            val p: Pattern = Pattern.compile("[0-9a-zA-Z|\u4e00-\u9fa5]+")
            val p: Pattern = Pattern.compile("[0-9a-zA-Z|!@#$]+")
            val m: Matcher = p.matcher(source.toString())
            if (!m.matches()) "" else null
        }

        searchEdit?.filters = arrayOf(typeFilter, InputFilter.LengthFilter(10))

        recycleViewMain = mRootView.findViewById<RecyclerView>(R.id.home_main_function_rv)
        recycleViewMain?.let {
            requestListHelper.initView(it, null, loadData = true, hasLoadMore = true)
        }
        nazimieDrawable = mRootView.findViewById(R.id.home_music_bg)

        recycleViewSecond = mRootView.findViewById<RecyclerView>(R.id.home_second_rv)
        recycleViewSecond?.let {
            recycleViewSecondAdapter = HomeFucAdapter(R.layout.item_layout_normal)
            it.adapter = recycleViewSecondAdapter
        }
        layoutManager = GridLayoutManager(context, 4)
        layoutManager2 = GridLayoutManager(context, 5)

        recycleViewMain?.layoutManager = layoutManager
        recycleViewSecond?.layoutManager = layoutManager2

        animtorAlpha = ObjectAnimator.ofFloat(nazimieDrawable, "rotation", 0f, 720f); //旋转不停顿
        animtorAlpha?.interpolator = object : LinearInterpolator() {} //设置动画重复次数
        animtorAlpha?.repeatCount = 100 //旋转时长
        animtorAlpha?.duration = 36000 //开始旋转



    }


    override fun initListener() {
        nazimieDrawable?.setOnClickListener {
            if (!LXConstant.isPlay) {
                try {
                    am = requireActivity().assets
                    val afd = am?.openFd("nazimie.mp3")  //给MediaPlayer设置播放源
                    if (afd != null) {
                        mediaPlayer.setDataSource(afd.fileDescriptor, afd.startOffset, afd.length)
                    }
                    if (animtorAlpha?.isPaused == true) {
                        animtorAlpha?.resume()
                    } else animtorAlpha?.start()

                } catch (e: IOException) {
                    e.printStackTrace()
                } //设置准备就绪状态监听

                mediaPlayer.setOnPreparedListener { // 开始播放
                    mediaPlayer.start()
                } //准备播放
                mediaPlayer.prepareAsync()
                LXConstant.isPlay = true
            } else {
                mediaPlayer.reset()
                if (animtorAlpha?.isRunning == true) {
                    animtorAlpha?.pause()
                }
                nazimieDrawable?.clearAnimation()
                LXConstant.isPlay = false
            }
        }


        nazimieDrawable?.post {
            context?.let {
                nazimieDrawable?.let { it1 ->
                    Glide.with(it).asBitmap().skipMemoryCache(true).load(R.drawable.big_bg)
                        .circleCrop().into(it1)
                }
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
            this.route = "ZHIFUBAO"
        })
        secondListData.add(1, ItemBean().apply {
            this.title = "导航栏"
            this.avatarRes = R.drawable.yang_2
            this.introduction = "导航栏"
            this.route = RoutePath.MainApp.NAV_ACT
        })
        secondListData.add(2, ItemBean().apply {
            this.title = "浏览器"
            this.avatarRes = R.drawable.yang_3
            this.introduction = "登录注册等流程"
            this.route = RoutePath.MainApp.WEB_VIEW
        })
        secondListData.add(3, ItemBean().apply {
            this.title = "数据库"
            this.avatarRes = R.drawable.yang_4
            this.introduction = "登录注册等流程"
            this.route = RoutePath.MainApp.FUNC_SQL
        })
        secondListData.add(4, ItemBean().apply {
            this.title = "游戏分组"
            this.avatarRes = R.drawable.yang_5
            this.introduction = "登录注册等流程"
            this.route = RoutePath.MainApp.GAME_TEAM
        })
        secondListData.add(5, ItemBean().apply {
            this.title = "图片处理"
            this.avatarRes = R.drawable.yang_6
            this.introduction = "登录注册等流程"
            this.route = RoutePath.MainApp.IMG_CENTER
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
            this.route = RoutePath.MainApp.CARD_BAG
        })
        list.add(1, ItemBean().apply {
            this.title = "简历"
            this.avatarRes = R.drawable.yang_2
            this.introduction = "登录注册等流程"
            this.route = RoutePath.MainApp.TOOLS_NORMAL
        })
        list.add(2, ItemBean().apply {
            this.title = "账号"
            this.avatarRes = R.drawable.yang_3
            this.introduction = "打开账号界面"
            this.route = "AccountsTotalActivity"
        })
        list.add(3, ItemBean().apply {
            this.title = "学习"
            this.avatarRes = R.drawable.yang_4
            this.introduction = "登录注册等流程"
            this.route = RoutePath.MainApp.TOOLS_NORMAL
        })

        response(true, list)
    }

    override fun bind(holder: RecyclerView.ViewHolder, position: Int, data: ItemBean) {
        with(holder.itemView) {
            val avatar: AppCompatImageView = findViewById(R.id.item_home_func_img)
            val title: AppCompatTextView = findViewById(R.id.item_home_func_title)

            avatar.let {
               Glide.with(avatar).load(data.avatarRes).placeholder(R.drawable.ic_launcher_foreground).circleCrop().into(avatar)
            }
            title.text = data.title

            this.setOnClickListener {
                RoutePath.jumpAct(context, data.route)
            }
        }
    }

    override fun onDestroy() {
        mediaPlayer.release()
        super.onDestroy()
    }
}