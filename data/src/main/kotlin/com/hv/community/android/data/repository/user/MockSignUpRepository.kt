package com.hv.community.android.data.repository.user

import com.hv.community.android.data.remote.local.SharedPreferencesManager
import com.hv.community.android.domain.model.user.UserSignIn
import com.hv.community.android.domain.model.user.UserSignUp
import com.hv.community.android.domain.repository.SignUpRepository
import kotlinx.coroutines.delay

class MockSignUpRepository(
    private val sharedPreferencesManager: SharedPreferencesManager
) : SignUpRepository {

    override var emailToken: String
        set(value) = sharedPreferencesManager.setString(EMAIL_TOKEN, value)
        get() = sharedPreferencesManager.getString(EMAIL_TOKEN, "")

    override var email: String
        set(value) = sharedPreferencesManager.setString(EMAIL, value)
        get() = sharedPreferencesManager.getString(EMAIL, "")

    override var password: String
        set(value) = sharedPreferencesManager.setString(PASSWORD, value)
        get() = sharedPreferencesManager.getString(PASSWORD, "")

    override suspend fun signUp(
        email: String,
        nickname: String,
        password: String
    ): Result<UserSignUp> {
        randomLongDelay()
        return Result.success(
            UserSignUp(
                token = "email_token"
            )
        )
    }

    override suspend fun signIn(
        email: String,
        password: String
    ): Result<UserSignIn> {
        randomShortDelay()
        return Result.success(
            UserSignIn(
                accessToken = "access_token",
                refreshToken = "refresh_token"
            )
        )
    }

    override suspend fun sendEmail(
        token: String
    ): Result<Unit> {
        randomLongDelay()
        return Result.success(Unit)
    }

    override suspend fun verifyEmail(
        token: String,
        verificationCode: String
    ): Result<Unit> {
        randomShortDelay()
        return Result.success(Unit)
    }

    private suspend fun randomShortDelay() {
        delay(LongRange(100, 5000).random())
    }

    private suspend fun randomLongDelay() {
        delay(LongRange(500, 2000).random())
    }

    companion object {
        private const val EMAIL_TOKEN = "mock_email_token"
        private const val EMAIL = "mock_email"
        private const val PASSWORD = "mock_password"
    }
}
