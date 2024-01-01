package com.hv.community.android.data.repository

import com.hv.community.android.data.remote.local.SharedPreferencesManager
import com.hv.community.android.domain.model.user.UserSignIn
import com.hv.community.android.domain.model.user.UserSignUp
import com.hv.community.android.domain.repository.UserRepository

class MockUserRepository(
    private val sharedPreferencesManager: SharedPreferencesManager
) : UserRepository {

    override var emailToken: String
        set(value) = sharedPreferencesManager.setString(EMAIL_TOKEN, value)
        get() = sharedPreferencesManager.getString(PASSWORD, "")

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
        return Result.success(Unit)
    }

    override suspend fun verifyEmail(
        token: String,
        verificationCode: String
    ): Result<Unit> {
        return Result.success(Unit)
    }

    companion object {
        private const val EMAIL_TOKEN = "mock_email_token"
        private const val EMAIL = "mock_email"
        private const val PASSWORD = "mock_password"
    }
}
