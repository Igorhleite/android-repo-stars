package com.ileitelabs.trends.data.datasource.dto

import com.google.gson.annotations.SerializedName

data class RepositoryDetailsResponseDto(
    @SerializedName("name")
    val name: String?,
    @SerializedName("full_name")
    val fullName: String?,
    @SerializedName("owner")
    val owner: Owner?,
    @SerializedName("html_url")
    val htmlUrl: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("stargazers_count")
    val stars: Int?,
    @SerializedName("watchers_count")
    val watchers: Int?,
    @SerializedName("open_issues_count")
    val issues: Int?
) {
    data class Owner(
        @SerializedName("login")
        val login: String?,
        @SerializedName("avatar_url")
        val avatarUrl: String?,
        @SerializedName("html_url")
        val htmlUrl: String?
    )
}