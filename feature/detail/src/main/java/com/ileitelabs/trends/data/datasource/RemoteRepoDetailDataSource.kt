package com.ileitelabs.trends.data.datasource

import com.ileitelabs.trends.data.datasource.dto.RepositoryDetailsResponseDto
import kotlinx.coroutines.flow.Flow


interface RemoteRepoDetailDataSource {
    suspend fun fetchRepoDetails(owner: String, repo: String): Flow<RepositoryDetailsResponseDto>
}