package com.example.lxview.function.jetpack.livedata

import androidx.lifecycle.MutableLiveData

import androidx.lifecycle.ViewModel

/**
 * author: 李 祥
 * date:   2022/2/16 4:25 下午
 * description:
 */
class TestViewModel : ViewModel() {
    private val mNameEvent = MutableLiveData<String>()

    fun getNameEvent(): MutableLiveData<String> {
        return mNameEvent
    }

}
