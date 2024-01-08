package com.hv.community.android.data.repository.authentication

import com.hv.community.android.data.remote.local.ErrorMessageMapper
import com.hv.community.android.data.remote.local.SharedPreferencesManager
import com.hv.community.android.data.remote.network.api.AuthenticationApi
import com.hv.community.android.data.remote.network.model.authentication.GetAccessTokenReq
import com.hv.community.android.data.remote.network.util.convertResponse
import com.hv.community.android.domain.repository.AuthenticationRepository

class RealAuthenticationRepository(
    private val authenticationApi: AuthenticationApi,
    private val sharedPreferencesManager: SharedPreferencesManager,
    private val errorMessageMapper: ErrorMessageMapper
) : AuthenticationRepository {

    override var refreshToken: String
        set(value) = sharedPreferencesManager.setString(REFRESH_TOKEN, value)
        get() = sharedPreferencesManager.getString(REFRESH_TOKEN, "")
    override var accessToken: String
        set(value) = sharedPreferencesManager.setString(ACCESS_TOKEN, value)
        get() = sharedPreferencesManager.getString(ACCESS_TOKEN, "")

    override suspend fun getAccessToken(
        refreshToken: String
    ): Result<String> {
        return authenticationApi.getAccessToken(
            GetAccessTokenReq(
                refreshToken = refreshToken
            )
        ).convertResponse(errorMessageMapper::map).map {
            it.accessToken
        }
    }

    override suspend fun decrypt(message: String): Result<String> {
        TODO("Not yet implemented")
    }

    companion object {
        private const val REFRESH_TOKEN = "refresh_token"
        private const val ACCESS_TOKEN = "access_token"
    }
}
