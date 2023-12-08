package com.ileitelabs.designsystem.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.ileitelabs.designsystem.tokens.shape.repoTrendsShapes
import com.ileitelabs.designsystem.tokens.typography.repoTrendsTypography

@Composable
fun RepoTrendsTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        shapes = repoTrendsShapes,
        typography = repoTrendsTypography,
        content = content
    )
}