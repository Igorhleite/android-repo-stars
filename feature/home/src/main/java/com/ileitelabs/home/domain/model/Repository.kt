package com.ileitelabs.home.domain.model

data class Repository(
    val id: Int?,
    val name: String?,
    val repoUrl: String?,
    val stars: Int?,
    val forks: Int?,
    val owner: Owner,
)