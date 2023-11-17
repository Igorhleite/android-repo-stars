package com.ileitelabs.core.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface RepoTrendsService {
    @GET("repositories")
    suspend fun getRepositories(
        @Query("q") q: String = "language:kotlin",
        @Query("sort") sort: String = "stars",
        @Query(value = "page") page: Int,
    ): String
}