package com.hv.community.android.data.remote.network.model.authentication

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetAccessTokenReq(
    @SerialName("refresh_token")
    val refreshToken: String
)
