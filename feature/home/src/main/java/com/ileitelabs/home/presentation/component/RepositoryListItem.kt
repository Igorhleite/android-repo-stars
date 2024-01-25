package com.ileitelabs.home.presentation.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.ileitelabs.designsystem.component.RepoTrendsAsyncImage
import com.ileitelabs.designsystem.tokens.dimen.size100
import com.ileitelabs.designsystem.tokens.dimen.size16
import com.ileitelabs.designsystem.tokens.dimen.size32
import com.ileitelabs.designsystem.tokens.dimen.size4
import com.ileitelabs.designsystem.tokens.dimen.size8
import com.ileitelabs.designsystem.tokens.dimen.size80
import com.ileitelabs.home.domain.model.Repository
import com.ileitelabs.repotrends.foundation.designsystem.R as dsR

@Composable
fun RepositoryListItem(repository: Repository?) {
    Row(
        modifier = Modifier
            .padding(horizontal = size8, vertical = size4)
            .fillMaxWidth()
            .wrapContentHeight()
            .clip(shape = MaterialTheme.shapes.extraLarge)
            .background(Color.LightGray)
    ) {
        RepoTrendsAsyncImage(
            imageUrl = repository?.owner?.img.orEmpty(),
            modifier = Modifier
                .padding(size4)
                .height(size80)
                .width(size80)
        )

        Spacer(modifier = Modifier.width(size16))

        Column(
            modifier = Modifier.fillMaxHeight()
        ) {
            Text(
                text = repository?.owner?.name.orEmpty(),
                style = MaterialTheme.typography.titleSmall
            )

            Spacer(modifier = Modifier.height(4.dp))

            RepoTrendsTextWithIconLeft(
                text = repository?.name.orEmpty(), iconResId = dsR.drawable.ic_person
            )

            Row {
                RepoTrendsTextWithIconLeft(
                    text = repository?.stars.toString(), iconResId = dsR.drawable.ic_star
                )
                RepoTrendsTextWithIconLeft(
                    modifier = Modifier.padding(
                        start = 12.dp,
                        top = 0.dp,
                        end = 0.dp,
                        bottom = 0.dp
                    ),
                    text = repository?.forks.toString(),
                    iconResId = dsR.drawable.ic_fork
                )
            }
        }
    }
}

@Composable
fun RepoTrendsTextWithIconLeft(
    modifier: Modifier = Modifier,
    text: String,
    @DrawableRes iconResId: Int,
    imageContentDescription: String? = null
) {
    Row(modifier = modifier) {
        Icon(
            painter = painterResource(id = iconResId), contentDescription = imageContentDescription
        )
        Text(
            text = text, style = MaterialTheme.typography.bodySmall
        )
    }
}