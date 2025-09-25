package com.walcker.movies.features.movies.features.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.walcker.movies.features.movies.features.domain.models.Movie
import com.walcker.movies.features.movies.features.ui.preview.mockData.movieTestData
import com.walcker.movies.features.movies.theme.MoviesAppTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
internal fun MoviePoster(
    modifier: Modifier = Modifier,
    movie: Movie,
    onPosterClick: (movieId: Int) -> Unit,
) {
    Surface(
        modifier = modifier
            .width(140.dp)
            .height(230.dp),
        color = MaterialTheme.colorScheme.background
        ) {
        Column(
            modifier = Modifier
                .clickable { onPosterClick(movie.id) }
                .width(140.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Card(
                modifier = Modifier
                    .width(140.dp)
                    .height(210.dp),
                shape = RoundedCornerShape(12.dp),
            ) {
                MovieAsyncImage(imageUrl = movie.posterUrl)
            }

            Text(
                text = movie.title,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.titleMedium,
                maxLines = 1,
            )
        }
    }
}

@Preview()
@Composable
private fun Preview() {
    MoviesAppTheme {
        MoviePoster(
            movie = movieTestData,
            onPosterClick = {}
        )
    }
}
