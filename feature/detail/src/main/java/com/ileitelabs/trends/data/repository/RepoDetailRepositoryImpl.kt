package com.ileitelabs.trends.data.repository

import com.ileitelabs.trends.data.datasource.RemoteRepoDetailDataSource
import com.ileitelabs.trends.data.mapper.RepoDetailMapper
import com.ileitelabs.trends.domain.model.RepositoryDetail
import com.ileitelabs.trends.domain.repository.RepoDetailRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RepoDetailRepositoryImpl @Inject constructor(
    private val dataSource: RemoteRepoDetailDataSource,
    private val mapper: RepoDetailMapper
) : RepoDetailRepository {
    override suspend fun fetchRepoDetail(owner: String, repo: String): Flow<RepositoryDetail> {
        return dataSource.fetchRepoDetails(
            owner = owner,
            repo = repo
        ).map { mapper.fromDtoToModel(it) }
    }
}