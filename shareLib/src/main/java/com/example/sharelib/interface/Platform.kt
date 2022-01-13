package com.example.sharelib.`interface`

interface Platform {
    companion object {
        const val UNKNOWN: Int = -1000

        /**
         * android 原生分享
         */
        const val ANDROID: Int = -1

        /**
         * copy
         */
        const val COPY: Int = -2

        /**
         * 添加收藏
         */
        const val ADD_TO_FAVORITES: Int = -3

        /**
         * facebook
         */
        const val FACEBOOK: Int = 0

        /**
         * facebook messager
         */
        const val FBMESSAGER: Int = 1

        /**
         * whatapp
         */
        const val WHATAPP: Int = 2

        /**
         * 短信
         */
        const val MESSAGE: Int = 3

        /**
         *  snapchat
         */
        const val SNAPCHAT: Int = 4

        /**
         *  instagram
         */
        const val INSTAGRAM: Int = 5

        /**
         *  App内容好友
         */
        const val FRIENDS: Int = 6

        /**
         * 通讯录联系人
         */
        const val PHONE_CONTACTS: Int = 7

        /**
         * 复制链接
         */
        const val SHARE_COPY_LINK: Int = 8

        /**
         * 下载
         */
        const val SHARE_DOWNLOAD: Int = 9

        /**
         * 其他功能
         */
        const val SHARE_FUNCTION: Int = -100

        const val LINE = 10
    }
}
