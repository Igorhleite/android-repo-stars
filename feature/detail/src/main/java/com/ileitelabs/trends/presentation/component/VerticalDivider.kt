package com.ileitelabs.trends.presentation.component

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.ileitelabs.designsystem.tokens.dimen.size1
import com.ileitelabs.designsystem.tokens.dimen.size32

//Mover para DS
@Composable
fun VerticalDivider() {
    Divider(
        color = Color.Gray,
        modifier = Modifier
            .height(size32)
            .width(size1)
    )
}