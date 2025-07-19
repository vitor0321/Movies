package com.walcker.movies.features.ui.components

import com.walcker.movies.features.ui.preview.mockData.movieTestData
import com.walcker.movies.utils.DefaultPaparazzi
import com.walcker.movies.utils.movieSnapshot
import org.junit.Rule
import kotlin.test.Test

internal class MoviePosterTest {
    @get:Rule
    val paparazzi = DefaultPaparazzi

    @Test
    fun snapshot() {
        paparazzi.movieSnapshot {
            MoviePoster(
                movie = movieTestData,
                onPosterClick = {}
            )
        }
    }
}