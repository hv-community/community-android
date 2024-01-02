package com.hv.community.android.data.remote.network.environment

import com.hv.community.android.domain.repository.AuthenticationRepository
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import timber.log.Timber
import java.net.HttpURLConnection

class TokenAuthenticator(
    private val authenticationRepository: AuthenticationRepository
) : Authenticator {
    override fun authenticate(route: Route?, response: Response): Request? {
        synchronized(this) {
            if (response.code != HttpURLConnection.HTTP_UNAUTHORIZED) return null

            return runBlocking {
                authenticationRepository.getAccessToken(
                    authenticationRepository.refreshToken
                ).map { accessToken ->
                    authenticationRepository.accessToken = accessToken
                    response.request.newBuilder().apply {
                        removeHeader("Authorization")
                        addHeader("Authorization", "Bearer $accessToken")
                    }.build()
                }.onFailure {
                    Timber.e(it)
                }.getOrNull()
            }
        }
    }
}
