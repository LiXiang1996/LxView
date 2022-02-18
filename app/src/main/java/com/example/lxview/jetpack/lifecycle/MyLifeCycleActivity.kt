package com.example.lxview.jetpack.lifecycle

import android.content.ContentValues
import android.os.Bundle
import android.os.IBinder
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.lifecycle.*
import androidx.recyclerview.widget.RecyclerView
import com.example.lxview.R
import com.example.lxview.base.adapter.RequestListDelegate
import com.example.lxview.base.adapter.RequestListHelper
import com.example.lxview.function.home.bean.ItemBean
import com.example.lxview.function.home.bean.RouteType
import com.example.lxview.jetpack.livedata.TestViewModel

/**
 * author: 李 祥
 * date:   2022/2/16 3:39 下午
 * description:
 */
class MyLifeCycleActivity : AppCompatActivity(), RequestListDelegate<ItemBean> {


    lateinit var rv: RecyclerView
    private lateinit var tvAdd: AppCompatTextView
    private lateinit var tvExit: AppCompatTextView
    private val requestListHelper = RequestListHelper(this)
    private val myLocationListener = MyPlayListener2()
    val list = mutableListOf<ItemBean>()
    val list2 = mutableListOf<Int>()
    var i = 0


    /**
     * 1、如果是想监听某个 Activity 的生命周期，需要我们做的就是自定义组件，实现 LifecycleObserver 接口即可，该接口没有接口方法，不需要任何具体的实现。
     * 创建一个 MyPlayListener 类，实现 LifecycleObserver 接口，与播放相关的逻辑全在这个类里面完成。对于组件里面需要在 Activity 生命周期变化时得到通知的方法，
     * 用 @OnLifecycleEvent(Lifecycle.Event.ON_XXX) 注解进行标记，这样当 Activity 生命周期发生变化时，被标记过的方法便会被自动调用。
     * 而不需要通过onCreate()等方法
     *
     * 2、也可以使用 LifecycleService 解耦 Service 与组件，使用 @OnLifecycleEvent 标记希望在 Server 生命周期发生变化时得到同步调用的方法。
     *
     * 3、具有生命周期的组件除了 Activity、Fragment 和 Service 外，还有 Application。ProcessLifecycleOwner 就是用来监听整个应用程序的生命周期情况。
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.jetpack_lifecycle_activity_layout)

        lifecycle.addObserver(myLocationListener)
        rv = findViewById(R.id.jetpack_lifecycle_rv)
        tvAdd = findViewById(R.id.jetpack_lifecycle_tv_add)
        tvExit = findViewById(R.id.jetpack_lifecycle_tv_exit)


        rv.let {
            requestListHelper.initView(it, null, loadData = true, hasLoadMore = true)
        }
        requestListHelper.request(true)

        tvAdd.setOnClickListener {
            val mTestViewModel = ViewModelProviders.of(this@MyLifeCycleActivity).get(MyLifeTestViewModel::class.java) //不带参数
            i += 1
            mTestViewModel.getNameEvent().value = i.toString()+"只羊"
        }
        tvExit.setOnClickListener {
            finish()
        }

        list2.add(0,R.drawable.yang_1)
        list2.add(1,R.drawable.yang_2)
        list2.add(2,R.drawable.yang_3)
        list2.add(3,R.drawable.yang_4)
        list2.add(4,R.drawable.yang_5)
        list2.add(5,R.drawable.yang_6)
        list2.add(6,R.drawable.yang_7)

    }


    override fun onStart() {
        super.onStart()
        myLocationListener.startPlay()
    }

    override fun onResume() {
        super.onResume()
        myLocationListener.resumePlay()
    }

    override fun onPause() {
        super.onPause()
        myLocationListener.pausePlay()
    }

    override fun itemLayoutId(data: ItemBean): Int {
        return R.layout.jetpack_lifecycle_activity_item_layout
    }

    override fun request(refresh: Boolean, response: (Boolean, List<ItemBean?>?) -> Unit) {
        list.add(0, ItemBean().apply {
            this.introduction = "开始搞事"
        })
        val mTestViewModel = ViewModelProviders.of(this).get(MyLifeTestViewModel::class.java) //不带参数
        val nameEvent: MutableLiveData<String> = mTestViewModel.getNameEvent() //lambda表达式
        nameEvent.observe(this, { str ->
            Log.i(ContentValues.TAG, "onChanged: s = $str")
            list.add(0, ItemBean().apply {
                this.introduction = nameEvent.value
                response(true, list)
            })
        })
    }

    override fun bind(holder: RecyclerView.ViewHolder, position: Int, data: ItemBean) {
        with(holder.itemView) {
            val tv: AppCompatTextView = findViewById(R.id.life_item_text)
            val img: AppCompatImageView = findViewById(R.id.life_item_img)
            val k = (0 until 7).random()
            img.setImageResource(list2[k])
            tv.text = data.introduction
        }
    }


    /**
     * author: 李 祥
     * date:   2022/2/16 3:50 下午
     * description:
     */
    inner class MyPlayListener2 : LifecycleObserver {

        /**
         * LifeCycle 有三种实现方法：
         *LifecycleObserver 配合 @OnLifecycleEvent 注解
         *DefaultLifecycleObserver 拥有宿主所有生命周期事件
         *LifecycleEventObserver 将宿主生命周期事件封装成 Lifecycle.Event
         */
        @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
        fun initVideo() {
            Log.d(TAG2, "initVideo")
            val mTestViewModel = ViewModelProviders.of(this@MyLifeCycleActivity).get(MyLifeTestViewModel::class.java) //不带参数
            mTestViewModel.getNameEvent().value = "activity创建"

        }
        @OnLifecycleEvent(Lifecycle.Event.ON_START)
        fun startPlay() {
            Log.d(TAG2, "startVideo")
            val mTestViewModel = ViewModelProviders.of(this@MyLifeCycleActivity).get(MyLifeTestViewModel::class.java) //不带参数
            mTestViewModel.getNameEvent().value = "activity开始"

        }

        @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
        fun resumePlay() {
            Log.d(TAG2, "resumePlay")
            val mTestViewModel = ViewModelProviders.of(this@MyLifeCycleActivity).get(MyLifeTestViewModel::class.java) //不带参数
            mTestViewModel.getNameEvent().value = "activityResume"
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
        fun pausePlay() {
            Log.d(TAG2, "pausePlay")
            val mTestViewModel = ViewModelProviders.of(this@MyLifeCycleActivity).get(MyLifeTestViewModel::class.java) //不带参数
            mTestViewModel.getNameEvent().value = "activityPause"
        }
        private  val TAG2 = "MyVideoPlayListener"
    }
}

