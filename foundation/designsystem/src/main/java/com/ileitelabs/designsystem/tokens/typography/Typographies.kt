package com.ileitelabs.designsystem.tokens.typography

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.ileitelabs.designsystem.tokens.dimen.font12
import com.ileitelabs.designsystem.tokens.dimen.font16
import com.ileitelabs.designsystem.tokens.dimen.font20
import com.ileitelabs.designsystem.tokens.dimen.font22
import com.ileitelabs.repotrends.foundation.designsystem.R

val repoTrendsTypography = Typography(
    titleLarge = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = font22,
        lineHeight = 28.sp,
        letterSpacing = 0.2.sp,
        fontFamily = FontFamily(R.font.poppinsbold.asFont())
    ),
    titleMedium = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = font16,
        lineHeight = 24.sp,
        letterSpacing = 0.15.sp,
        fontFamily = FontFamily(R.font.poppinsbold.asFont())
    ),
    titleSmall = TextStyle(
        fontWeight = FontWeight.SemiBold,
        fontSize = font20,
        letterSpacing = 0.1.sp,
        lineHeight = 22.sp,
        fontFamily = FontFamily(R.font.poppinsregular.asFont())
    ),
    bodyMedium = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = font16,
        letterSpacing = 0.5.sp,
        lineHeight = 24.sp,
        fontFamily = FontFamily(R.font.poppinsregular.asFont())
    ),
    bodySmall = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = font12,
        letterSpacing = 0.25.sp,
        lineHeight = 20.sp,
        fontFamily = FontFamily(R.font.poppinsregular.asFont())
    ),
    labelSmall = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = font12,
        letterSpacing = 1.25.sp,
        lineHeight = 20.sp,
        fontFamily = FontFamily(R.font.poppinslight.asFont())
    )
)

private fun Int.asFont(): Font = Font(this)
