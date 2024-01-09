package com.hv.community.android.data.remote.network.model.community

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UpdateReplyReq(
    @SerialName("content")
    val content: String,
    @SerialName("password")
    val password: String
)
