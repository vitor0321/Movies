package com.walcker.movies.ui.movies

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.walcker.movies.data.network.KtorClient
import com.walcker.movies.data.network.imageSmallBaseUrl
import com.walcker.movies.domain.models.Movie
import com.walcker.movies.ui.components.MovieSection
import com.walcker.movies.ui.preview.mockData.movieTestData
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun MoviesListRoute() {
    var pupularmovies = remember {
        mutableStateOf(emptyList<Movie>())
    }

    LaunchedEffect(Unit) {
        val reseponse = KtorClient.getMovies("popular")
        pupularmovies.value = reseponse.results.map { movie ->
            Movie(
                id = movie.id,
                title = movie.title,
                overview = movie.overview,
                posterUrl = "$imageSmallBaseUrl${movie.posterPath}",
            )
        }
    }
    MoviesListScreen(
        popularMovies = pupularmovies.value.toImmutableList()
    )
}

@Composable
fun MoviesListScreen(
    popularMovies: ImmutableList<Movie>,
) {
    Scaffold { paddingValues ->
        LazyColumn(
            modifier = Modifier.padding(paddingValues),
            contentPadding = PaddingValues(vertical = 16.dp)
        ) {
            item {
                MovieSection(
                    title = "Popular Movies",
                    movies = popularMovies
                )
            }
            item {
                MovieSection(
                    modifier = Modifier.padding(top = 32.dp),
                    title = "Top Rated Movies",
                    movies = List(10) {
                        movieTestData
                    }.toImmutableList()
                )
            }
            item {
                MovieSection(
                    modifier = Modifier.padding(top = 32.dp),
                    title = "Upcoming Movies",
                    movies = List(10) {
                        movieTestData
                    }.toImmutableList()
                )
            }
        }
    }
}

@Preview
@Composable
private fun MoviesListScreenPreview() {
    MoviesListScreen(
        popularMovies = List(10) {
            movieTestData
        }.toImmutableList()
    )
}