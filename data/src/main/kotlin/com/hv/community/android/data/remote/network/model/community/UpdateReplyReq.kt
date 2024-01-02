package com.hv.community.android.data.remote.network.model.community


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UpdateReplyReq(
    @SerialName("password")
    val password: String,
    @SerialName("reply")
    val reply: String,
    @SerialName("reply_id")
    val replyId: Long
)
