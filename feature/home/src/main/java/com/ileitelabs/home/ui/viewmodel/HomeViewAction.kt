package com.ileitelabs.home.ui.viewmodel

internal sealed class HomeViewAction {
    data class NavigateToDetail(val name: String) : HomeViewAction()
    object FetchData : HomeViewAction()
}