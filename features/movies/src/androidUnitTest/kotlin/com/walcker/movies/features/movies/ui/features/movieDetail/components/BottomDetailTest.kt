package com.walcker.movies.features.movies.ui.features.movieDetail.components

import com.walcker.movies.features.movies.features.ui.preview.mockData.movieTestData
import com.walcker.movies.utils.DefaultPaparazzi
import com.walcker.movies.utils.movieSnapshot
import org.junit.Rule
import kotlin.test.Test

internal class BottomDetailTest {
    @get:Rule
    val paparazzi = DefaultPaparazzi

    @Test
    fun snapshot() {
        paparazzi.movieSnapshot {
            BottomDetail(
                movie = movieTestData,
            )
        }
    }
}