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
    @POST("/member/v1/signup")
    suspend fun signUp(
        @Body body: UserSignUpReq,
    ): Response<UserSignUpRes>

    @POST("/member/v1/email/send")
    suspend fun sendEmail(
        @Body body: UserSendEmailReq,
    ): Response<Unit>

    @POST("/member/v1/email/activate")
    suspend fun verifyEmail(
        @Body body: UserVerifyEmailReq,
    ): Response<Unit>

    @POST("/member/v1/signin")
    suspend fun signIn(
        @Body body: UserSignInReq,
    ): Response<UserSignInRes>
}
