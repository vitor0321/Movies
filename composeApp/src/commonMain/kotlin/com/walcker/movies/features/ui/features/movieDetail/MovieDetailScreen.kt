package com.walcker.movies.features.ui.features.movieDetail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.walcker.movies.features.domain.models.Movie
import com.walcker.movies.features.ui.components.MovieTopAppBar
import com.walcker.movies.features.ui.features.movieDetail.components.BodyDetail
import com.walcker.movies.features.ui.features.movieDetail.components.BottomDetail
import com.walcker.movies.features.ui.features.movieDetail.components.HeaderDetail
import com.walcker.movies.features.ui.preview.mockData.movieTestData
import com.walcker.movies.theme.MoviesAppTheme
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.ArrowLeft
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
internal fun MovieDetailRoute() {
    MovieDetailScreen(
        movie = movieTestData
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun MovieDetailScreen(movie: Movie) {
    Scaffold(
        topBar = {
            MovieTopAppBar(
                title = "Movie Detail",
                icon = FontAwesomeIcons.Solid.ArrowLeft,
                onBackPressed = { /*TODO*/ } // TODO move to real result
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            HeaderDetail(
                modifier = Modifier.weight(1f),
                posterUrl = movie.posterUrl
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(2f)
                    .padding(top = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                BodyDetail(movie = movie)
                Spacer(modifier = Modifier.height(16.dp))
                BottomDetail()
            }
        }
    }
}

@Preview
@Composable
private fun MovieDetailScreenLightPreview() {
    MoviesAppTheme(isDarkTheme = false) {
        MovieDetailScreen(movieTestData)
    }
}

@Preview
@Composable
private fun MovieDetailScreenDarkPreview() {
    MoviesAppTheme {
        MovieDetailScreen(movieTestData)
    }
}