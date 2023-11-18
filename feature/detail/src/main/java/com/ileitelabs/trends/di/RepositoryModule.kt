package com.ileitelabs.trends.di

import com.ileitelabs.trends.data.repository.RepoDetailRepositoryImpl
import com.ileitelabs.trends.domain.repository.RepoDetailRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindDetailRepository(
        repository: RepoDetailRepositoryImpl
    ): RepoDetailRepository
}