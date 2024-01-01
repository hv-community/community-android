package com.hv.community.android.presentation.common.util

import android.content.res.ColorStateList
import android.content.res.TypedArray
import android.graphics.drawable.Drawable
import androidx.annotation.StyleableRes
import androidx.core.content.res.getColorOrThrow
import androidx.core.content.res.getColorStateListOrThrow
import androidx.core.content.res.getDrawableOrThrow

inline fun TypedArray.getColor(
    @StyleableRes index: Int,
    onSuccess: (Int) -> Unit
) {
    if (hasValue(index)) {
        val value = getColorOrThrow(index)
        onSuccess(value)
    }
}

inline fun TypedArray.getColorStateList(
    @StyleableRes index: Int,
    onSuccess: (ColorStateList) -> Unit
) {
    if (hasValue(index)) {
        val value = getColorStateListOrThrow(index)
        onSuccess(value)
    }
}

inline fun TypedArray.getDrawable(
    @StyleableRes index: Int,
    onSuccess: (Drawable) -> Unit
) {
    if (hasValue(index)) {
        val value = getDrawableOrThrow(index)
        onSuccess(value)
    }
}
