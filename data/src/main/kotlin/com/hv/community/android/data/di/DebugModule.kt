package com.hv.community.android.data.di

import dagger.BindsOptionalOf
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import javax.inject.Qualifier

@Module
@InstallIn(SingletonComponent::class)
abstract class DebugModule {

    @BindsOptionalOf
    @DebugInterceptor
    abstract fun bindsDebugInterceptor(): Interceptor
}

@Qualifier
annotation class DebugInterceptor
