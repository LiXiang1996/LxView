package com.example.lxview.function.ipc.messager.service

import android.app.Service

import android.content.Intent
import android.os.*
import android.util.Log


/**
 * author: 李 祥
 * date:   2022/2/25 4:00 下午
 * description:
 */
class MessengerService : Service() {


    private val mHandler = MessengerHandler()
    private val mMessenger: Messenger = Messenger(mHandler)

    override fun onBind(intent: Intent?): IBinder {
        return mMessenger.binder
    }


    /**
    *接受客户端的消息，其实与线程间通信一样，
    *同样是在handleMessage方法中接受；如果服务端需要回复客户端，
    *则需要拿到客户端携带过来的Messenger 对象（即msg.replyTo）通过msg.replyTo.send方法给客户端发送信息。
     */
    private class MessengerHandler : Handler() {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg) //取出客户端的消息内容
            val bundle: Bundle = msg.data
            val clientMsg = bundle.getString("client")
            Log.i(TAG, "来自客户端的消息：$clientMsg") //新建一个Message对象，作为回复客户端的对象
            val message: Message = Message.obtain()
            val bundle1 = Bundle()
            bundle1.putString("service", "今天就不去了，还有很多东西要学！！")
            message.data = bundle1
            try {
                //发送给客户端
                msg.replyTo.send(message)
            } catch (e: RemoteException) {
                e.printStackTrace()
            }
        }
    }

    companion object {
        private const val TAG = "MessengerService"
    }
}

