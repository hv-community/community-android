package com.hv.community.android.data.remote.network.model.community


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreateReplyReq(
    @SerialName("nickname")
    val nickname: String,
    @SerialName("password")
    val password: String,
    @SerialName("post_id")
    val postId: Long,
    @SerialName("reply")
    val reply: String
)
