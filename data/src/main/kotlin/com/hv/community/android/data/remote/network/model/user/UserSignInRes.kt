package com.hv.community.android.data.remote.network.model.user

import com.hv.community.android.data.mapper.DataMapper
import com.hv.community.android.domain.model.user.UserSignIn
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserSignInRes(
    @SerialName("access_token")
    val accessToken: String,
    @SerialName("refresh_token")
    val refreshToken: String
) : DataMapper<UserSignIn> {
    override fun toDomain(): UserSignIn {
        return UserSignIn(
            accessToken = accessToken,
            refreshToken = refreshToken
        )
    }
}
