package com.walcker.movies.features.ui.features.movies.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.walcker.movies.features.domain.models.Movie
import com.walcker.movies.features.ui.components.MovieAsyncImage
import com.walcker.movies.features.ui.preview.mockData.movieTestData
import com.walcker.movies.theme.MoviesAppTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
internal fun HeaderSuccessContent(
    movie: Movie,
    modifier: Modifier = Modifier,
    onPosterClick: (movieId: Int) -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .height(450.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            text = movie.title,
            overflow = TextOverflow.Ellipsis,
            fontFamily = MaterialTheme.typography.headlineSmall.fontFamily,
            fontStyle = MaterialTheme.typography.headlineSmall.fontStyle,
            style = MaterialTheme.typography.headlineSmall,
            maxLines = 1,
        )
        MovieAsyncImage(
            imageUrl = movie.posterUrl,
            modifier = Modifier
                .clip(MaterialTheme.shapes.large)
                .clickable(
                    enabled = true,
                    onClick = { onPosterClick(movie.id) },
                )
        )
    }
}

@Preview
@Composable
private fun Preview() {
    MoviesAppTheme {
        HeaderSuccessContent(
            movie = movieTestData,
            onPosterClick = {},
        )
    }
}