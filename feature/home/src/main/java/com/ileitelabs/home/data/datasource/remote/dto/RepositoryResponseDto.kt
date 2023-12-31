package com.ileitelabs.home.data.datasource.remote.dto

import com.google.gson.annotations.SerializedName

data class RepositoryResponseDto(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("html_url")
    val repoUrl: String?,
    @SerializedName("stargazers_count")
    val stars: Int?,
    @SerializedName("forks_count")
    val forks: Int?,
    @SerializedName("owner")
    val owner: OwnerResponseDto,
)