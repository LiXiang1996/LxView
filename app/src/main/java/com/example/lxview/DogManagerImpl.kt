package com.example.lxview

import android.os.*
import com.example.lxview.IDogManager

/**
 * author: 李 祥
 * date:   2022/2/23 6:18 下午
 * description:
 */


/**
 * Created by Administrator on 2016/1/27.
 */
abstract class DogManagerImpl : Binder(), IDogManager {

    override fun asBinder(): IBinder {
        return this
    }

    @Throws(RemoteException::class)
    override fun onTransact(code: Int, data: Parcel, reply: Parcel?, flags: Int): Boolean {
        when (code) {
            INTERFACE_TRANSACTION -> {
                reply!!.writeString(DESCRIPTOR)
                return true
            }
            TRANSACTION_getDogList -> {
                data.enforceInterface(DESCRIPTOR)
                val _result: List<Dog> = this.getDogList()
                reply!!.writeNoException()
                reply.writeTypedList(_result)
                return true
            }
            TRANSACTION_addDog -> {
                data.enforceInterface(DESCRIPTOR)
                val _arg0: Dog? = if (0 != data.readInt()) {
                    Dog.createFromParcel(data)
                } else {
                    null
                }
                this.addDog(_arg0)
                reply!!.writeNoException()
                return true
            }
        }
        return super.onTransact(code, data, reply, flags)
    }

    private class Proxy internal constructor(private val mRemote: IBinder) : DogManagerImpl() {
        override fun asBinder(): IBinder {
            return mRemote
        }

        override fun getInterfaceDescriptor(): String? {
            return DESCRIPTOR
        }

        override fun getDogList(): MutableList<Dog> {
            val _data = Parcel.obtain()
            val _reply = Parcel.obtain()
            val _result: MutableList<Dog>
            try {
                _data.writeInterfaceToken(DESCRIPTOR)
                mRemote.transact(TRANSACTION_getDogList, _data, _reply, 0)
                _reply.readException()
                _result = _reply.createTypedArrayList<Dog>(Dog.CREATOR)!!
            } finally {
                _reply.recycle()
                _data.recycle()
            }
            return _result
        }


        override fun addDog(dog: Dog?) {
            val _data = Parcel.obtain()
            val _reply = Parcel.obtain()
            try {
                _data.writeInterfaceToken(DESCRIPTOR)
                if (dog != null) {
                    _data.writeInt(1)
                    dog.writeToParcel(_data, 0)
                } else {
                    _data.writeInt(0)
                }
                mRemote.transact(TRANSACTION_addDog, _data, _reply, 0)
                _reply.readException()
            } finally {
                _reply.recycle()
                _data.recycle()
            }
        }
    }

    companion object {
        fun asInterface(obj: IBinder?): IDogManager? {
            if (obj == null) {
                return null
            }
            val iin = obj.queryLocalInterface(DESCRIPTOR) //如果是同1个进程，也就是说进程内通信的话 我们就返回括号内里的对象
            return if (iin != null && iin is IDogManager) {
                iin
            } else Proxy(obj) //如果不是同一进程，是2个进程之间相互通信，那我们就得返回这个Stub.Proxy 看上去叫Stub 代理的对象了
        }

        const val DESCRIPTOR: String = "com.example.lxview.IDogManager";
        const val TRANSACTION_getDogList: Int = IBinder.FIRST_CALL_TRANSACTION + 0;
        const val TRANSACTION_addDog: Int = IBinder.FIRST_CALL_TRANSACTION + 1;
    }

    init {
        attachInterface(this, DESCRIPTOR)
    }
}