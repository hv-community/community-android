package com.hv.community.android.data.remote.network.model.community

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreateReplyRes(
    @SerialName("id")
    val id: Long = -1L,
)
