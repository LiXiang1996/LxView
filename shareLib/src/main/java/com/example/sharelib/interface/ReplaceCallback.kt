package com.example.sharelib.`interface`

interface ReplaceCallback {
    /**
     * 在分享的末尾环节调用
     * 返回 true 则打断原有doShare流程
     */
    fun onDoShare(): Boolean
}
