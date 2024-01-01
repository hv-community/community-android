package com.hv.community.android.data.di

import com.hv.community.android.data.remote.local.SharedPreferencesManager
import com.hv.community.android.data.remote.network.api.UserApi
import com.hv.community.android.data.repository.MockUserRepository
import com.hv.community.android.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object RepositoryModule {
    @Provides
    @Singleton
    fun bindsSampleRepository(
        userApi: UserApi,
        sharedPreferencesManager: SharedPreferencesManager
    ): UserRepository {
        // return RealSampleRepository(userApi, sharedPreferencesManager)
        return MockUserRepository(sharedPreferencesManager)
    }
}
