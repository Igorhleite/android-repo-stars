package com.ileitelabs.trends.presentation.viewmodel

internal sealed class RepositoryDetailViewAction {
    object FetchData : RepositoryDetailViewAction()
    data class GoToExternalUrl(val url: String) : RepositoryDetailViewAction()
}