package com.ileitelabs.trends.di

import com.ileitelabs.trends.data.repository.RepoDetailRepositoryImpl
import com.ileitelabs.trends.domain.repository.RepoDetailRepository
import com.ileitelabs.trends.domain.usecase.GetRepositoryDetail
import com.ileitelabs.trends.domain.usecase.GetRepositoryDetailImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface UseCaseModule {

    @Binds
    fun bindGetDetailUseCase(
        useCase: GetRepositoryDetailImpl
    ): GetRepositoryDetail
}