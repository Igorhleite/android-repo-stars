package com.ileitelabs.repotrends.navigation.di

import com.ileitelabs.navigation.RepoTrendsNavigation
import com.ileitelabs.repotrends.navigation.RepoTrendsNavigationImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
interface RepoTrendsNavModule {
    @Binds
    fun bindDeepLinkBuilder(impl: RepoTrendsNavigationImpl): RepoTrendsNavigation
}