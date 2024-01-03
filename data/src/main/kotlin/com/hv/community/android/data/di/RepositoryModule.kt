package com.hv.community.android.data.di

import com.hv.community.android.data.remote.local.SharedPreferencesManager
import com.hv.community.android.data.remote.network.api.AuthenticationApi
import com.hv.community.android.data.remote.network.api.CommunityApi
import com.hv.community.android.data.remote.network.api.SignUpApi
import com.hv.community.android.data.remote.network.api.UserApi
import com.hv.community.android.data.repository.authentication.RealAuthenticationRepository
import com.hv.community.android.data.repository.community.RealCommunityRepository
import com.hv.community.android.data.repository.user.RealSignUpRepository
import com.hv.community.android.data.repository.user.RealUserRepository
import com.hv.community.android.domain.repository.AuthenticationRepository
import com.hv.community.android.domain.repository.CommunityRepository
import com.hv.community.android.domain.repository.SignUpRepository
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
    fun bindsSignUpRepository(
        signUpApi: SignUpApi,
        sharedPreferencesManager: SharedPreferencesManager
    ): SignUpRepository {
//        return MockSignUpRepository(sharedPreferencesManager)
        return RealSignUpRepository(signUpApi, sharedPreferencesManager)
    }

    @Provides
    @Singleton
    fun bindsUserRepository(
        userApi: UserApi
    ): UserRepository {
//        return MockUserRepository()
        return RealUserRepository(userApi)
    }

    @Provides
    @Singleton
    fun bindsAuthenticationRepository(
        authenticationApi: AuthenticationApi,
        sharedPreferencesManager: SharedPreferencesManager
    ): AuthenticationRepository {
//        return MockAuthenticationRepository(sharedPreferencesManager)
        return RealAuthenticationRepository(authenticationApi, sharedPreferencesManager)
    }

    @Provides
    @Singleton
    fun bindsCommunityRepository(
        communityApi: CommunityApi
    ): CommunityRepository {
//        return MockCommunityRepository()
        return RealCommunityRepository(communityApi)
    }
}
