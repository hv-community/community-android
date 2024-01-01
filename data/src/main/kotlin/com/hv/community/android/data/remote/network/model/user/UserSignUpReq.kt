package com.hv.community.android.data.remote.network.model.user

import kotlinx.serialization.SerialName

data class UserSignUpReq(
    @SerialName("email")
    val email: String,
    @SerialName("password")
    val password: String,
    @SerialName("nickname")
    val nickname: String
)
