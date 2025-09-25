package com.walcker.movies.features.movies.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

// Dark theme
private val Primary80 = Color(0xFFE50914)
private val BackgroundDark = Color(0xFF121212)
private val SurfaceDark = Color(0xFF1E1E1E)
private val TextPrimaryDark = Color(0xFFFFFFFF)
private val ColorError = Color(0xFFF24E1E)
private val Neutral60 = Color(0xFF8A91A8)

// Light theme
private val Primary80Light = Color(0xFFE50914)
private val BackgroundLight = Color(0xFFF8F8F8)
private val SurfaceLight = Color(0xFFFFFFFF)
private val TextPrimaryLight = Color(0xFF121212)
private val ColorErrorLight = Color(0xFFD00000)
private val Neutral60Light = Color(0xFF6E7491)


internal val MoviesDarkColorScheme = darkColorScheme(
    primary = Primary80,
    onPrimary = Color.White,
    background = BackgroundDark,
    onBackground = TextPrimaryDark,
    surface = SurfaceDark,
    onSurface = TextPrimaryDark,
    secondary = Neutral60,
    onSecondary = TextPrimaryDark,
    error = ColorError,
    onError = Color.White
)

internal val MoviesLightColorScheme = lightColorScheme(
    primary = Primary80Light,
    onPrimary = Color.White,
    background = BackgroundLight,
    onBackground = TextPrimaryLight,
    surface = SurfaceLight,
    onSurface = TextPrimaryLight,
    secondary = Neutral60Light,
    onSecondary = Color.White,
    error = ColorErrorLight,
    onError = Color.White
)