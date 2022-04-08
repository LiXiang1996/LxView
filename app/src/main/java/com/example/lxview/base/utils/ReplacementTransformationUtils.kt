package com.example.lxview.base.utils

import android.text.method.ReplacementTransformationMethod


/**
 * @author: JayQiu
 * @date: 2022/2/22
 * @description:
 */
class ReplacementTransformationUtils : ReplacementTransformationMethod() {
    override fun getReplacement(): CharArray {
        return charArrayOf('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z')
    }

    override fun getOriginal(): CharArray {
        return charArrayOf('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z')
    }
}