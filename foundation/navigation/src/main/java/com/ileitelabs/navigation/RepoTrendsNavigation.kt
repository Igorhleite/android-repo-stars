package com.ileitelabs.navigation

import androidx.navigation.NavController

interface RepoTrendsNavigation {
    fun navigateToRepositoryDetail(
        navController: NavController,
        repositoryName: String,
        ownerName: String
    )
}