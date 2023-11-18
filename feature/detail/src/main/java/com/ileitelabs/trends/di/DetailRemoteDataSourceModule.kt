package com.ileitelabs.trends.di

import com.ileitelabs.trends.data.datasource.RemoteRepoDetailDataSource
import com.ileitelabs.trends.data.datasource.RemoteRepoDetailDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DetailRemoteDataSourceModule {

    @Binds
    fun bindRemoteRepoDetailDataSource(
        dataSource: RemoteRepoDetailDataSourceImpl
    ): RemoteRepoDetailDataSource
}