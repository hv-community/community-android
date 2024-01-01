package com.hv.community.android.data.repository.authentication

import com.hv.community.android.data.remote.local.SharedPreferencesManager
import com.hv.community.android.domain.repository.AuthenticationRepository
import kotlinx.coroutines.delay

class MockAuthenticationRepository(
    private val sharedPreferencesManager: SharedPreferencesManager
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
        randomShortDelay()
        return Result.success("refresh_token")
    }

    override suspend fun decrypt(message: String): Result<String> {
        TODO("Not yet implemented")
    }

    private suspend fun randomShortDelay() {
        delay(LongRange(100, 5000).random())
    }

    private suspend fun randomLongDelay() {
        delay(LongRange(500, 2000).random())
    }

    companion object {
        private const val REFRESH_TOKEN = "mock_refresh_token"
        private const val ACCESS_TOKEN = "mock_access_token"
    }
}
