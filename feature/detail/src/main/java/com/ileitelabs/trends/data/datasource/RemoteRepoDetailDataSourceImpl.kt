package com.ileitelabs.trends.data.datasource

import com.ileitelabs.trends.data.datasource.dto.RepositoryDetailsResponseDto
import com.ileitelabs.trends.data.service.DetailService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RemoteRepoDetailDataSourceImpl @Inject constructor(
    private val detailService: DetailService
) : RemoteRepoDetailDataSource {
    override suspend fun fetchRepoDetails(owner: String, repo: String): Flow<RepositoryDetailsResponseDto> =
        flow {
            emit(detailService.getRepositoryDetails(owner, repo))
        }
}