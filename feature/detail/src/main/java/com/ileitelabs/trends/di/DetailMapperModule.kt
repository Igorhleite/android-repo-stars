package com.ileitelabs.trends.di

import com.ileitelabs.trends.data.datasource.RemoteRepoDetailDataSource
import com.ileitelabs.trends.data.datasource.RemoteRepoDetailDataSourceImpl
import com.ileitelabs.trends.data.mapper.RepoDetailMapper
import com.ileitelabs.trends.data.mapper.RepoDetailMapperImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DetailMapperModule {

    @Binds
    fun bindRepoDetailMapper(
        mapper: RepoDetailMapperImpl
    ): RepoDetailMapper
}