package com.ileitelabs.trends.domain.model

data class RepositoryDetail(
    val name: String,
    val fullName: String,
    val owner: RepositoryDetailOwner,
    val htmlUrl: String,
    val description: String?,
    val stars: String?
)
