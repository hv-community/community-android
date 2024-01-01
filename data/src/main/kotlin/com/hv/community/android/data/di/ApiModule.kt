package com.hv.community.android.data.di

import com.hv.community.android.data.remote.network.api.AuthenticationApi
import com.hv.community.android.data.remote.network.api.UserApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object ApiModule {
    @Provides
    @Singleton
    fun provideUserApi(
        retrofit: Retrofit
    ): UserApi {
        return retrofit.create(UserApi::class.java)
    }

    @Provides
    @Singleton
    fun provideAuthenticationApi(
        retrofit: Retrofit
    ): AuthenticationApi {
        return retrofit.create(AuthenticationApi::class.java)
    }
}
