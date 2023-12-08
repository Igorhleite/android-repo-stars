package com.ileitelabs.trends.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import com.ileitelabs.designsystem.tokens.dimen.size12
import com.ileitelabs.designsystem.tokens.dimen.size16
import com.ileitelabs.designsystem.tokens.dimen.size24
import com.ileitelabs.designsystem.tokens.dimen.size32
import com.ileitelabs.designsystem.tokens.dimen.size4
import com.ileitelabs.designsystem.tokens.dimen.size8

@Composable
fun RepositoryInfo(
    repositoryName: String,
    repositoryOwnerName: String,
    repositoryDescription: String,
    stars: String,
    watchers: String,
    issues: String
) {
    Column(
        modifier = Modifier.padding(top = size16),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = repositoryOwnerName,
            style = MaterialTheme.typography.bodyLarge,
        )
        Text(
            modifier = Modifier.padding(top = size12),
            text = repositoryName,
            style = MaterialTheme.typography.titleMedium,
        )
        RepoDescription(
            repositoryDescription = repositoryDescription
        )
        RepoInfoNumbers(
            stars = stars,
            watchers = watchers,
            issues = issues
        )
    }
}

@Composable
fun RepoDescription(repositoryDescription: String) {
    Column {
        Text(
            modifier = Modifier
                .padding(top = size4)
                .clip(shape = MaterialTheme.shapes.small)
                .background(Color.LightGray)
                .padding(horizontal = size32, vertical = size16),
            text = repositoryDescription,
            style = MaterialTheme.typography.bodySmall,
        )
    }
}

@Composable
fun RepoInfoNumbers(
    stars: String,
    watchers: String,
    issues: String
) {
    Row(
        modifier = Modifier
            .padding(top = size32)
            .clip(shape = MaterialTheme.shapes.extraLarge)
            .background(Color.LightGray)
            .padding(vertical = size8, horizontal = size24),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        RepoInfoNumber(
            number = stars,
            label = "stars"
        )
        VerticalDivider()
        RepoInfoNumber(
            number = watchers,
            label = "watchers"
        )
        VerticalDivider()
        RepoInfoNumber(
            number = issues,
            label = "issues"
        )
    }
}

@Composable
fun RepoInfoNumber(
    label: String = "Stars",
    number: String = "123124"
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(horizontal = size8)
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodySmall,
        )
        Text(
            modifier = Modifier.padding(top = size4),
            text = number,
            style = MaterialTheme.typography.titleSmall,
        )
    }
}