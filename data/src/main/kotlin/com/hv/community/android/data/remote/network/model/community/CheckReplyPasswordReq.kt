package com.hv.community.android.data.remote.network.model.community


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CheckReplyPasswordReq(
    @SerialName("password")
    val password: String,
    @SerialName("reply_id")
    val replyId: Long
)
