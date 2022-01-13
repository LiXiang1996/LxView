package com.example.sharelib

import com.example.sharelib.`interface`.Platform

@Suppress("EnumEntryName", "SpellCheckingInspection")
enum class SharePlatform(val platform: Int, val pn: String) {

    unkown(-1102, "no such element"),

    shareFav(-100, "fav"),

    shareReport(-100, "report"),
    shareDislike(-100,"dislike"),

    shareDelete(-100, "delete"),

    shareDownload(-100, "download"),

    shareFacebook(Platform.FACEBOOK, "share_facebook"),

    shareMessager(Platform.FBMESSAGER, "share_messager"),

    shareWhatapp(Platform.WHATAPP, "share_whatapp"),

    shareInstagram(Platform.INSTAGRAM, "share_instagram"),

    shareSnapchat(Platform.SNAPCHAT, "share_snapchat"),

    shareMessages(Platform.MESSAGE, "share_messages"),

    shareMore(Platform.ANDROID, "share_more"),

    shareCopyLink(Platform.SHARE_COPY_LINK, "share_copy_link"),

    shareLine(Platform.LINE,"share_line");

    companion object {
        const val FUNCTION_TYPE = -100
    }
}