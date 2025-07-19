package com.walcker.movies.features.ui.components

import app.cash.paparazzi.Paparazzi
import org.junit.Rule
import kotlin.test.Test

class MovieBadgeTest {
    @get:Rule
    val paparazzi = Paparazzi()

    @Test
    fun simpleTextSnapshot() {
        paparazzi.snapshot {
            MovieBadge(text = "Badge")
        }
    }
}