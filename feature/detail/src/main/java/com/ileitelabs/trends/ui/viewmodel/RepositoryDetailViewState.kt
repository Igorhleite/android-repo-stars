package com.ileitelabs.trends.ui.viewmodel

import com.ileitelabs.trends.domain.model.RepositoryDetail

internal data class RepositoryDetailViewState(
    val data: RepositoryDetail? = null,
    val state: RepositoryDetailState = RepositoryDetailState.LOADING
)

enum class RepositoryDetailState(val child: Int) {
    LOADING(child = 0),
    SUCCESS(child = 1),
    ERROR(child = 2)
}