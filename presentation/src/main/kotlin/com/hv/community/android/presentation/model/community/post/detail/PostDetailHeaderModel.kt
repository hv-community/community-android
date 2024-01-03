package com.hv.community.android.presentation.model.community.post.detail

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PostDetailHeaderModel(
    val id: Long = 0L,
    val title: String = "",
    val nickname: String = "",
    val isInit: Boolean = true,
    val content: String = ""
) : Parcelable
