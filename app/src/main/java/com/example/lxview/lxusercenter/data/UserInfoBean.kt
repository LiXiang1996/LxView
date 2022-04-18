package com.example.lxview.lxusercenter.data

import com.example.lxview.R
import com.example.lxview.function.home.bean.ItemBean

data class UserInfoBean(
    var userId:String?="",
    var userName:String?="",
    var gender:String?="",
    var avatar:String?="",
    var avatarRes:Int= R.drawable.avatar,
    var email:String?="",
    var IDCardList:List<ItemBean>?=null
)
