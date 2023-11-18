package com.ileitelabs.home.ui.viewmodel

import com.ileitelabs.home.domain.model.Repository

internal sealed class HomeViewAction {
    data class NavigateToDetail(val repository: Repository) : HomeViewAction()
    object FetchData : HomeViewAction()
}