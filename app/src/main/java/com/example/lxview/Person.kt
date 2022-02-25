package com.example.lxview

import android.os.Parcel
import android.os.Parcelable

/**
 * author: 李 祥、
 * date:   2022/2/23 11:41 上午
 * description:
 */


/**
 * Created by Administrator on 2016/1/27.
 */
class Person : Parcelable {
    var name: String? = null
    var gender = 0

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(name)
        dest.writeInt(gender)
    }

    constructor() {}
    protected constructor(`in`: Parcel) {
        name = `in`.readString()
        gender = `in`.readInt()
    }

    companion object CREATOR : Parcelable.Creator<Person> {
        override fun createFromParcel(parcel: Parcel): Person {
            return Person(parcel)
        }

        override fun newArray(size: Int): Array<Person?> {
            return arrayOfNulls(size)
        }
    }
}