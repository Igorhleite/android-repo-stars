package com.ileitelabs.core.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class RepoTrendsViewModel<S, A>(
    initialState: S
) : ViewModel() {

    private val _state = MutableLiveData(initialState)
    val state = _state

    private val _action = MutableLiveData<A?>()
    val action = _action

    protected fun onState(updateViewState: (S) -> S) {
        _state.value = updateViewState(_state.value!!)
    }

    protected fun onAction(doAction: () -> A) {
        _action.value = doAction()
        _action.value = null
    }
}
