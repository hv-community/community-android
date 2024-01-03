package com.hv.community.android.data.repository.user

import com.hv.community.android.data.remote.local.SharedPreferencesManager
import com.hv.community.android.data.remote.network.api.SignUpApi
import com.hv.community.android.data.remote.network.model.user.UserSendEmailReq
import com.hv.community.android.data.remote.network.model.user.UserSignInReq
import com.hv.community.android.data.remote.network.model.user.UserSignUpReq
import com.hv.community.android.data.remote.network.model.user.UserVerifyEmailReq
import com.hv.community.android.data.remote.network.util.convertResponse
import com.hv.community.android.data.remote.network.util.convertResponseToDomain
import com.hv.community.android.domain.model.user.UserSignIn
import com.hv.community.android.domain.model.user.UserSignUp
import com.hv.community.android.domain.repository.SignUpRepository

class RealSignUpRepository(
    private val signUpApi: SignUpApi,
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
        return signUpApi.signUp(
            UserSignUpReq(
                email = email,
                nickname = nickname,
                password = password
            )
        ).convertResponseToDomain()
    }

    override suspend fun signIn(
        email: String,
        password: String
    ): Result<UserSignIn> {
        return signUpApi.signIn(
            UserSignInReq(
                email = email,
                password = password
            )
        ).convertResponseToDomain()
    }

    override suspend fun sendEmail(
        token: String
    ): Result<Unit> {
        return signUpApi.sendEmail(
            UserSendEmailReq(
                token = token
            )
        ).convertResponse()
    }

    override suspend fun verifyEmail(
        token: String,
        verificationCode: String
    ): Result<Unit> {
        return signUpApi.verifyEmail(
            UserVerifyEmailReq(
                token = token,
                verificationCode = verificationCode
            )
        ).convertResponse()
    }

    companion object {
        private const val EMAIL_TOKEN = "email_token"
        private const val EMAIL = "email"

        // TODO : Biometric Authentication
        private const val PASSWORD = "password"
    }
}
