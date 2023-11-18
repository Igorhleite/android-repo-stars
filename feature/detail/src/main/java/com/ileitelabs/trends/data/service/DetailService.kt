package com.ileitelabs.trends.data.service

import com.ileitelabs.trends.data.datasource.dto.RepositoryDetailsResponseDto
import retrofit2.http.GET
import retrofit2.http.Path

interface DetailService {
    @GET("repos/{owner}/{repo}")
    suspend fun getRepositoryDetails(
        @Path("owner") owner: String,
        @Path("repo") repo: String
    ): RepositoryDetailsResponseDto
}