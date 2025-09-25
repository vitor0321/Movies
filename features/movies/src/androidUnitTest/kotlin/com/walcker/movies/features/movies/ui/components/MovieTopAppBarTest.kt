package com.walcker.movies.features.movies.ui.components

import com.walcker.movies.utils.DefaultPaparazzi
import com.walcker.movies.utils.movieSnapshot
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.ArrowLeft
import org.junit.Rule
import kotlin.test.Test

internal class MovieTopAppBarTest {
    @get:Rule
    val paparazzi = DefaultPaparazzi

    @Test
    fun snapshot() {
        paparazzi.movieSnapshot {
            MovieTopAppBar(
                title = "Movie Detail",
                icon = FontAwesomeIcons.Solid.ArrowLeft,
            )
        }
    }
}