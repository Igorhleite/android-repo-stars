package com.ileitelabs.trends.presentation

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.ileitelabs.core.ui.viewmodel.onViewAction
import com.ileitelabs.core.ui.viewmodel.onViewState
import com.ileitelabs.designsystem.theme.RepoTrendsTheme
import com.ileitelabs.repotrends.feature.detail.databinding.DetailFragmentRepoBinding
import com.ileitelabs.trends.presentation.component.DetailScreenContent
import com.ileitelabs.trends.presentation.viewmodel.RepositoryDetailViewAction
import com.ileitelabs.trends.presentation.viewmodel.RepositoryDetailViewModel
import com.ileitelabs.trends.presentation.viewmodel.RepositoryDetailViewState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RepoDetailFragment : Fragment() {

    private var _binding: DetailFragmentRepoBinding? = null
    private val binding: DetailFragmentRepoBinding get() = _binding!!

    private val args by navArgs<RepoDetailFragmentArgs>()

    private val viewModel: RepositoryDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ) = DetailFragmentRepoBinding.inflate(
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
        initView()
    }

    private fun initView() {
        viewModel.obtainRepositoryDetail(args.repositoryName, args.ownerName)
        addObservables()
    }

    private fun addObservables() {
        onViewState(viewModel) {
            manageRepositoryDetail(it)
        }

        onViewAction(viewModel) {
            when (it) {
                is RepositoryDetailViewAction.FetchData -> viewModel.obtainRepositoryDetail(
                    args.repositoryName, args.ownerName
                )

                is RepositoryDetailViewAction.GoToExternalUrl -> openUrlInBrowser(it.url)

                else -> {}
            }
        }
    }

    private fun openUrlInBrowser(url: String) {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
    }

    private fun manageRepositoryDetail(state: RepositoryDetailViewState) {
        binding.composeView.setContent {
            RepoTrendsTheme {
                DetailScreenContent(
                    state = state.state,
                    repository = state.data,
                    doRefresh = viewModel::onTryAgainClicked,
                    onClickAction = viewModel::onCheckRepoClicked
                )
            }
        }
    }
}