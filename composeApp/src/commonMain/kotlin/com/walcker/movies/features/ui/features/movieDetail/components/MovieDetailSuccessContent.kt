package com.walcker.movies.features.ui.features.movieDetail.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.walcker.movies.features.domain.models.Movie
import com.walcker.movies.features.ui.preview.mockData.movieTestData
import com.walcker.movies.strings.features.MovieDetailString
import com.walcker.movies.strings.features.movieDetailStringsPt
import com.walcker.movies.theme.MoviesAppTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
internal fun MovieDetailSuccessContent(
    modifier: Modifier = Modifier,
    movie: Movie,
    string: MovieDetailString,
    onWatchClick: (Int) -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
    ) {
        HeaderDetail(
            modifier = Modifier.height(500.dp),
            posterUrl = movie.posterUrl
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            BodyDetail(
                movie = movie,
                string = string,
                onWatchClick = onWatchClick,
            )
            Spacer(modifier = Modifier.height(16.dp))
            BottomDetail(movie = movie)
        }
    }
}

@Preview
@Composable
private fun Preview() {
    MoviesAppTheme(isDarkTheme = false) {
        MovieDetailSuccessContent(
            movie = movieTestData,
            string = movieDetailStringsPt,
            onWatchClick = {},
        )
    }
}