package com.hv.community.android.data.remote.network.api

import com.hv.community.android.data.remote.network.model.user.GetProfileRes
import retrofit2.Response
import retrofit2.http.GET

interface UserApi {
    @GET("/member/get-my-profile")
    suspend fun getProfile(): Response<GetProfileRes>
}
