package com.hv.community.android.data.remote.network.api

import com.hv.community.android.data.remote.network.model.user.GetProfileRes
import retrofit2.Response
import retrofit2.http.POST

interface UserApi {
    @POST("/member/get-my-profile")
    suspend fun getProfile(): Response<GetProfileRes>
}
