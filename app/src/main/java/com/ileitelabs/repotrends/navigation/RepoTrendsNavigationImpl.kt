package com.ileitelabs.repotrends.navigation

import androidx.core.net.toUri
import androidx.navigation.NavController
import com.ileitelabs.navigation.RepoTrendsNavigation
import javax.inject.Inject


class RepoTrendsNavigationImpl @Inject constructor() : RepoTrendsNavigation {
    override fun navigateToRepositoryDetail(
        navController: NavController,
        repositoryName: String,
        ownerName: String
    ) {
        val deepLinkUri = "repoTrends://detail/$repositoryName/$ownerName".toUri()
        navController.navigate(deepLinkUri)
    }
}