package com.walcker.movies.strings.features

import com.walcker.movies.features.movies.strings.features.moviesListStringsEn
import com.walcker.movies.features.movies.strings.features.moviesListStringsPt
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

internal class MoviesListStringsTest {

    @Test
    fun `given moviesListStringsPt when accessing properties then should return Portuguese strings`() {
        // Given & When
        val ptStrings = moviesListStringsPt

        // Then
        assertNotNull(ptStrings)
        assertEquals("Filmes", ptStrings.appName)
        assertEquals("Populares", ptStrings.popularMovies)
        assertEquals("Mais Bem Avaliados", ptStrings.topRatedMovies)
        assertEquals("Em Breve", ptStrings.upcomingMovies)
    }

    @Test
    fun `given moviesListStringsEn when accessing properties then should return English strings`() {
        // Given & When
        val enStrings = moviesListStringsEn

        // Then
        assertNotNull(enStrings)
        assertEquals("Movies", enStrings.appName)
        assertEquals("Popular", enStrings.popularMovies)
        assertEquals("Top Rated", enStrings.topRatedMovies)
        assertEquals("Upcoming", enStrings.upcomingMovies)
    }
}