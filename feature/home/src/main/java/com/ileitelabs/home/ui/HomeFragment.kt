package com.ileitelabs.home.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.recyclerview.widget.LinearLayoutManager
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

    private val repositoriesAdapter by lazy {
        RepositoriesAdapter { repository, _ ->
            //setOpenRepoUrlInBrowser(repoUrl = repository.repoUrl)
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
        initView()
    }

    private fun initRecycler() {
        binding.rcRepositories.apply {
            adapter = repositoriesAdapter
            layoutManager = LinearLayoutManager(activity)
            isVerticalFadingEdgeEnabled = true
            setFadingEdgeLength(150)
        }
    }

    private fun initView() {
        viewModel.state.observe(viewLifecycleOwner) {
            manageRepositoryList(it.data)
        }
        viewModel.action.observe(viewLifecycleOwner) {
            when (it) {
                HomeViewAction.FetchData -> viewModel.obtainSets()
                else -> {}
            }
        }
    }

    private fun manageRepositoryList(repositories: PagingData<Repository>?) {
        repositories?.let {
            repositoriesAdapter.submitData(lifecycle, it)
            addLoadStateAdapter()
        }
    }

    private fun setTryAgainButtonAction() {
        binding.screenError.btErrorRefresh.setOnClickListener {
            viewModel.onTryAgainClicked()
        }
    }

    private fun addLoadStateAdapter() {
        repositoriesAdapter.addLoadStateListener {
            it.loadStatesRules()
        }
    }

    private fun CombinedLoadStates.loadStatesRules() {
        binding.apply {
            val isLoading = refresh is LoadState.Loading
            val isError = refresh is LoadState.Error

            screenError.root.isVisible =
                (isError) && repositoriesAdapter.itemCount == 0
            pbProgress.isVisible = isLoading
        }
    }
}