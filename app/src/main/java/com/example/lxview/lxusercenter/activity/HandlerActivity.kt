package com.example.lxview.lxusercenter.activity

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.lxview.R


/**
 * author: 李 祥
 * date:   2022/11/23 13:54
 * description:
 */
class HandlerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_handler_layout)
        val mainHandler: Handler = object : Handler() {
            override fun handleMessage(msg: Message) {
                Log.i(TAG, "mainHandler handle message")
            }
        }
        //起一个线程进行一些例如耗时操作等等，完成后，
        val thread = Thread {
            val handler: Handler = object : Handler(Looper.getMainLooper()) {
                override fun handleMessage(msg: Message) {
                    if (msg.what ==1) {
                        Toast.makeText(this@HandlerActivity, "${msg.what}", Toast.LENGTH_SHORT).show()
                    }else{
                        Toast.makeText(this@HandlerActivity, "-----${msg.what}------", Toast.LENGTH_SHORT).show()
                    }
                }
            }
           // 例如在下面进行耗时操作，操作完后，sendToTarget()
            val message = Message.obtain(handler)
            message.what = 2
//            handler.sendMessage(message)  sendToTarget和此方法一样，都是跳转到handler里面，target.sendMessage(this);target就是handler
            message.sendToTarget()
        }
        thread.start()
    }

    companion object {
        private const val TAG = "Handler"
    }
}