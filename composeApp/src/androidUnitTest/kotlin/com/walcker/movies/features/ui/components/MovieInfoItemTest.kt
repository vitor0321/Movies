package com.walcker.movies.features.ui.components

import com.walcker.movies.utils.DefaultPaparazzi
import com.walcker.movies.utils.movieSnapshot
import org.junit.Rule
import kotlin.test.Test

internal class MovieInfoItemTest {
    @get:Rule
    val paparazzi = DefaultPaparazzi

    @Test
    fun snapshot() {
        paparazzi.movieSnapshot {
            MovieBadge(text = "Action")
        }
    }
}