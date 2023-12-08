package com.ileitelabs.home.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.ileitelabs.core.ui.viewmodel.RepoTrendsViewModel
import com.ileitelabs.home.domain.model.Repository
import com.ileitelabs.home.domain.usecase.GetTrendingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class HomeViewModel @Inject constructor(
    private val useCase: GetTrendingUseCase,
    private val dispatcher: CoroutineDispatcher
) : RepoTrendsViewModel<HomeViewState, HomeViewAction>(HomeViewState()) {

    init {
        obtainRepositories()
    }

    fun obtainRepositories() {
        viewModelScope.launch {
            useCase()
                .cachedIn(viewModelScope)
                .flowOn(dispatcher)
                .collect(::handleOnSuccess)
        }
    }

    private fun handleOnSuccess(repoTrendingList: PagingData<Repository>) {
        onState {
            it.copy(
                data = repoTrendingList,
                hasLoading = false
            )
        }
    }

    fun onRepositoryClicked(repository: Repository) {
        onAction {
            HomeViewAction.NavigateToDetail(
                repositoryName = repository.owner.name.orEmpty(),
                ownerName = repository.name.orEmpty()
            )
        }
    }

    fun onTryAgainClicked() {
        onAction { HomeViewAction.FetchData }
    }

    fun manageAdapterLoadStates(loadState: LoadState, isAdapterEmpty: Boolean) {

        val hasLoading = loadState is LoadState.Loading
        val hasError = loadState is LoadState.Error
        val hasErrorWithoutCache = hasError && isAdapterEmpty
        val hasErrorWithCache = hasError && !isAdapterEmpty

        onState {
            it.copy(
                emptyDataError = hasErrorWithoutCache,
                refreshDataError = hasErrorWithCache,
                hasLoading = hasLoading
            )
        }
    }
}