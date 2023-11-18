package com.ileitelabs.home.ui.viewmodel

import androidx.paging.PagingData
import com.ileitelabs.home.domain.model.Repository

internal data class HomeViewState(
    val data: PagingData<Repository>? = null
)