package com.hv.community.android.presentation.model.community.post.detail

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

// TODO : sealed 로 분리
@Parcelize
data class PostDetailFooterModel(
    val id: Long = -1L,
    val isLogined: Boolean = false,
    val isInit: Boolean = true,
    val nickname: String = "",
    val password: String = "",
    val content: String = ""
) : Parcelable
