package com.example.sharelib

class SharePopConfig {

    var isShowFav: Boolean = true
    var isShowReport: Boolean = true
    var isShowDownload: Boolean = true
    var isShowDisLike: Boolean = true
    var isAlreadyFavorite: Boolean = false
    var showTitle: Boolean = true
    var showSubTitle: Boolean = false
    var isShowDelete: Boolean = false
    var title: String? = ""
    var subTitle: String? = ""

    companion object {
        fun createBySelf(): SharePopConfig {
            val shareConfig = SharePopConfig()
            shareConfig.isShowDelete = true
            shareConfig.isShowFav = false
            shareConfig.isShowReport = false
            shareConfig.isShowDisLike = false
            return shareConfig
        }

        fun createByInvite(): SharePopConfig {
            val shareConfig = SharePopConfig()
            shareConfig.isShowFav = false
            shareConfig.isShowReport = false
            return shareConfig
        }

        fun createByChallenge(): SharePopConfig {
            val shareConfig = SharePopConfig()
            shareConfig.isShowReport = false
            shareConfig.isShowFav = true
            shareConfig.isShowDisLike = false
            return shareConfig
        }

        fun createByVote(): SharePopConfig {
            val shareConfig = SharePopConfig()
            shareConfig.isShowDisLike = false
            return shareConfig
        }

        fun createByLive(): SharePopConfig {
            val shareConfig = SharePopConfig()
            shareConfig.isShowFav = false
            shareConfig.isShowReport = false
            shareConfig.isShowDisLike = false
            shareConfig.isShowDownload = false
            shareConfig.showTitle = false
            shareConfig.showSubTitle = true
            return shareConfig
        }

        fun createByIMClapHouse(): SharePopConfig {
            val shareConfig = SharePopConfig()
            shareConfig.isShowFav = false
            shareConfig.isShowReport = false
            shareConfig.isShowDisLike = false
            shareConfig.isShowDownload = false
            shareConfig.showTitle = false
            shareConfig.showSubTitle = true
            return shareConfig
        }
    }
}