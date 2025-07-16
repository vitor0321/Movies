package com.walcker.movies.strings.features

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
        assertEquals("Filmes Populares", ptStrings.popularMovies)
        assertEquals("Filmes Mais Bem Avaliados", ptStrings.topRatedMovies)
        assertEquals("Filmes em Breve", ptStrings.upcomingMovies)
    }

    @Test
    fun `given moviesListStringsEn when accessing properties then should return English strings`() {
        // Given & When
        val enStrings = moviesListStringsEn

        // Then
        assertNotNull(enStrings)
        assertEquals("Movies", enStrings.appName)
        assertEquals("Popular Movies", enStrings.popularMovies)
        assertEquals("Top Rated Movies", enStrings.topRatedMovies)
        assertEquals("Upcoming Movies", enStrings.upcomingMovies)
    }
}