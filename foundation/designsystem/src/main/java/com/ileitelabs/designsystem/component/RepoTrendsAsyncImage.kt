package com.ileitelabs.designsystem.component

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage
import com.ileitelabs.repotrends.foundation.designsystem.R

@Composable
fun RepoTrendsAsyncImage(
    imageUrl: String,
    modifier: Modifier,
    contentScale: ContentScale = ContentScale.Crop,
    shape: Shape = CircleShape,
    placeholder: Painter = painterResource(R.drawable.ic_placeholder),
    contentDescription: String? = null
) {
    AsyncImage(
        model = imageUrl,
        placeholder = placeholder,
        contentDescription = contentDescription,
        contentScale = contentScale,
        modifier = modifier.clip(shape)
    )
}