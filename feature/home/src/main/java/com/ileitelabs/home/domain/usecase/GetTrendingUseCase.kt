package com.ileitelabs.home.domain.usecase

import androidx.paging.PagingData
import com.ileitelabs.home.domain.model.Repository
import kotlinx.coroutines.flow.Flow

interface GetTrendingUseCase {
    suspend operator fun invoke(): Flow<PagingData<Repository>>
}