package com.hv.community.android.data.remote.network.model.user

import kotlinx.serialization.SerialName

data class UserVerifyEmailReq(
    @SerialName("token")
    val token: String,
    @SerialName("verification_code")
    val verificationCode: String
)
