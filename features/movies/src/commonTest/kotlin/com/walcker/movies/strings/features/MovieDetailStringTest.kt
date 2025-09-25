package com.walcker.movies.strings.features

import com.walcker.movies.features.movies.strings.features.movieDetailStringsEn
import com.walcker.movies.features.movies.strings.features.movieDetailStringsPt
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

internal class MovieDetailStringTest {

    @Test
    fun `given movieDetailStringsPt when accessing properties then should return Portuguese strings`() {
        // Given & When
        val ptStrings = movieDetailStringsPt

        // Then
        assertNotNull(ptStrings)
        assertEquals("Detalhes", ptStrings.title)
        assertEquals("Assistir trailer", ptStrings.buttonText)
    }

    @Test
    fun `given movieDetailStringsEn when accessing properties then should return English strings`() {
        // Given & When
        val enStrings = movieDetailStringsEn

        // Then
        assertNotNull(enStrings)
        assertEquals("Details", enStrings.title)
        assertEquals("Watch trailer", enStrings.buttonText)
    }
}