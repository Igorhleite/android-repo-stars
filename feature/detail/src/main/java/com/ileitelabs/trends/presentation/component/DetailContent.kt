package com.ileitelabs.trends.presentation.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.ileitelabs.designsystem.theme.RepoTrendsTheme
import com.ileitelabs.trends.domain.model.RepositoryDetail
import com.ileitelabs.trends.domain.model.RepositoryDetailOwner
import com.ileitelabs.trends.presentation.viewmodel.RepositoryDetailViewState.RepositoryDetailState

@Composable
internal fun DetailContent(
    state: RepositoryDetailState,
    repository: RepositoryDetail?,
    onTryAgain: () -> Unit,
    onAccessRepo: (url: String) -> Unit
) {
    AnimatedVisibility(
        visible = state == RepositoryDetailState.LOADING
    ) {
        LoadingContent()
    }

    AnimatedVisibility(
        visible = state == RepositoryDetailState.SUCCESS,
        enter = fadeIn() + slideInHorizontally(),
        exit = fadeOut() + slideOutHorizontally()
    ) {
        repository?.let { repoDetail ->
            SuccessContent(
                repository = repoDetail
            ) { repoUrl ->
                onAccessRepo(repoUrl)
            }
        }
    }

    AnimatedVisibility(visible = state == RepositoryDetailState.ERROR) {
        /*ErrorContent(
            onTryAgain = onTryAgain
        )*/
    }
}


@Preview(showBackground = true, widthDp = 390, heightDp = 800)
@Composable
fun DetailContentPreview() {
    RepoTrendsTheme {
        DetailContent(
            state = RepositoryDetailState.SUCCESS,
            RepositoryDetail(
                name = "Repo Name",
                fullName = "Owner Name",
                description = "Description",
                owner = RepositoryDetailOwner(
                    avatarUrl = "https://avatars.githubusercontent.com/u/1?v=4",
                    login = "Login",
                    htmlUrl = ""
                ),
                htmlUrl = "",
                stars = "55432",
                watchers = "1233",
                issues = "12322"
            ),
            onTryAgain = {},
            onAccessRepo = {}
        )
    }
}