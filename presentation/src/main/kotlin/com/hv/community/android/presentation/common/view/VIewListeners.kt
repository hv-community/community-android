package com.hv.community.android.presentation.common.view

fun interface OnTextChangedListener {
    fun onChanged(text: String)
}

fun interface OnStateChangedListener {
    fun onChanged(state: Boolean)
}
