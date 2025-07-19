package com.walcker.movies.features.ui.features.movies.components

import com.walcker.movies.features.ui.preview.mockData.movieSectionTestData
import com.walcker.movies.strings.features.moviesListStringsPt
import com.walcker.movies.utils.DefaultPaparazzi
import com.walcker.movies.utils.movieSnapshot
import org.junit.Rule
import kotlin.test.Test

internal class MoviesListSuccessContentTest {
    @get:Rule
    val paparazzi = DefaultPaparazzi

    @Test
    fun snapshot() {
        paparazzi.movieSnapshot {
            MoviesListSuccessContent(
                strings = moviesListStringsPt,
                movies = movieSectionTestData,
                onPosterClick = {},
                onLoadMore = {}
            )
        }
    }
}