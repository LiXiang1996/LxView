package com.example.lxview.base.ext

import com.example.lxview.base.utils.ScreenUtils


val Double.toPX: Int get() = (this * ScreenUtils.screenDensity).toInt()

val Double.toDP: Int get() = (this / ScreenUtils.screenDensity).toInt()