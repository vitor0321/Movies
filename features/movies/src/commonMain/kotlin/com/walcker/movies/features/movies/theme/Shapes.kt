package com.walcker.movies.features.movies.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp

private val SmallSpacing = 4.dp
private val MediumSpacing = 8.dp
private val LargeSpacing = 16.dp

internal val MoviesShapes = Shapes(
    small = RoundedCornerShape(SmallSpacing),
    medium = RoundedCornerShape(MediumSpacing),
    large = RoundedCornerShape(LargeSpacing)
)