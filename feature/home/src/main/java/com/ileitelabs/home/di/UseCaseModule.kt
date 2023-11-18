package com.ileitelabs.home.di

import com.ileitelabs.home.domain.usecase.GetTrendingUseCase
import com.ileitelabs.home.domain.usecase.impl.GetTrendingUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface UseCaseModule {

    @Binds
    fun bindGetTrendingUseCase(
        useCase: GetTrendingUseCaseImpl
    ): GetTrendingUseCase
}