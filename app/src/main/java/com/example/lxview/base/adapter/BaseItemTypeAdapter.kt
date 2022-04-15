package com.example.lxview.base.adapter


import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseViewHolder

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.example.baselib.utils.GlideUtils
import com.example.lxview.R
import com.example.lxview.function.home.bean.ItemBean


/**
 * author: 李 祥
 * date:   2022/4/15 5:21 下午
 * description:
 */
class BaseItemTypeAdapter(data: List<ItemBean?>?) : BaseMultiItemQuickAdapter<ItemBean, BaseViewHolder?>(data) {


    override fun convert(helper: BaseViewHolder, item: ItemBean?) {
        when (helper.itemViewType) {
            1 -> {

            }
            2->{
                val title = helper.getView<AppCompatTextView>(R.id.item_certificates_title)
                val account = helper.getView<AppCompatTextView>(R.id.item_certificates_account)
                val name = helper.getView<AppCompatTextView>(R.id.item_certificates_name)
                val text1 = helper.getView<AppCompatTextView>(R.id.func1)
                val text2 = helper.getView<AppCompatTextView>(R.id.func2)
                val avater = helper.getView<AppCompatImageView>(R.id.item_certificates_avater)
                title.text =item?.title
                account.text =item?.account
                name.text =item?.name
                item?.avatarRes?.let { GlideUtils.loadResUrlImage(helper.itemView.context, it,avater,12) }
                text1.setOnClickListener {

                }
                text2.setOnClickListener {

                }
            }
        }
    }

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    init {
        addItemType(1, R.layout.item_layout_normal)
        addItemType(2, R.layout.item_layout_certificates)
    }
}