package com.ileitelabs.designsystem.tokens.shape

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import com.ileitelabs.designsystem.tokens.dimen.size12
import com.ileitelabs.designsystem.tokens.dimen.size16
import com.ileitelabs.designsystem.tokens.dimen.size24
import com.ileitelabs.designsystem.tokens.dimen.size4
import com.ileitelabs.designsystem.tokens.dimen.size8

val repoTrendsShapes = Shapes(
    extraSmall = RoundedCornerShape(size4),
    small = RoundedCornerShape(size8),
    medium = RoundedCornerShape(size12),
    large = RoundedCornerShape(size16),
    extraLarge = RoundedCornerShape(size24)
)
