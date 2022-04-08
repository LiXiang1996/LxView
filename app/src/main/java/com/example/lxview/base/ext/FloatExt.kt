package com.example.lxview.base.ext

import com.example.lxview.base.utils.ScreenUtils

val Float.toPX: Int get() = (this * ScreenUtils.screenDensity).toInt()

val Float.toDP: Int get() = (this / ScreenUtils.screenDensity).toInt()