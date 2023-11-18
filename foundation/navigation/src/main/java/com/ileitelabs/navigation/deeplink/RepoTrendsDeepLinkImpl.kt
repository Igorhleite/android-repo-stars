package com.ileitelabs.navigation.deeplink

import android.net.Uri
import androidx.core.net.toUri
import javax.inject.Inject


class RepoTrendsDeepLinkImpl @Inject constructor() : RepoTrendsDeepLink {
    override fun deepLinkToRepoDetail(repositoryName: String, ownerName: String): Uri {
        return "repoTrends://detail/$repositoryName/$ownerName".toUri()
    }
}