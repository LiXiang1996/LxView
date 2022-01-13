package com.example.sharelib.service.impl

import com.example.sharelib.data.repository.ShareRepository
import com.example.sharelib.service.ShareService
import rx.Observable
import javax.inject.Inject

/**
 * author: lx
 * Description: 分享接口请求
 */
abstract class ShareServiceImpl @Inject constructor() : ShareService {

//    var repository: ShareRepository = ShareRepository()
//
//    override fun getShare(userid: Int, pid: Int, scene: Int, platform: Int): Observable<String?> =
//            repository.getShare(userid, pid, scene, platform)
//
//
//    override fun getShare(userid: Int, id: String, scene: Int, platform: Int): Observable<String?> =
//            repository.getShare(userid, id, scene, platform)
//
//
//    override fun reportShare(userid: Int, token: String, shareid: Int, status: Int): Observable<String?> =
//            repository.reportShare(userid, token, shareid, status)
//
//
//    override fun behaviorEvent(eventType: String, sourceId: String, pid: String): Observable<String?> =
//            repository.behaviorEvent(eventType, sourceId, pid)
//
//    override fun behaviorFeedNameEvent(eventType: String, sourceId: String, feedName: String): Observable<String?> =
//            repository.behaviorFeedNameEvent(eventType, sourceId, feedName)
}
