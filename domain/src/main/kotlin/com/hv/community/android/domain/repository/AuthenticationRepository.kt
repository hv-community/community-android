package com.hv.community.android.domain.repository

interface AuthenticationRepository {

    var refreshToken: String

    var accessToken: String

    suspend fun getAccessToken(
        refreshToken: String
    ): Result<String>

    suspend fun decrypt(
        message: String
    ): Result<String>
}
