package com.hv.community.android.data.remote.network.api

import com.hv.community.android.data.remote.network.model.authentication.GetAccessTokenReq
import com.hv.community.android.data.remote.network.model.authentication.GetAccessTokenRes
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthenticationApi {
    @POST("/member/v1/refresh")
    suspend fun getAccessToken(
        @Body body: GetAccessTokenReq,
    ): Response<GetAccessTokenRes>
}
