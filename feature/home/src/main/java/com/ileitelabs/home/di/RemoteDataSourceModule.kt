package com.ileitelabs.home.di

import com.ileitelabs.home.data.datasource.remote.RemoteRepoTrendsDataSource
import com.ileitelabs.home.data.datasource.remote.impl.RemoteRepoTrendsDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RemoteDataSourceModule {

    @Binds
    fun bindRemoteRepoTrendsDataSource(
        dataSource: RemoteRepoTrendsDataSourceImpl
    ): RemoteRepoTrendsDataSource
}