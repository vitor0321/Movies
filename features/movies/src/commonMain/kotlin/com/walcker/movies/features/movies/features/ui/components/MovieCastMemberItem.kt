package com.walcker.movies.features.movies.features.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.ZeroCornerSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.walcker.movies.features.movies.theme.MoviesAppTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
internal fun MovieCastMemberItem(
    modifier: Modifier = Modifier,
    imageUrl: String?,
    name: String,
    character: String,
) {
    Surface(
        modifier = modifier
            .height(76.dp),
        shape = MaterialTheme.shapes.medium,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            MovieAsyncImage(
                modifier = Modifier
                    .widthIn(max = 68.dp)
                    .clip(
                        MaterialTheme.shapes.medium.copy(
                            topEnd = ZeroCornerSize,
                            bottomEnd = ZeroCornerSize,
                        )
                    ),
                imageUrl = imageUrl
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Text(
                    text = name,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(bottom = 2.dp)
                )
                Text(
                    text = character,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray,
                )
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    MoviesAppTheme {
        MovieCastMemberItem(
            imageUrl = "https://example.com/profile.jpg",
            name = "John Doe",
            character = "Main Character"
        )
    }
}