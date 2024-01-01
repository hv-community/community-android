package com.hv.community.android.data.di

import com.hv.community.android.data.remote.network.api.SampleApi
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
    fun provideSampleApi(
        retrofit: Retrofit
    ): SampleApi {
        return retrofit.create(SampleApi::class.java)
    }
}