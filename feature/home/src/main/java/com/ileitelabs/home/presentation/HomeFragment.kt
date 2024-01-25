package com.ileitelabs.home.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.ileitelabs.core.ui.viewmodel.onViewAction
import com.ileitelabs.core.ui.viewmodel.onViewState
import com.ileitelabs.home.domain.model.Repository
import com.ileitelabs.home.presentation.viewmodel.HomeViewAction
import com.ileitelabs.home.presentation.viewmodel.HomeViewModel
import com.ileitelabs.navigation.RepoTrendsNavigation
import com.ileitelabs.repotrends.feature.home.databinding.HomeFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import androidx.paging.compose.items
import com.ileitelabs.home.presentation.component.RepositoryListItem

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: HomeFragmentBinding? = null
    private val binding: HomeFragmentBinding get() = _binding!!

    private val viewModel: HomeViewModel by viewModels()

    private val navController by lazy { findNavController() }

    @Inject
    lateinit var navigator: RepoTrendsNavigation

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
        setTryAgainButtonAction()
        addObservables()
    }

    private fun initLazyColumn(data: Flow<PagingData<Repository>>?) {
        binding.composeListView.setContent {
            data?.let { CharacterList(it) }
        }
    }

    @Composable
    fun CharacterList(repositoryFlow: Flow<PagingData<Repository>>) {

        val lazyRepository: LazyPagingItems<Repository> = repositoryFlow.collectAsLazyPagingItems()

        LazyColumn {
            items(lazyRepository) { repository ->
                RepositoryListItem(repository)
            }
        }
    }

    private fun addObservables() {
        onViewState(viewModel) { state ->
            initLazyColumn(state.data)
            manageLoading(state.hasLoading)
            manageError(state.emptyDataError, state.refreshDataError)
        }

        onViewAction(viewModel) { action ->
            when (action) {
                is HomeViewAction.FetchData -> viewModel.obtainRepositories()
                is HomeViewAction.NavigateToDetail -> navigateToDetail(
                    action.repositoryName,
                    action.ownerName
                )

                else -> {}
            }
        }
    }

    private fun navigateToDetail(repository: String, ownerName: String) {
        navigator.navigateToRepositoryDetail(navController, repository, ownerName)
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

    private fun setTryAgainButtonAction() {
        binding.screenError.onClickTryAgainButton {
            viewModel.onTryAgainClicked()
        }
    }
}