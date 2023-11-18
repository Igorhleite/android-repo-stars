package com.ileitelabs.trends.domain.usecase

import com.ileitelabs.trends.domain.model.RepositoryDetail
import kotlinx.coroutines.flow.Flow

interface GetRepositoryDetail {
    suspend operator fun invoke(owner: String, repo: String): Flow<RepositoryDetail>
}