package com.ileitelabs.home.domain.usecase.impl

import androidx.paging.PagingData
import com.ileitelabs.home.domain.model.Repository
import com.ileitelabs.home.domain.repository.RepoTrendsRepository
import com.ileitelabs.home.domain.usecase.GetTrendingUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTrendingUseCaseImpl @Inject constructor(
    private val repository: RepoTrendsRepository
) : GetTrendingUseCase {
    override suspend fun invoke(): Flow<PagingData<Repository>> {
        return repository.fetchCachedRepositories()
    }
}