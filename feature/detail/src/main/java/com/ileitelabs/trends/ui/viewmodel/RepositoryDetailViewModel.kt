package com.ileitelabs.trends.ui.viewmodel

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.ileitelabs.core.ui.viewmodel.RepoTrendsViewModel
import com.ileitelabs.trends.domain.model.RepositoryDetail
import com.ileitelabs.trends.domain.usecase.GetRepositoryDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class RepositoryDetailViewModel @Inject constructor(
    private val dispatcher: CoroutineDispatcher, private val useCase: GetRepositoryDetail
) : RepoTrendsViewModel<RepositoryDetailViewState, RepositoryDetailViewAction>(
    RepositoryDetailViewState()
) {

    fun obtainRepositoryDetail(name: String, owner: String) {
        viewModelScope.launch {
            useCase(name, owner)
                .flowOn(dispatcher)
                .onStart { handleOnLoading(true) }
                .onCompletion { handleOnLoading(false) }
                .catch { handleOnError(it) }
                .collect(::handleOnSuccess)
        }
    }

    private fun handleOnError(error: Throwable?) {
        onState {
            it.copy(
                state = RepositoryDetailState.ERROR
            )
        }
        Log.e("RepositoryDetailViewModel", "Error on handleOnError: ${error?.message}")
    }

    private fun handleOnSuccess(repoTrendingList: RepositoryDetail) {
        onState {
            it.copy(
                data = repoTrendingList,
                state = RepositoryDetailState.SUCCESS
            )
        }
    }

    private fun handleOnLoading(isLoading: Boolean) {
        if (isLoading) {
            onState {
                it.copy(
                    state = RepositoryDetailState.LOADING
                )
            }
        }
    }

    fun onTryAgainClicked() {
        onAction { RepositoryDetailViewAction.FetchData }
    }
}