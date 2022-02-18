package com.example.lxview.jetpack.lifecycle

import androidx.lifecycle.MutableLiveData

import androidx.lifecycle.ViewModel

/**
 * author: 李 祥
 * date:   2022/2/16 4:25 下午
 * description:
 */
class MyLifeTestViewModel : ViewModel() {
    private var mNameEvent = MutableLiveData<String>()

    fun getNameEvent(): MutableLiveData<String> {
        return mNameEvent
    }

}
