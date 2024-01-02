package com.hv.community.android.data.remote.network.model.community

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreatePostReq(
    @SerialName("community_id")
    val communityId: Long,
    @SerialName("content")
    val content: String,
    @SerialName("title")
    val title: String,
    @SerialName("nickname")
    val nickname: String,
    @SerialName("password")
    val password: String,
)
