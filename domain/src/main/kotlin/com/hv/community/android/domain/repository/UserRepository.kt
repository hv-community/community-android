package com.hv.community.android.domain.repository

import com.hv.community.android.domain.model.user.UserSignIn
import com.hv.community.android.domain.model.user.UserSignUp

interface UserRepository {

    var emailToken: String

    var email: String

    var password: String

    suspend fun signUp(
        email: String,
        nickname: String,
        password: String
    ): Result<UserSignUp>

    suspend fun signIn(
        email: String,
        password: String
    ): Result<UserSignIn>

    suspend fun sendEmail(
        token: String
    ): Result<Unit>

    suspend fun verifyEmail(
        token: String,
        verificationCode: String
    ): Result<Unit>
}
