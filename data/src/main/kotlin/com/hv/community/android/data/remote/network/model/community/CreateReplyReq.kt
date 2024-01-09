package com.hv.community.android.data.remote.network.model.community

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreateReplyReq(
    @SerialName("content")
    val content: String,
    @SerialName("nickname")
    val nickname: String,
    @SerialName("password")
    val password: String
)
