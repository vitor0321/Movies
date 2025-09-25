package com.walcker.movies.features.movies.ui.components

import androidx.compose.foundation.layout.width
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.walcker.movies.features.movies.features.ui.preview.mockData.movieTestData
import com.walcker.movies.utils.DefaultPaparazzi
import com.walcker.movies.utils.movieSnapshot
import kotlinx.collections.immutable.persistentListOf
import org.junit.Rule
import kotlin.test.Test

internal class MovieRowListTest {
    @get:Rule
    val paparazzi = DefaultPaparazzi

    @Test
    fun snapshot() {
        paparazzi.movieSnapshot {
            MovieRowList(
                items = persistentListOf(
                    movieTestData,
                    movieTestData,
                    movieTestData
                )
            ) { castMember, width ->
                MovieCastMemberItem(
                    modifier = Modifier.width(width.dp),
                    imageUrl = castMember.posterUrl,
                    name = castMember.title,
                    character = castMember.overview
                )
            }
        }
    }
}