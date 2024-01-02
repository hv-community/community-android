package com.hv.community.android.data.remote.network.model.error

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ErrorRes(
    @SerialName("code")
    val code: Int,
    @SerialName("message")
    val message: String
)
