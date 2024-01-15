package com.ileitelabs.trends.presentation.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.ileitelabs.designsystem.component.RepoTrendsAnimatedVisibility
import com.ileitelabs.designsystem.theme.RepoTrendsTheme
import com.ileitelabs.trends.domain.model.RepositoryDetail
import com.ileitelabs.trends.domain.model.RepositoryDetailOwner
import com.ileitelabs.trends.presentation.viewmodel.RepositoryDetailViewState.RepositoryDetailState

@Composable
internal fun DetailScreenContent(
    state: RepositoryDetailState,
    repository: RepositoryDetail,
    doRefresh: () -> Unit,
    onClickAction: (url: String) -> Unit
) {

    RepoTrendsAnimatedVisibility(visible = state == RepositoryDetailState.LOADING) {
        LoadingContent()
    }

    RepoTrendsAnimatedVisibility(visible = state == RepositoryDetailState.SUCCESS) {
        SuccessContent(repository = repository) { repoUrl ->
            onClickAction(repoUrl)
        }
    }

    RepoTrendsAnimatedVisibility(visible = state == RepositoryDetailState.ERROR) {
        ErrorContent(
            refresh = doRefresh
        )
    }
}

@Preview(showBackground = true, widthDp = 390, heightDp = 800)
@Composable
fun DetailContentPreview() {
    RepoTrendsTheme {
        DetailScreenContent(
            state = RepositoryDetailState.ERROR,
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
            doRefresh = {},
            onClickAction = {}
        )
    }
}