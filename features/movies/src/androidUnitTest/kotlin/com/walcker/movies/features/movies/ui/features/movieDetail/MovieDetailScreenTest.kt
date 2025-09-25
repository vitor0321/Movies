package com.walcker.movies.features.movies.ui.features.movieDetail

import com.walcker.movies.features.movies.features.ui.preview.mockData.movieTestData
import com.walcker.movies.features.movies.strings.features.movieDetailStringsPt
import com.walcker.movies.utils.DefaultPaparazzi
import com.walcker.movies.utils.movieSnapshot
import org.junit.Rule
import kotlin.test.Test

internal class MovieDetailScreenTest {
    @get:Rule
    val paparazzi = DefaultPaparazzi

    @Test
    fun snapshot() {
        paparazzi.movieSnapshot {
            MovieDetailScreen(
                uiState = MovieDetailUiState.Success(movieTestData),
                string = movieDetailStringsPt,
                onNavigationBack = {},
            )
        }
    }
}