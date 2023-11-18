package com.ileitelabs.core.ui.viewmodel

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.launch

inline fun <reified S, reified A> LifecycleOwner.onViewState(
    viewModel: RepoTrendsViewModel<S, A>,
    crossinline state: (S) -> Unit
) {
    lifecycleScope.launch{
        repeatOnLifecycle(Lifecycle.State.STARTED) {
            viewModel.state.observe(this@onViewState) { state(it) }
        }
    }}

inline fun <reified S, reified A> LifecycleOwner.onViewAction(
    viewModel: RepoTrendsViewModel<S, A>,
    crossinline action: (A) -> Unit
) {
    lifecycleScope.launch{
        repeatOnLifecycle(Lifecycle.State.STARTED) {
            viewModel.action.observe(this@onViewAction) { action(it) }
        }
    }
}
