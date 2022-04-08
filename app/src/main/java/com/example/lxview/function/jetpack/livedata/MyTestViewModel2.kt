package com.example.lxview.function.jetpack.livedata

import androidx.lifecycle.MutableLiveData

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider




/**
 * author: 李 祥
 * date:   2022/2/16 4:25 下午
 * description:
 */
class TestViewModel2(key: String) : ViewModel() {

    private val mKey: String = key
    private val mNameEvent = MutableLiveData<String>()
    fun getNameEvent(): MutableLiveData<String> {
        return mNameEvent
    }


    class Factory(private val mKey: String) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return TestViewModel2(mKey) as T
        }
    }

    fun getKey(): String {
        return mKey
    }

}

