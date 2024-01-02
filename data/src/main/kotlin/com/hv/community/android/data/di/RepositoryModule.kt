package com.hv.community.android.data.di

import com.hv.community.android.data.remote.local.SharedPreferencesManager
import com.hv.community.android.data.remote.network.api.AuthenticationApi
import com.hv.community.android.data.remote.network.api.CommunityApi
import com.hv.community.android.data.remote.network.api.UserApi
import com.hv.community.android.data.repository.authentication.MockAuthenticationRepository
import com.hv.community.android.data.repository.community.MockCommunityRepository
import com.hv.community.android.data.repository.user.MockUserRepository
import com.hv.community.android.domain.repository.AuthenticationRepository
import com.hv.community.android.domain.repository.CommunityRepository
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
    fun bindsUserRepository(
        userApi: UserApi,
        sharedPreferencesManager: SharedPreferencesManager
    ): UserRepository {
        // return RealSampleRepository(userApi, sharedPreferencesManager)
        return MockUserRepository(sharedPreferencesManager)
    }

    @Provides
    @Singleton
    fun bindsAuthenticationRepository(
        authenticationApi: AuthenticationApi,
        sharedPreferencesManager: SharedPreferencesManager
    ): AuthenticationRepository {
        // return RealAuthenticationRepository(authenticationApi, sharedPreferencesManager)
        return MockAuthenticationRepository(sharedPreferencesManager)
    }

    @Provides
    @Singleton
    fun bindsCommunityRepository(
        communityApi: CommunityApi
    ): CommunityRepository {
        // return RealCommunityRepository(communityApi)
        return MockCommunityRepository()
    }
}
