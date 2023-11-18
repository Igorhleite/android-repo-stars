package com.ileitelabs.home.ui.viewmodel

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.ileitelabs.core.ui.viewmodel.RepoTrendsViewModel
import com.ileitelabs.home.data.datasource.local.entity.RepositoryEntity
import com.ileitelabs.home.domain.model.Repository
import com.ileitelabs.home.domain.usecase.GetTrendingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class HomeViewModel @Inject constructor(
    private val useCase: GetTrendingUseCase,
    private val dispatcher: CoroutineDispatcher
) : RepoTrendsViewModel<HomeViewState, HomeViewAction>(HomeViewState()) {

    init {
        obtainSets()
    }

    fun obtainSets() {
        viewModelScope.launch {
            useCase()
                .cachedIn(viewModelScope)
                .flowOn(dispatcher)
                .onStart { handleOnLoading(true) }
                .onCompletion { handleOnLoading(false) }
                .catch { handleOnError(it) }
                .collect(::handleOnSuccess)
        }
    }

    private fun handleOnError(error: Throwable?) {
        val a = 1
    }

    private fun handleOnSuccess(repoTrendingList: PagingData<Repository>) {
        onState {
            it.copy(
                data = repoTrendingList
            )
        }
    }

    private fun handleOnLoading(isLoading: Boolean) {
        val a = 1

    }

    fun onTryAgainClicked() {
        onAction { HomeViewAction.FetchData }
    }
}