package com.hv.community.android.data.remote.network.environment

import com.hv.community.android.domain.repository.AuthenticationRepository
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import timber.log.Timber

class TokenAuthenticator(
    private val authenticationRepository: AuthenticationRepository
) : Authenticator {
    override fun authenticate(route: Route?, response: Response): Request? {
        var goToLoginScreen: Boolean = false

        val request = synchronized(this) {
            return@synchronized runBlocking {
                val refreshToken = authenticationRepository.refreshToken.ifEmpty { null }
                val accessToken = refreshToken?.let {
                    authenticationRepository.getAccessToken(
                        it
                    ).map { accessToken ->
                        authenticationRepository.accessToken = accessToken
                        response.request.newBuilder().apply {
                            removeHeader("Authorization")
                            addHeader("Authorization", "Bearer $accessToken")
                        }.build()
                    }.onFailure { exception ->
                        Timber.e(exception)
                    }.getOrNull()
                }

                if (
                    accessToken == null &&
                    refreshToken != null
                ) {
                    authenticationRepository.accessToken = ""
                    authenticationRepository.refreshToken = ""
                    goToLoginScreen = true
                }

                return@runBlocking accessToken
            }
        }

        if (goToLoginScreen) {
            // TODO: InvalidTokenActivity, Repository observe from Application?
        }

        return request
    }
}
