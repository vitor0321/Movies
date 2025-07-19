package com.walcker.movies.features.ui.components

import com.walcker.movies.utils.DefaultPaparazzi
import com.walcker.movies.utils.movieSnapshot
import org.junit.Rule
import kotlin.test.Test

internal class MovieCastMemberItemTest {
    @get:Rule
    val paparazzi = DefaultPaparazzi

    @Test
    fun snapshot() {
        paparazzi.movieSnapshot {
            MovieCastMemberItem(
                imageUrl = "https://example.com/profile.jpg",
                name = "John Doe",
                character = "Main Character"
            )
        }
    }
}