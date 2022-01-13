package com.example.lxview.listTimestamp.adapter

import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.lxview.R
import com.example.lxview.listTimestamp.ImMessageDecoration
import com.example.lxview.listTimestamp.bean.TimeStampBean

/**
 * author: 李 祥
 * date:   2021/9/27 2:04 下午
 * description:
 */

/*
    1）第一个参数：提供数据的Bean类
    （2）第二个参数：BaseViewHolder
  */
class TimeStampAdapter(layoutResId: Int,recyclerView: RecyclerView) :
    BaseQuickAdapter<TimeStampBean, BaseViewHolder>(layoutResId),ImMessageDecoration.TimeLineViewBuilder<TimeStampBean> {


    init {

        val imDecoration = object : ImMessageDecoration<TimeStampBean, ImMessageDecoration.TimeLineViewBuilder<TimeStampBean>>(this) {
            override fun getItem(p: Int): TimeStampBean? {
                return this@TimeStampAdapter.getItem(p)
            }
        }

        recyclerView.itemAnimator = null
        recyclerView.addItemDecoration(imDecoration)

    }
    override fun convert(helper: BaseViewHolder, item: TimeStampBean?) {
        val textId = helper.getView<TextView>(R.id.list_item_tv_id)
        val textContent = helper.getView<TextView>(R.id.list_item_tv_content)
        val img = helper.getView<AppCompatImageView>(R.id.list_item_img)

        textId?.text = item?.itemID.toString()
        textContent?.text = item?.itemContent.toString()
        img?.let { Glide.with(mContext).load(R.mipmap.ic_launcher).into(it) }

        helper.addOnClickListener(R.id.list_item_img)
    }

    override fun getTopMargin(): Int {
        TODO("Not yet implemented")
    }

    override fun getTimeLine(d: TimeStampBean?): String? {
        TODO("Not yet implemented")
    }

    override fun getTextSize(): Int {
        TODO("Not yet implemented")
    }

    override fun getTextColor(): Int {
        TODO("Not yet implemented")
    }

}