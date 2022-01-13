package com.example.lxview.function.home.bean

class ItemBean {
    var title: String? = ""
    var introduction:String? = ""
    var avatar: String? = ""
    var code: String? = ""
    var tag:Int?=0
}

enum class RouteType(val type:Int){
    SHARE_POP(1)
}
