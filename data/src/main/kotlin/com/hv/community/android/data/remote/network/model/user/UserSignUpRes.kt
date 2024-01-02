package com.hv.community.android.data.remote.network.model.user

import com.hv.community.android.data.mapper.DataMapper
import com.hv.community.android.domain.model.user.UserSignUp
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserSignUpRes(
    @SerialName("token")
    val token: String
) : DataMapper<UserSignUp> {
    override fun toDomain(): UserSignUp {
        return UserSignUp(
            token = token
        )
    }
}
