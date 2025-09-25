package com.walcker.movies.features.movies.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import flickly.features.movies.generated.resources.Res
import flickly.features.movies.generated.resources.urbanist_bold
import flickly.features.movies.generated.resources.urbanist_medium
import flickly.features.movies.generated.resources.urbanist_regular
import org.jetbrains.compose.resources.Font

private val urbanist: FontFamily
    @Composable get() = FontFamily(
        Font(Res.font.urbanist_regular, FontWeight.Normal),
        Font(Res.font.urbanist_medium, FontWeight.Medium),
        Font(Res.font.urbanist_bold, FontWeight.Bold),
    )

@Composable
internal fun MoviesTypography() = Typography(
    displaySmall = TextStyle(
        fontSize = 26.sp,
        fontWeight = FontWeight.Normal,
        fontFamily = urbanist,
    ),
    headlineLarge = TextStyle(
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold,
        fontFamily = urbanist,
    ),
    titleLarge = TextStyle(
        fontSize = 20.sp,
        fontWeight = FontWeight.Normal,
        fontFamily = urbanist,
    ),
    titleMedium = TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.Medium,
        fontFamily = urbanist,
    ),
    titleSmall = TextStyle(
        fontSize = 14.sp,
        fontWeight = FontWeight.Medium,
        fontFamily = urbanist,
    ),
    bodyLarge = TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.Normal,
        fontFamily = urbanist,
    ),
    bodyMedium = TextStyle(
        fontSize = 14.sp,
        fontWeight = FontWeight.Normal,
        fontFamily = urbanist,
    ),
    bodySmall = TextStyle(
        fontSize = 12.sp,
        fontWeight = FontWeight.Normal,
        fontFamily = urbanist,
    ),
)