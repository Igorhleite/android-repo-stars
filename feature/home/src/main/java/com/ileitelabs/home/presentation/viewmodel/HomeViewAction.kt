package com.ileitelabs.home.presentation.viewmodel

internal sealed class HomeViewAction {
    data class NavigateToDetail(val repositoryName: String, val ownerName: String) :
        HomeViewAction()

    object FetchData : HomeViewAction()
}