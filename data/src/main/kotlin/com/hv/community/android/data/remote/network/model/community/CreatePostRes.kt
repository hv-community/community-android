package com.hv.community.android.data.remote.network.model.community

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreatePostRes(
    @SerialName("post_id")
    val postId: Long
)
