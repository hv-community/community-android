package com.hv.community.android.data.remote.network.model.community


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UpdatePostReq(
    @SerialName("content")
    val content: String,
    @SerialName("password")
    val password: String,
    @SerialName("post_id")
    val postId: Long,
    @SerialName("title")
    val title: String
)
