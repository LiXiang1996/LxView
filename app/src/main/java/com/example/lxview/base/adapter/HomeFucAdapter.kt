package com.example.lxview.base.adapter

import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.lxview.R
import com.example.lxview.base.route.RoutePath
import com.example.lxview.function.home.bean.ItemBean

/**
 * author: 李 祥
 * date:   2022/4/13 3:00 下午
 * description:
 */
class HomeFucAdapter(layoutId:Int): BaseQuickAdapter<ItemBean, BaseViewHolder>(layoutId, mutableListOf()) {

    override fun convert(helper: BaseViewHolder, item: ItemBean?) {
        val icon:AppCompatImageView = helper.getView(R.id.item_home_func_img)
        val title:AppCompatTextView = helper.getView(R.id.item_home_func_title)
        Glide.with(mContext).load(item?.avatarRes).error(R.drawable.yang_7).into(icon)
        title.text = item?.title
        helper.itemView.setOnClickListener{
            RoutePath.jumpAct(helper.itemView.context,item?.route)
        }
    }

    override fun addData(data: ItemBean) {
        super.addData(data)
    }

    override fun setNewData(data: MutableList<ItemBean>?) {
        super.setNewData(data)
    }

}