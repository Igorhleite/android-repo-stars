package com.ileitelabs.trends.domain.repository

import com.ileitelabs.trends.domain.model.RepositoryDetail
import kotlinx.coroutines.flow.Flow

interface RepoDetailRepository {
    suspend fun fetchRepoDetail(owner: String, repo: String): Flow<RepositoryDetail>
}