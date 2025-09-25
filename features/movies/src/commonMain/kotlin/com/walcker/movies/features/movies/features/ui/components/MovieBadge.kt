package com.walcker.movies.features.movies.features.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.walcker.movies.features.movies.theme.MoviesAppTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
internal fun MovieBadge(
    modifier: Modifier = Modifier,
    text: String,
    styleText: TextStyle = MaterialTheme.typography.labelSmall,
    colorText: Color = MaterialTheme.colorScheme.onPrimary,
    colorBackground: Color = MaterialTheme.colorScheme.primary
) {
    Surface(
        modifier = modifier,
        shape = MaterialTheme.shapes.small,
        color = colorBackground
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(horizontal = 8.dp),
            style = styleText,
            color = colorText,
        )
    }
}

@Preview()
@Composable
private fun LightPreview() {
    MoviesAppTheme(isDarkTheme = false) {
        MovieBadge(
            text = "Badge",
        )
    }
}

@Preview()
@Composable
private fun DarkPreview() {
    MoviesAppTheme(isDarkTheme = true) {
        MovieBadge(
            text = "Badge",
        )
    }
}