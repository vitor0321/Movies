package com.walcker.movies.features.ui.features.movieDetail.components

import com.walcker.movies.features.ui.preview.mockData.movieTestData
import com.walcker.movies.strings.features.movieDetailStringsPt
import com.walcker.movies.utils.DefaultPaparazzi
import com.walcker.movies.utils.movieSnapshot
import org.junit.Rule
import kotlin.test.Test

internal class MovieDetailSuccessContentTest {
    @get:Rule
    val paparazzi = DefaultPaparazzi

    @Test
    fun snapshot() {
        paparazzi.movieSnapshot {
            MovieDetailSuccessContent(
                movie = movieTestData,
                string = movieDetailStringsPt,
                onWatchClick = {},
            )
        }
    }
}