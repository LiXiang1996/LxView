package com.example.sharelib.service

import rx.Observable

/**
 * author: lx
 * Date: 2018/11/2 14:04
 * Description:
 */
interface ShareService {

    /**
     * 获取分享信息
     */
    fun getShare(userid: Int, pid: Int, scene: Int, platform: Int): Observable<String?>

    fun getShare(userid: Int, id: String, scene: Int, platform: Int): Observable<String?>

    /**
     * 回调分享结果
     */
    fun reportShare(userid: Int, token: String, shareid: Int, status: Int): Observable<String?>


    fun behaviorEvent(eventType: String, sourceId: String, pid: String): Observable<String?>

    fun behaviorFeedNameEvent(eventType: String, sourceId: String, feedName: String): Observable<String?>
}
