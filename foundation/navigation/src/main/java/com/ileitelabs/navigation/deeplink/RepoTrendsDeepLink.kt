package com.ileitelabs.navigation.deeplink

import android.net.Uri

interface RepoTrendsDeepLink {
    fun deepLinkToRepoDetail(repositoryName: String, ownerName: String) : Uri
}