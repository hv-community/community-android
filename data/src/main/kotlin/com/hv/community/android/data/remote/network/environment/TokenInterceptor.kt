package com.hv.community.android.data.remote.network.environment

import com.hv.community.android.domain.repository.AuthenticationRepository
import okhttp3.Interceptor
import okhttp3.Response

class TokenInterceptor(
    private val authenticationRepository: AuthenticationRepository
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(
            chain.request().newBuilder()
                .addHeader("Authorization", "Bearer ${authenticationRepository.accessToken}")
                .build()
        )
    }
}
