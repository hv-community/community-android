package com.hv.community.android.data.di

import android.content.Context
import com.hv.community.android.data.remote.local.ErrorMessageMapper
import com.hv.community.android.data.remote.local.SharedPreferencesManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object LocalModule {

    // TODO : Local 과 Network 분리
    @Provides
    @Singleton
    fun provideSharedPreferencesManager(
        @ApplicationContext context: Context
    ): SharedPreferencesManager {
        return SharedPreferencesManager(context)
    }

    @Provides
    @Singleton
    fun provideErrorMessageMapper(
        @ApplicationContext context: Context
    ): ErrorMessageMapper {
        return ErrorMessageMapper(context)
    }
}
