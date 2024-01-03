package com.hv.community.android.presentation.ui.community.post.detail.more

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

sealed interface PostDetailMoreResult : Parcelable {
    @Parcelize
    data object Noting : PostDetailMoreResult

    @Parcelize
    data class Fail(
        val exception: Throwable
    ) : PostDetailMoreResult

    @Parcelize
    data class Error(
        val exception: Throwable
    ) : PostDetailMoreResult

    @Parcelize
    data object Edit : PostDetailMoreResult

    @Parcelize
    data object Delete : PostDetailMoreResult
}
