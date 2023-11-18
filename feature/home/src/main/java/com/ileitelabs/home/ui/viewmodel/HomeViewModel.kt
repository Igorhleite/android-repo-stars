package com.ileitelabs.home.ui.viewmodel

import android.util.Log
import androidx.lifecycle.viewModelScope
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.ileitelabs.core.ui.viewmodel.RepoTrendsViewModel
import com.ileitelabs.home.domain.model.Repository
import com.ileitelabs.home.domain.usecase.GetTrendingUseCase
import com.ileitelabs.navigation.deeplink.RepoTrendsDeepLink
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
    private val dispatcher: CoroutineDispatcher,
    private val deepLinkBuilder: RepoTrendsDeepLink
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
        onState { state ->
            state.copy(
                unexpectedError = true
            )
        }
        Log.e("HomeViewModel", "Unexpected error on handleOnError: ${error?.message}")
    }

    private fun handleOnSuccess(repoTrendingList: PagingData<Repository>) {
        onState {
            it.copy(
                data = repoTrendingList
            )
        }
    }

    private fun handleOnLoading(isLoading: Boolean) {
        onState {
            it.copy(
                hasLoading = isLoading
            )
        }
    }

    fun onRepositoryClicked(repository: Repository) {
        val uri = deepLinkBuilder.deepLinkToRepoDetail(
            repositoryName = repository.owner.name.orEmpty(),
            ownerName = repository.name.orEmpty()
        )

        onAction { HomeViewAction.NavigateToDetail(uri) }
    }

    fun onTryAgainClicked() {
        onAction { HomeViewAction.FetchData }
    }

    fun manageAdapterLoadStates(loadState: CombinedLoadStates, isAdapterEmpty: Boolean) {
        with(loadState) {
            val hasLoading = refresh is LoadState.Loading
            val hasError = refresh is LoadState.Error
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
}