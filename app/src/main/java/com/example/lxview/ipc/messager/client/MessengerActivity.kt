package com.example.lxview.ipc.messager.client

import android.content.ComponentName

import android.content.ServiceConnection
import com.example.lxview.ipc.messager.service.MessengerService

import android.content.Intent
import android.os.*
import android.util.Log
import android.view.View
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import com.example.lxview.R


/**
 * author: 李 祥
 * date:   2022/2/25 4:04 下午
 * description:客户端
 */
class MessengerActivity : AppCompatActivity() {

    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_messenger_client_layout)
    }

    fun onClick(view: View) {
        when (view.id) {
            R.id.bindService -> {
                val intent = Intent(this, MessengerService::class.java)
                bindService(intent, mConnection, BIND_AUTO_CREATE)
            }
            R.id.unbindService -> unbindService(mConnection)
        }
    }

    private val mConnection: ServiceConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName, service: IBinder) { //获取服务端关联的Messenger对象
            val mService = Messenger(service) //创建Message对象
            val message: Message = Message.obtain()
            val bundle = Bundle()
            bundle.putString("client", "今天出去玩吗？")
            message.data = bundle //在message中添加一个回复mRelyMessenger对象
            message.replyTo = mRelyMessenger
            try {
                //发送消息
                mService.send(message)
            } catch (e: RemoteException) {
                e.printStackTrace()
            }
        }

        override fun onServiceDisconnected(name: ComponentName) {}
    }
    private val mGetRelyHandler = GetRelyHandler()
    private val mRelyMessenger: Messenger = Messenger(mGetRelyHandler)

    class GetRelyHandler : Handler() {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            val bundle: Bundle = msg.data
            val serviceMsg = bundle.getString("service")
            Log.i(TAG, "来自服务端的回复：$serviceMsg")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    companion object {
        private const val TAG = "MessengerActivity"
    }
}

