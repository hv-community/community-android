package com.hv.community.android.data.remote.network.model.authentication

import kotlinx.serialization.SerialName

data class GetAccessTokenRes(
    @SerialName("access_token")
    val accessToken: String
)
