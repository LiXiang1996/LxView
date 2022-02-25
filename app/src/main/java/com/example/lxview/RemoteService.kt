package com.example.lxview

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.os.RemoteException
import android.util.Log
import java.util.ArrayList


/**
 * author: 李 祥
 * date:   2022/2/23 4:06 下午
 * description:服务器端
 */


class RemoteService : Service() {
    var str :String?="狗狗列表："

    private val mDogsList: MutableList<Dog> = ArrayList()
    private val mBinder: DogManagerImpl = object : DogManagerImpl() {

        override fun getDogList(): MutableList<Dog> {
            return mDogsList
        }

        @Throws(RemoteException::class)
        override fun addDog(dog: Dog) {
            mDogsList.add(dog)
        }
    }

    override fun onBind(intent: Intent?): IBinder {
        mDogsList.forEach{
            str += it.name
        }
        Log.d("bind",str.toString())
        return mBinder
    }

    override fun onCreate() {
        super.onCreate()
        initDogData()
    }

    override fun onUnbind(intent: Intent?): Boolean {

        Log.d("unbind",str.toString())
        return super.onUnbind(intent)
    }

    override fun onDestroy() {
        Log.e("Destroy","RemoteService")
        super.onDestroy()
    }

    private fun initDogData() {
        val dog1 = Dog().apply {
            this.gender = 0
            this.name = "伟伟 "
        }
        val dog2 = Dog().apply {
            this.gender = 0
            this.name = "阿浪 "
        }
        val dog3 = Dog().apply {
            this.gender = 0
            this.name = "聪酱 "
        }
        val dog4 = Dog().apply {
            this.gender = 0
            this.name = "柏林锅锅 "
        }
        mBinder.addDog(dog3)
        mBinder.addDog(dog4)

        mDogsList.add(0,dog1)
        mDogsList.add(1,dog2)
    }
}