package com.walcker.movies.ui.movies

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.walcker.movies.ui.components.MovieSection
import com.walcker.movies.ui.preview.mockData.movieTestData
import kotlinx.collections.immutable.toImmutableList
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
public fun MoviesListRoute() {
    MoviesListScreen()
}

@Composable
public fun MoviesListScreen() {
    Scaffold { paddingValues ->
        LazyColumn(
            modifier = Modifier.padding(paddingValues),
            contentPadding = PaddingValues(vertical = 16.dp)
        ) {
            item {
                MovieSection(
                    title = "Popular Movies",
                    movies = List(10) {
                        movieTestData
                    }.toImmutableList()
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
internal fun MoviesListScreenPreview() {
    MoviesListScreen()
}