package com.example.lxview.function.ipc.binder

import android.annotation.SuppressLint
import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.os.RemoteException
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.example.lxview.*
import com.example.lxview.base.BaseActivity

/**
 * author: 李 祥
 * date:   2022/2/24 2:25 下午
 * description:
 */
class AidlBinderActivity:BaseActivity() {
    private val TAG = "BinderSimple"
    private var mBinder: IDogManager? = null

    private var mIsBound = false
    private var mCallBackTv: TextView? = null

    override val contentId: Int
        get() = R.layout.activity_aidl_binder_client_layout

    override fun onCreate(instanceState: Bundle?) {
        super.onCreate(instanceState)


        mCallBackTv = findViewById<View>(R.id.tv_callback) as TextView
        mCallBackTv?.text="未连接"

    }


    @SuppressLint("NonConstantResourceId")
    fun clickHandler(view: View) {
        when (view.id) {
            R.id.btn_bind -> bindRemoteService()
            R.id.btn_unbind -> unbindRemoteService()
            R.id.btn_kill -> killRemoteService()
        }
    }

    /**
     * 用语监控远程服务连接的状态
     */
    private val mConnection: ServiceConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName, service: IBinder) {
            //在得到代理对象Binder时，不能直接使用强转的方式，否则会报类型转换异常，
            // 必须使用IBookManager.Stub.asInterface()得到AIDL接口文件生成的Binder对象。

            mBinder = IDogManager.Stub.asInterface(service)
            var pidInfo: String? = "狗狗列表： "
            val myData: MutableList<Dog>? = mBinder?.dogList
            try {
                myData?.forEach{
                    pidInfo += it.name
                }
            } catch (e: RemoteException) {
                e.printStackTrace()
            }
            Log.d(TAG, "[ClientActivity] onServiceConnected  $pidInfo")

            mCallBackTv!!.text = pidInfo
            Toast.makeText(this@AidlBinderActivity, "连接", Toast.LENGTH_SHORT).show()
        }

        override fun onServiceDisconnected(name: ComponentName) {
            Log.d(TAG, "[ClientActivity] onServiceDisconnected")
            mCallBackTv?.text = "断开连接"
            mBinder = null
            Toast.makeText(this@AidlBinderActivity, "断开连接", Toast.LENGTH_SHORT).show()
        }
    }

    /**
     * 绑定远程服务
     */
    private fun bindRemoteService() {
        Log.d(TAG, "[ClientActivity] bindRemoteService")
        val intent = Intent(this, RemoteService::class.java)
        intent.action = IDogManager::class.java.name
        bindService(intent, mConnection, BIND_AUTO_CREATE)
        mIsBound = true
        mCallBackTv?.setText(R.string.binding)
    }

    /**
     * 解除绑定远程服务
     */
    private fun unbindRemoteService() {
        if (!mIsBound) {
            return
        }
        Log.e(TAG, "[ClientActivity] unbindRemoteService ==>")
        unbindService(mConnection)
        mIsBound = false
        mCallBackTv?.setText(R.string.unbinding)
    }

    /**
     * 杀死远程服务
     */
    private fun killRemoteService() {
        Log.i(TAG, "[ClientActivity] killRemoteService")
        try {
//            Process.killProcess(mBinder?.dogList.hashCode())
            mCallBackTv?.setText(R.string.kill_success)
        } catch (e: RemoteException) {
            e.printStackTrace()
            Toast.makeText(this@AidlBinderActivity, R.string.kill_failure, Toast.LENGTH_SHORT).show()
        }
    }
}