package com.ileitelabs.home.data.datasource.remote.dto

import com.google.gson.annotations.SerializedName

data class RepoTrendsResponseDto(
    @SerializedName("items")
    val repositories: List<RepositoryResponseDto>?,
)