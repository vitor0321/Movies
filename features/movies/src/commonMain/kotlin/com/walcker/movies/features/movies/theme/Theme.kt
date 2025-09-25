package com.walcker.movies.features.movies.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
internal fun MoviesAppTheme(
    isDarkTheme: Boolean = true,
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = if (isDarkTheme) MoviesDarkColorScheme else MoviesLightColorScheme,
        typography = MoviesTypography(),
        shapes = MoviesShapes,
        content = content
    )
}