package com.ileitelabs.home.data.service

import com.ileitelabs.home.data.datasource.remote.dto.RepoTrendsResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface HomeService {
    @GET("search/repositories")
    suspend fun getRepositories(
        @Query("q") q: String = "language:kotlin",
        @Query("sort") sort: String = "stars",
        @Query(value = "page") page: Int,
    ): RepoTrendsResponseDto
}