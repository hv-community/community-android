package com.hv.community.android.data.repository.user

import com.hv.community.android.data.remote.network.api.UserApi
import com.hv.community.android.data.remote.network.util.convertResponseToDomain
import com.hv.community.android.domain.model.user.UserProfile
import com.hv.community.android.domain.repository.UserRepository

class RealUserRepository(
    private val userApi: UserApi
) : UserRepository {
    override suspend fun getProfile(): Result<UserProfile> {
        return userApi.getProfile().convertResponseToDomain()
    }
}
