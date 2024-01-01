package com.hv.community.android.data.remote.network.model.user

import kotlinx.serialization.SerialName

data class UserSendEmailReq(
    @SerialName("token")
    val token: String
)
