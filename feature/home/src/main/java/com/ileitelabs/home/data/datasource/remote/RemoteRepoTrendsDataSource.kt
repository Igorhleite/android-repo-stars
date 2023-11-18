package com.ileitelabs.home.data.datasource.remote

import com.ileitelabs.home.data.datasource.remote.dto.RepoTrendsResponseDto

interface RemoteRepoTrendsDataSource {
    suspend fun fetchRepoTrends(page: Int): RepoTrendsResponseDto
}