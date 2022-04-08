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

    var repository: ShareRepository = ShareRepository()

}
