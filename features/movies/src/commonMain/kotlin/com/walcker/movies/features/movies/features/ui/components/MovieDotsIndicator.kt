package com.walcker.movies.features.movies.features.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.walcker.movies.features.movies.theme.MoviesAppTheme
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Regular
import compose.icons.fontawesomeicons.regular.DotCircle
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
internal fun MovieDotsIndicator(
    modifier: Modifier = Modifier,
    count: Int,
    selectedIndex: Int,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        repeat(times = count) { index ->
            Icon(
                modifier = Modifier.size(15.dp),
                imageVector = FontAwesomeIcons.Regular.DotCircle,
                contentDescription = null,
                tint = if (index == selectedIndex) Color.White else Color.Gray,
            )
        }
    }
}

@Preview()
@Composable
private fun Preview() {
    MoviesAppTheme {
        MovieDotsIndicator(
            count = 5,
            selectedIndex = 2
        )
    }
}