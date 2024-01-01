package com.hv.community.android.data.remote.network.model.user

import kotlinx.serialization.SerialName

data class UserSignInReq(
    @SerialName("email")
    val email: String,
    @SerialName("password")
    val password: String
)
