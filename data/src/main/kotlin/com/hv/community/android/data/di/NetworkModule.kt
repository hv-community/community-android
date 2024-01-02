package com.hv.community.android.data.di

import com.hv.community.android.data.remote.network.environment.TokenAuthenticator
import com.hv.community.android.data.remote.network.environment.TokenInterceptor
import com.hv.community.android.domain.repository.AuthenticationRepository
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.util.Optional
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    private val contentType = "application/json".toMediaType()

    private val json = Json { ignoreUnknownKeys = true }

    @Provides
    @Singleton
    fun providesTokenAuthenticator(
        authenticationRepository: AuthenticationRepository
    ): TokenAuthenticator {
        return TokenAuthenticator(authenticationRepository)
    }

    @Provides
    @Singleton
    fun providesTokenInterceptor(
        authenticationRepository: AuthenticationRepository
    ): TokenInterceptor {
        return TokenInterceptor(authenticationRepository)
    }

    @NoAuthRetrofit
    @Provides
    @Singleton
    fun provideNoAuthRetrofit(
        @DebugInterceptor debugClient: Optional<Interceptor>,
    ): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(json.asConverterFactory(contentType))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .baseUrl(SERVER_URL).apply {
                client(
                    OkHttpClient.Builder()
                        .apply {
                            if (debugClient.isPresent) {
                                addNetworkInterceptor(debugClient.get())
                            }
                        }.build()
                )
            }.build()
    }

    @AuthRetrofit
    @Provides
    @Singleton
    fun provideAuthRetrofit(
        @DebugInterceptor debugClient: Optional<Interceptor>,
        tokenAuthenticator: TokenAuthenticator,
        tokenInterceptor: TokenInterceptor
    ): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(json.asConverterFactory(contentType))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .baseUrl(SERVER_URL).apply {
                client(
                    OkHttpClient.Builder()
                        .authenticator(tokenAuthenticator)
                        .addInterceptor(tokenInterceptor)
                        .apply {
                            if (debugClient.isPresent) {
                                addNetworkInterceptor(debugClient.get())
                            }
                        }.build()
                )
            }.build()
    }

    companion object {
        private const val SERVER_URL = "https://hvcommunity.duckdns.org"
    }
}

@Qualifier
annotation class NoAuthRetrofit

@Qualifier
annotation class AuthRetrofit
