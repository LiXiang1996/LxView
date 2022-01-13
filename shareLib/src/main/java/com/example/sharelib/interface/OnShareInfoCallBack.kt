package com.example.sharelib.`interface`

import com.example.sharelib.bean.ShareBean


interface OnShareInfoCallBack {
    fun onCallBack(share: ShareBean?)
}

interface OnDevShareInfoCallBack {
    fun onCallBack(share: Boolean)
}
