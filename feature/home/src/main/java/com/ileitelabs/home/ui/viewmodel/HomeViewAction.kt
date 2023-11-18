package com.ileitelabs.home.ui.viewmodel

import android.net.Uri

internal sealed class HomeViewAction {
    data class NavigateToDetail(val uri: Uri) : HomeViewAction()
    object FetchData : HomeViewAction()
}