package com.ileitelabs.home.presentation.viewmodel

import androidx.paging.PagingData
import com.ileitelabs.home.domain.model.Repository
import kotlinx.coroutines.flow.Flow

internal data class HomeViewState(
    val data: Flow<PagingData<Repository>>? = null,
    val emptyDataError: Boolean = false,
    val refreshDataError: Boolean = false,
    val unexpectedError: Boolean = false,
    val hasLoading: Boolean = false
)