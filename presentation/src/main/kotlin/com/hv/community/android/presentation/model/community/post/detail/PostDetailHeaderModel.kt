package com.hv.community.android.presentation.model.community.post.detail

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PostDetailHeaderModel(
    val id: Long = -1L,
    val title: String = "",
    val nickname: String = "",
    val isInit: Boolean = true,
    val content: String = ""
) : Parcelable
