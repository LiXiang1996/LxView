package com.example.sharelib.`interface`

interface Intercept {
    /**
     * 此处拦截设计的有很大的瑕疵，仅为了接口需要而添加，后续完善
     * return true 将不继续走doShare函数 需手动执行之后的分享操作
     */
    fun doShare(platform: Int): Boolean
}
