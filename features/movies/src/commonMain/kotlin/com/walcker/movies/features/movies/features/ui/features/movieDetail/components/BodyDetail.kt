package com.walcker.movies.features.movies.features.ui.features.movieDetail.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.walcker.movies.features.movies.features.domain.models.Movie
import com.walcker.movies.features.movies.features.ui.components.MovieBadge
import com.walcker.movies.features.movies.features.ui.components.MovieInfoItem
import com.walcker.movies.features.movies.features.ui.preview.mockData.movieTestData
import com.walcker.movies.features.movies.strings.features.MovieDetailString
import com.walcker.movies.features.movies.strings.features.movieDetailStringsPt
import com.walcker.movies.features.movies.theme.MoviesAppTheme
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.Calendar
import compose.icons.fontawesomeicons.solid.Clock
import compose.icons.fontawesomeicons.solid.Play
import compose.icons.fontawesomeicons.solid.Star
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
internal fun BodyDetail(
    modifier: Modifier = Modifier,
    movie: Movie,
    string: MovieDetailString,
    onWatchClick: (String) -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = movie.title,
            modifier = Modifier.padding(horizontal = 16.dp),
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.headlineMedium,
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            MovieInfoItem(
                icon = FontAwesomeIcons.Solid.Star,
                text = movie.rating,
            )
            Spacer(modifier = Modifier.width(16.dp))
            movie.duration?.let { duration ->
                MovieInfoItem(
                    icon = FontAwesomeIcons.Solid.Clock,
                    text = duration,
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            MovieInfoItem(
                icon = FontAwesomeIcons.Solid.Calendar,
                text = movie.year,
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        movie.genres?.let { genres ->
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
                modifier = Modifier.fillMaxWidth()
            ) {
                items(genres) { genre ->
                    MovieBadge(text = genre.name)
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        movie.moviesTrailerYouTubeKey?.let {
            ElevatedButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                onClick = { onWatchClick(it) },
            ) {
                Icon(
                    imageVector = FontAwesomeIcons.Solid.Play,
                    modifier = Modifier.size(12.dp),
                    contentDescription = null,
                )
                Text(
                    text = string.buttonText,
                    modifier = Modifier.padding(start = 16.dp),
                    fontWeight = FontWeight.Medium,
                    style = MaterialTheme.typography.bodyMedium,
                )
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    MoviesAppTheme {
        BodyDetail(
            movie = movieTestData,
            string = movieDetailStringsPt,
            onWatchClick = { },
        )
    }
}