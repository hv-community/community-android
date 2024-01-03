package com.hv.community.android.data.remote.network.api

import com.hv.community.android.data.remote.network.model.user.UserSendEmailReq
import com.hv.community.android.data.remote.network.model.user.UserSignInReq
import com.hv.community.android.data.remote.network.model.user.UserSignInRes
import com.hv.community.android.data.remote.network.model.user.UserSignUpReq
import com.hv.community.android.data.remote.network.model.user.UserSignUpRes
import com.hv.community.android.data.remote.network.model.user.UserVerifyEmailReq
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface SignUpApi {
    @POST("/member/signup")
    suspend fun signUp(
        @Body body: UserSignUpReq,
    ): Response<UserSignUpRes>

    @POST("/member/send-email-verification-code")
    suspend fun sendEmail(
        @Body body: UserSendEmailReq,
    ): Response<Unit>

    @POST("/member/activate-email")
    suspend fun verifyEmail(
        @Body body: UserVerifyEmailReq,
    ): Response<Unit>

    @POST("/member/signin")
    suspend fun signIn(
        @Body body: UserSignInReq,
    ): Response<UserSignInRes>
}
