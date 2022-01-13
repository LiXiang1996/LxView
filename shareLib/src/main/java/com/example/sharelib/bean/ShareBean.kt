package com.example.sharelib.bean

/**
 * author: lx
 * Date: 2018/11/27 21:03
 * Description:
 */
data class ShareBean(
    val shareweb: String,
    val shareid: Int,
        // product数据分享时暂时没有用到
    val product: Product

)

data class Product(
    val activity_audit_time: Any,
    val activity_join_time: Any,
    val activityid: Any,
    val border_img: Any,
    val code: String,
    val createtime: Long,
    val dpid: Any,
    val enable: Int,
    val headpic: String,
    val img_cover: String,
    val pid: Int,
    val pixel: String,
    val ptype: Int,
    val role: Int,
    val screen_mode: Int,
    val singername: Any,
    val songid: Any,
    val songname: Any,
    val songpic: Any,
    val sortnum: Any,
    val subject: String,
    val url_cover: Any,
    val url_preview: String,
    val url_share: String,
    val url_sound: Any,
    val url_video: String,
    val userid: Int,
    val username: String,
    val videoid: Int
)
