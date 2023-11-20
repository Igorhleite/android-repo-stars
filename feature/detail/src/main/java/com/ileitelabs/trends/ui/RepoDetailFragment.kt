package com.ileitelabs.trends.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import coil.load
import coil.transform.CircleCropTransformation
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.ileitelabs.core.ui.viewmodel.onViewAction
import com.ileitelabs.core.ui.viewmodel.onViewState
import com.ileitelabs.repotrends.feature.detail.R
import com.ileitelabs.repotrends.feature.detail.databinding.DetailFragmentRepoBinding
import com.ileitelabs.trends.domain.model.RepositoryDetail
import com.ileitelabs.trends.ui.viewmodel.RepositoryDetailViewAction
import com.ileitelabs.trends.ui.viewmodel.RepositoryDetailViewModel
import com.ileitelabs.trends.ui.viewmodel.RepositoryDetailViewState.RepositoryDetailState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RepoDetailFragment : BottomSheetDialogFragment() {

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
        initErrorStateListener()
    }

    private fun initErrorStateListener() {
        binding.detailRepoError.onClickTryAgainButton {
            viewModel.onTryAgainClicked()
        }
    }

    private fun addObservables() {
        onViewState(viewModel) {
            manageViewState(it.state)
            manageRepositoryDetail(it.data)
        }

        onViewAction(viewModel) {
            when (it) {
                is RepositoryDetailViewAction.FetchData -> viewModel.obtainRepositoryDetail(
                    args.repositoryName, args.ownerName
                )
                is  RepositoryDetailViewAction.GoToExternalUrl -> openUrlInBrowser(it.url)

                else -> {}
            }
        }
    }

    private fun openUrlInBrowser(url: String) {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
    }

    private fun manageViewState(state: RepositoryDetailState) {
        binding.flipperRepoDetail.displayedChild = state.child
    }

    private fun manageRepositoryDetail(repository: RepositoryDetail?) {
        setRepositoryOwnerImage(repository?.owner?.avatarUrl)
        setRepositoryOwnerName(repository?.owner?.login)
        setRepositoryName(repository?.name)
        setRepositoryDescription(repository?.description)
        setRepositoryStars(repository?.stars)
        setRepositoryUrl(repository?.htmlUrl)
    }

    private fun setRepositoryName(name: String?) {
        binding.includeViewRepoDetailSuccessState.repoDetailNameTv.text =
            getString(R.string.detail_owner_name_label, name)
    }

    private fun setRepositoryUrl(htmlUrl: String?) {
        binding.includeViewRepoDetailSuccessState.repoCheckBtn.setOnClickListener {
            htmlUrl?.let {
                viewModel.onCheckRepoClicked(it)
            }
        }
    }

    private fun setRepositoryStars(stars: String?) {
        binding.includeViewRepoDetailSuccessState.repoStarsTv.text = stars
    }

    private fun setRepositoryDescription(description: String?) {
        binding.includeViewRepoDetailSuccessState.repoDescriptionTv.text =
            getString(R.string.detail_description_label, description)
    }

    private fun setRepositoryOwnerName(login: String?) {
        binding.includeViewRepoDetailSuccessState.repoDetailOwnerNameTv.text =
            getString(R.string.detail_owner_label, login)
    }

    private fun setRepositoryOwnerImage(avatarUrl: String?) {
        binding.includeViewRepoDetailSuccessState.repoDetailOwnerIv.load(avatarUrl) {
            crossfade(true)
            transformations(CircleCropTransformation())
        }
    }
}