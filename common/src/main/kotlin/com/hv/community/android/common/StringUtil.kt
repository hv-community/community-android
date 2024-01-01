package com.hv.community.android.common

fun Char?.orEmpty(): Char {
    return this ?: Char.MIN_VALUE
}