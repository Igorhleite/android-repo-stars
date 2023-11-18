package com.ileitelabs.home.data.datasource.remote.impl

import com.ileitelabs.home.data.datasource.remote.RemoteRepoTrendsDataSource
import com.ileitelabs.home.data.datasource.remote.dto.RepoTrendsResponseDto
import com.ileitelabs.home.data.service.RepoTrendsService
import javax.inject.Inject

class RemoteRepoTrendsDataSourceImpl @Inject constructor(
    private val hearthStoneApi: RepoTrendsService
) : RemoteRepoTrendsDataSource {
    override suspend fun fetchRepoTrends(page: Int): RepoTrendsResponseDto =
        hearthStoneApi.getRepositories(page = page)
}