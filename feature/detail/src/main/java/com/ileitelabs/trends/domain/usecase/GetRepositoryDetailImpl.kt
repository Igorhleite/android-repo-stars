package com.ileitelabs.trends.domain.usecase

import com.ileitelabs.trends.domain.model.RepositoryDetail
import com.ileitelabs.trends.domain.repository.RepoDetailRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRepositoryDetailImpl @Inject constructor(
    private val repository: RepoDetailRepository
) : GetRepositoryDetail {
    override suspend fun invoke(owner: String, repo: String): Flow<RepositoryDetail> {
        return repository.fetchRepoDetail(
            owner = owner,
            repo = repo
        )
    }
}