package com.hv.community.android.data.remote.network.model.community

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CheckPostPasswordReq(
    @SerialName("password")
    val password: String,
    @SerialName("post_id")
    val postId: Long
)