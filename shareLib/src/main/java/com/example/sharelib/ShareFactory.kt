package com.example.sharelib

import android.content.Context
import com.example.sharelib.`interface`.IShare

class ShareFactory {

    companion object {
        fun getShareManager(context: Context): IShare {
            return ShareManager.getInstance(context)
        }
    }
}
