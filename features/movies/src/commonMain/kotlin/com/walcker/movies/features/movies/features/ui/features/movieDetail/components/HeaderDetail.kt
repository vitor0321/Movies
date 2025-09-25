package com.walcker.movies.features.movies.features.ui.features.movieDetail.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.walcker.movies.features.movies.features.ui.components.MovieAsyncImage
import com.walcker.movies.features.movies.theme.MoviesAppTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
internal fun HeaderDetail(
    modifier: Modifier = Modifier,
    posterUrl: String,
) {
    Surface(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        shape = MaterialTheme.shapes.large,
    ) {
        MovieAsyncImage(
            imageUrl = posterUrl,
            modifier = Modifier.clip(MaterialTheme.shapes.large),
        )
    }
}

@Preview
@Composable
private fun Preview() {
    MoviesAppTheme {
        HeaderDetail(
            posterUrl = "https://image.tmdb.org/t/p/w500/1LRLLWGvs5sZdTzuMqLEahb88Pc.jpg"
        )
    }
}