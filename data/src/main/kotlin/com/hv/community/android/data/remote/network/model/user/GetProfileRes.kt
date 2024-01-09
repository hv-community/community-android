package com.hv.community.android.data.remote.network.model.user

import com.hv.community.android.data.mapper.DataMapper
import com.hv.community.android.domain.model.user.UserProfile
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetProfileRes(
    @SerialName("id")
    val id: Long = -1L,
    @SerialName("email")
    val email: String,
    @SerialName("nickname")
    val nickname: String
) : DataMapper<UserProfile> {
    override fun toDomain(): UserProfile {
        return UserProfile(
            id = id,
            email = email,
            nickname = nickname
        )
    }
}
