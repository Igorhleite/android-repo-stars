package com.ileitelabs.home.domain.repository

import androidx.paging.PagingData
import com.ileitelabs.home.domain.model.Repository
import kotlinx.coroutines.flow.Flow

interface RepoTrendsRepository {
    suspend fun fetchCachedRepositories(): Flow<PagingData<Repository>>
}