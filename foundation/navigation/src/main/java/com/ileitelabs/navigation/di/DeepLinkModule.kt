package com.ileitelabs.navigation.di

import com.ileitelabs.navigation.deeplink.RepoTrendsDeepLink
import com.ileitelabs.navigation.deeplink.RepoTrendsDeepLinkImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DeepLinkModule {

    @Provides
    fun bindDeepLinkBuilder(): RepoTrendsDeepLink {
        return RepoTrendsDeepLinkImpl()
    }
}