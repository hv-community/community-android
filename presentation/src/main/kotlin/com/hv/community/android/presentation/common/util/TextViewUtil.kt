package com.hv.community.android.presentation.common.util

import android.graphics.Paint
import androidx.appcompat.widget.AppCompatTextView
import androidx.databinding.BindingAdapter

@BindingAdapter("underline")
fun AppCompatTextView.setUnderline(
    isUnderline: Boolean
) {
    paintFlags = if (isUnderline) {
        paintFlags or Paint.UNDERLINE_TEXT_FLAG
    } else {
        paintFlags and Paint.UNDERLINE_TEXT_FLAG.inv()
    }
}
