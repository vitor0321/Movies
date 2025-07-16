package com.walcker.movies.strings.features

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
        assertEquals("Detalhes do filme", ptStrings.title)
        assertEquals("Assistir trailer", ptStrings.buttonText)
    }

    @Test
    fun `given movieDetailStringsEn when accessing properties then should return English strings`() {
        // Given & When
        val enStrings = movieDetailStringsEn

        // Then
        assertNotNull(enStrings)
        assertEquals("Movie details", enStrings.title)
        assertEquals("Watch trailer", enStrings.buttonText)
    }
}