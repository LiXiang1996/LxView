package com.example.lxview.base.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.loading.BaseLoadingView
import com.example.loading.DisplayMode
import com.example.loading.OnTapListener

interface RequestListDelegate<T> {
    fun itemLayoutId(data: T): Int
    fun request(refresh: Boolean, response: (Boolean, List<T?>?) -> Unit)
    fun bind(holder: RecyclerView.ViewHolder, position: Int, data: T)


    fun responseList(refresh: Boolean, isSuccess: Boolean, list: List<T?>?): Boolean {
        return false
    }

    fun initHolder(holder: RecyclerView.ViewHolder, viewType: Int) {
    }

    fun updateLoading(mode: DisplayMode) {

    }
}

open class RequestListHelper<T>(delegate: RequestListDelegate<T>) : RequestListDelegate<T> by delegate {

    lateinit var recyclerView: RecyclerView

    //    var refreshLayout: RefreshLayout? = null
    var loadingView: BaseLoadingView? = null

    val adapter: RequestListAdapter = RequestListAdapter()

    open var hasLoadMore: Boolean = true
    private var isRequest = false

    /**
     * @param loadData 第一次进入界面是否加载数据
     * @param hasLoadMore 是否有加载更多
     */
    open fun initView(recyclerView: RecyclerView, loadingView: BaseLoadingView? = null, loadData: Boolean = true, hasLoadMore: Boolean = true) {
        this.recyclerView = recyclerView //        this.refreshLayout = refreshLayout
        this.loadingView = loadingView
        val refresh: OnTapListener? = null //        refreshLayout?.setOnRefreshListener { request(true) }
        //        refreshLayout?.setOnLoadMoreListener { request(false) }
        loadingView?.setOnTapListener(refresh) {
            tapLoadMore()
        }
        recyclerView.layoutManager = layoutManager(recyclerView.context)
        recyclerView.adapter = adapter //        if (loadData) {
        //            refreshLayout?.autoRefresh() ?: request(true)
        //        }
        this.hasLoadMore = hasLoadMore
    }

    /**
     * 当发生错误，点击暂位图
     */
    open fun tapLoadMore() {
        updateLoadingView(DisplayMode.LOADING)
        request(true)
    }

    open fun layoutManager(context: Context): RecyclerView.LayoutManager = LinearLayoutManager(context)

    /**
     * 发出请求
     * @param refresh 是否为刷新
     */
    fun request(refresh: Boolean) {
        if (refresh && isRequest) {
            return
        }
        isRequest = refresh
        request(refresh) { isSuccess, list ->
            isRequest = false
            response(refresh, isSuccess, list)
        }
    }

    /**
     * 处理请求结果
     */
    private fun response(refresh: Boolean, isSuccess: Boolean, list: List<T?>?) {
        if (!responseList(refresh, isSuccess, list)) { //            if (refresh) refreshLayout?.finishRefresh() else refreshLayout?.finishLoadMore()
            if (!isSuccess) {
                if (adapter.itemCount == 0) {
                    updateLoadingView(DisplayMode.NO_NETWORK)
                }
            } else {
                if (list.isNullOrEmpty()) {
                    if (refresh) {
                        updateLoadingView(DisplayMode.NO_DATA)
                    } else {
                    //                        refreshLayout?.setNoMoreData(true)
                    }
                } else {
                    updateLoadingView(DisplayMode.NONE)
                    initLoadMore()
                }
                val value = list?.filterNotNull() ?: emptyList()
                if (!refresh) {
                    adapter.addData(value)
                } else {
                    adapter.setData(value)
                }
            }
        }
    }

    fun update(list: List<T>) {
        if (list.isEmpty()) {
            updateLoadingView(DisplayMode.NO_DATA)
        } else {
            updateLoadingView(DisplayMode.NONE)
            initLoadMore()
        }
        adapter.setData(list)
    }

    /**
     * 修改LoadingView的状态
     */
    open fun updateLoadingView(mode: DisplayMode) {
        loadingView?.visibility = if (mode == DisplayMode.LOADING || mode == DisplayMode.NO_DATA || mode == DisplayMode.NO_NETWORK) View.VISIBLE else View.GONE
        loadingView?.setMode(mode)
        updateLoading(mode)
    }

    private fun initLoadMore() {
        if (!hasLoadMore) { //            refreshLayout?.setNoMoreData(true)
        }
    }


    open fun viewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(viewType, null, false)
        view.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        val holder = object : RecyclerView.ViewHolder(view) {

        }
        initHolder(holder, viewType)
        return holder
    }

    inner class RequestListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

        var list = mutableListOf<T>()

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            return viewHolder(parent, viewType)
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            bind(holder, position, list[position])
        }

        override fun getItemCount(): Int {
            return list.size
        }

        fun setData(list: List<T>) {
            this.list = list.toMutableList()
            notifyDataSetChanged()
        }

        fun addData(list: List<T>) {
            val oldMaxIndex = this.list.lastIndex
            if (list.isEmpty()) {
                return
            }
            this.list.addAll(list)
            notifyItemRangeInserted(oldMaxIndex + 1, list.size)
        }

        fun remove(position: Int) {
            if (this.itemCount > position) {
                this.list.removeAt(position)
                notifyItemRemoved(position)
                this.notifyItemRangeChanged(position, this.itemCount)
            }
        }

        override fun getItemViewType(position: Int): Int {
            return itemLayoutId(list[position])
        }
    }

}



