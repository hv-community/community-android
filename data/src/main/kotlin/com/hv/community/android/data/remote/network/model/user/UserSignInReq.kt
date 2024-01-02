package com.hv.community.android.data.remote.network.model.user

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserSignInReq(
    @SerialName("email")
    val email: String,
    @SerialName("password")
    val password: String
)
