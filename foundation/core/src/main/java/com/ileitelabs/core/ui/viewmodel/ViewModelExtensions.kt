package com.ileitelabs.core.ui.viewmodel

import androidx.fragment.app.Fragment

inline fun <reified S, reified A> Fragment.onViewState(
    viewModel: RepoTrendsViewModel<S, A>,
    crossinline state: (S) -> Unit
) {
    viewModel.state.observe(viewLifecycleOwner) { state(it) }
}

inline fun <reified S, reified A> Fragment.onViewAction(
    viewModel: RepoTrendsViewModel<S, A>,
    crossinline action: (A?) -> Unit
) {
    viewModel.action.observe(viewLifecycleOwner) {
        action(it)
    }
}
