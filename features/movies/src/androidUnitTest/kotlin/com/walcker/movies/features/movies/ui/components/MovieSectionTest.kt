package com.walcker.movies.features.movies.ui.components

import com.walcker.movies.features.movies.features.ui.preview.mockData.movieTestData
import com.walcker.movies.utils.DefaultPaparazzi
import com.walcker.movies.utils.movieSnapshot
import kotlinx.collections.immutable.toImmutableList
import org.junit.Rule
import kotlin.test.Test

internal class MovieSectionTest {
    @get:Rule
    val paparazzi = DefaultPaparazzi

    @Test
    fun snapshot() {
        paparazzi.movieSnapshot {
            MovieSection(
                title = "Filmes Populares",
                movies = List(10) {
                    movieTestData
                }.toImmutableList(),
                onPosterClick = {}
            )
        }
    }
}