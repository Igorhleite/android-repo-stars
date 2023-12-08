package com.ileitelabs.trends.presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ileitelabs.designsystem.component.RepoTrendsAsyncImage
import com.ileitelabs.designsystem.tokens.dimen.size32
import com.ileitelabs.trends.domain.model.RepositoryDetail

@Composable
fun SuccessContent(
    repository: RepositoryDetail,
    onAccessRepo: (url: String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 64.dp, start = 24.dp, end = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        RepoTrendsAsyncImage(
            imageUrl = repository.owner.avatarUrl,
            modifier = Modifier
                .width(200.dp)
                .height(200.dp)
        )
        RepositoryInfo(
            repositoryName = repository.name,
            repositoryOwnerName = repository.owner.login,
            repositoryDescription = repository.description,
            stars = repository.stars,
            watchers = repository.watchers,
            issues = repository.issues
        )
        Button(
            onClick = {
                onAccessRepo(repository.htmlUrl)
            },
            modifier = Modifier
                .padding(top = size32)
        ) {
            Text(
                modifier = Modifier.padding(horizontal = size32),
                text = "Visitar"
            )
        }
    }
}