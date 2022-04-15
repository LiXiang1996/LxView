package com.example.lxview.function.home.bean

import com.chad.library.adapter.base.entity.MultiItemEntity

class ItemBean : MultiItemEntity {
    var itemType: Int? = 0
    var title: String? = ""
    var introduction: String? = ""
    var avatar: String? = ""
    var avatarRes: Int? = 0
    var code: String? = ""
    var tag: Int? = 0
    var tag2: String? = ""
    var account:String?=""
    var name:String?=""

    var clickText1:String?=null
    var clickText2:String?=null
    var clickText3:String?=null
    override fun getItemType(): Int {
        return itemType?:0
    }
}

enum class RouteType(val type: Int) {
    SHARE_POP(1)
}
