package com.ileitelabs.designsystem.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.ileitelabs.designsystem.tokens.dimen.font16
import com.ileitelabs.designsystem.tokens.dimen.size200
import com.ileitelabs.repotrends.foundation.designsystem.R

@Composable
fun RepoTrendsError(refresh: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(), contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {

            Image(
                painter = painterResource(id = R.drawable.ic_error_screen),
                contentDescription = "Imagem de erro",
                modifier = Modifier.size(size200),
                alignment = Alignment.Center
            )
            Text(
                text = stringResource(id = R.string.repo_trends_error_msg),
                style = MaterialTheme.typography.titleMedium,
                fontSize = font16,
                textAlign = TextAlign.Center
            )
            Button(
                modifier = Modifier.padding(24.dp),
                onClick = { refresh() }
            ) {
                Text(
                    text = stringResource(id = R.string.repo_trends_try_again),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}