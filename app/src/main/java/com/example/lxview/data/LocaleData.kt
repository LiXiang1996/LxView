package com.example.lxview.data

import com.example.lxview.function.home.bean.ItemBean

object LocaleData {

    fun getTestLocaleData():ArrayList<ItemBean>{
        val arrayList = ArrayList<ItemBean>()
        val bean = ItemBean().apply {
            this.name = "夏目贵志"
            this.itemType = 4
        }
        arrayList.add(bean)
        arrayList.add(bean)
        arrayList.add(bean)
        arrayList.add(bean)
        arrayList.add(bean)
        arrayList.add(bean)
        arrayList.add(bean)
        arrayList.add(bean)
        arrayList.add(bean)
        arrayList.add(bean)
        arrayList.add(bean)
        arrayList.add(bean)
        arrayList.add(bean)
        arrayList.add(bean)
        arrayList.add(bean)
        arrayList.add(bean)
        arrayList.add(bean)
        arrayList.add(bean)
        arrayList.add(bean)
        arrayList.add(bean)
        arrayList.add(bean)
        arrayList.add(bean)
        return arrayList
    }


}