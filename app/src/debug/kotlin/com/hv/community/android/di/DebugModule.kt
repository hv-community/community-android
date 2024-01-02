package com.hv.community.android.di

import android.content.Context
import com.facebook.flipper.android.AndroidFlipperClient
import com.facebook.flipper.plugins.network.FlipperOkhttpInterceptor
import com.facebook.flipper.plugins.network.NetworkFlipperPlugin
import com.hv.community.android.data.di.DebugInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DebugModule {

    @Provides
    @DebugInterceptor
    @Singleton
    fun provideDebugInterceptor(
        @ApplicationContext context: Context
    ): Interceptor {
        return FlipperOkhttpInterceptor(
            AndroidFlipperClient.getInstance(context).getPlugin(NetworkFlipperPlugin.ID)
        )
    }
}
