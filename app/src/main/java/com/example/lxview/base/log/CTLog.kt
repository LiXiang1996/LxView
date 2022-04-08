package com.example.lxview.base.log

import android.util.Log

class CTLog {
    companion object{
        private var Tag = "CTLog"
        fun d(msg: String){
            Log.d(Tag,msg)
        }

        fun d(tag:String, msg:String){
            Log.d(tag,msg)
        }

        fun e(throws: Throwable){
            Log.e(Tag,throws.toString(),throws)
        }

        fun e(msg:String, throws: Throwable? = null){
            Log.e(Tag,msg,throws)
        }

        fun e(tag: String, msg: String? = null,throws: Throwable? = null){
            Log.e(tag,msg,throws)
        }
    }
    var mTag = Tag
    constructor()
    constructor(tag:String){
        this.mTag = tag
    }

    fun d(msg:String){ d(mTag,msg) }
    fun e(msg: String? = null, throws: Throwable? = null){ e(mTag,msg,throws) }
    fun e(throws: Throwable){e(mTag,null,throws)}
}