package com.ileitelabs.home.ui

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.fragment.findNavController
import androidx.paging.PagingData
import androidx.recyclerview.widget.LinearLayoutManager
import com.ileitelabs.core.ui.viewmodel.onViewAction
import com.ileitelabs.core.ui.viewmodel.onViewState
import com.ileitelabs.home.domain.model.Repository
import com.ileitelabs.home.ui.adapters.RepositoriesAdapter
import com.ileitelabs.home.ui.viewmodel.HomeViewAction
import com.ileitelabs.home.ui.viewmodel.HomeViewModel
import com.ileitelabs.repotrends.feature.home.databinding.HomeFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: HomeFragmentBinding? = null
    private val binding: HomeFragmentBinding get() = _binding!!

    private val viewModel: HomeViewModel by viewModels()

    private val navController by lazy { findNavController() }

    private val repositoriesAdapter by lazy {
        RepositoriesAdapter { repository, _ ->
            viewModel.onRepositoryClicked(repository)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ) = HomeFragmentBinding.inflate(
        inflater, container, false
    ).apply {
        _binding = this
    }.root

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler()
        setTryAgainButtonAction()
        addLoadStateAdapter()
        addObservables()
    }

    private fun initRecycler() {
        binding.rcRepositories.apply {
            adapter = repositoriesAdapter
            layoutManager = LinearLayoutManager(activity)
            isVerticalFadingEdgeEnabled = true
            setFadingEdgeLength(150)
        }
    }

    private fun addObservables() {
        onViewState(viewModel) { state ->
            manageRepositoryList(state.data)
            manageLoading(state.hasLoading)
            manageError(state.emptyDataError, state.refreshDataError)
        }

        onViewAction(viewModel) { action ->
            when (action) {
                is HomeViewAction.FetchData -> viewModel.obtainRepositories()
                is HomeViewAction.NavigateToDetail -> navigateToDetail(action.uri)
                else -> {}
            }
        }
    }

    private fun navigateToDetail(uri: Uri) {
        val request = NavDeepLinkRequest.Builder.fromUri(uri).build()
        navController.navigate(request)
    }

    private fun manageError(emptyDataError: Boolean, refreshDataError: Boolean) {
        binding.screenError.isVisible = emptyDataError
        if (refreshDataError) {
            Toast.makeText(activity, "Erro ao atualizar os dados", Toast.LENGTH_SHORT).show()
        }
    }

    private fun manageLoading(hasLoading: Boolean) {
        binding.pbProgress.isVisible = hasLoading
    }

    private fun manageRepositoryList(repositories: PagingData<Repository>?) {
        repositories?.let {
            repositoriesAdapter.submitData(lifecycle, it)
        }
    }

    private fun setTryAgainButtonAction() {
        binding.screenError.onClickTryAgainButton {
            viewModel.onTryAgainClicked()
        }
    }

    private fun addLoadStateAdapter() {
        repositoriesAdapter.addLoadStateListener {
            viewModel.manageAdapterLoadStates(
                loadState = it.refresh,
                isAdapterEmpty = repositoriesAdapter.itemCount == 0
            )
        }
    }
}