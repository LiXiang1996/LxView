/**
 * author: 李 祥
 * date:   2022/2/23 2:26 下午
 * description:
 */
package com.example.lxview

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by Administrator on 2016/1/27.
 */
open class Dog : Parcelable {
    var gender = 0
    var name: String? = null

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeInt(gender)
        dest.writeString(name)
    }

    constructor() {}
    protected constructor(`in`: Parcel) {
        gender = `in`.readInt()
        name = `in`.readString()
    }


    companion object CREATOR : Parcelable.Creator<Dog> {
        override fun createFromParcel(parcel: Parcel): Dog {
            return Dog(parcel)
        }

        override fun newArray(size: Int): Array<Dog?> {
            return arrayOfNulls(size)
        }
    }
}