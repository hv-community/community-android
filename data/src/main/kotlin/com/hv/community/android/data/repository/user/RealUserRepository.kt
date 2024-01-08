package com.hv.community.android.data.repository.user

import com.hv.community.android.data.remote.local.ErrorMessageMapper
import com.hv.community.android.data.remote.network.api.UserApi
import com.hv.community.android.data.remote.network.util.convertResponseToDomain
import com.hv.community.android.domain.model.user.UserProfile
import com.hv.community.android.domain.repository.UserRepository

class RealUserRepository(
    private val userApi: UserApi,
    private val errorMessageMapper: ErrorMessageMapper
) : UserRepository {
    override suspend fun getProfile(): Result<UserProfile> {
        return userApi.getProfile().convertResponseToDomain(errorMessageMapper::map)
    }
}
