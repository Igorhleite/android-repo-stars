package com.ileitelabs.trends.presentation.viewmodel

import com.ileitelabs.trends.domain.model.RepositoryDetail

internal data class RepositoryDetailViewState(
    val data: RepositoryDetail = RepositoryDetail(),
    val state: RepositoryDetailState = RepositoryDetailState.LOADING
) {
    enum class RepositoryDetailState {
        LOADING,
        SUCCESS,
        ERROR
    }
}

