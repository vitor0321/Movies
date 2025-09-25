package com.walcker.movies.features.movies.ui.components

import com.walcker.movies.utils.DefaultPaparazzi
import com.walcker.movies.utils.movieSnapshot
import org.junit.Rule
import kotlin.test.Test

internal class MovieDotsIndicatorTest {

    @get:Rule
    val paparazzi = DefaultPaparazzi

    @Test
    fun snapshot() {
        paparazzi.movieSnapshot {
            MovieDotsIndicator(
                count = 5,
                selectedIndex = 2
            )
        }
    }
}