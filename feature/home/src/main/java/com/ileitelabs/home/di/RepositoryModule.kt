package com.ileitelabs.home.di

import com.ileitelabs.home.data.repository.RepoTrendsRepositoryImpl
import com.ileitelabs.home.domain.repository.RepoTrendsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindRepoTrendsRepository(
        repository: RepoTrendsRepositoryImpl
    ): RepoTrendsRepository
}