package com.example.loading

/**
 * author: 李 祥
 * date:   2022/1/10 6:48 下午
 * description:
 */
enum class DisplayMode(val value: Int) {
    NONE(0), LOADING(1), NO_DATA(2), NO_NETWORK(3), NORMAL(4);

    var delay = 0L
    fun delay(mills: Long): DisplayMode {
        delay = mills
        return this
    }
    fun reset() {
        delay = 0L
    }
}



