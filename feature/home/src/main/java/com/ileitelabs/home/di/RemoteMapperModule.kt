package com.ileitelabs.home.di

import com.ileitelabs.home.data.mapper.RepositoriesMapper
import com.ileitelabs.home.data.mapper.RepositoriesMapperImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RemoteMapperModule {

    @Binds
    fun bindRepoTrendsResponseMapper(
        mapper: RepositoriesMapperImpl
    ): RepositoriesMapper
}