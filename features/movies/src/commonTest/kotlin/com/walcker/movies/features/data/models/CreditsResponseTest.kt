package com.walcker.movies.features.data.models

import com.walcker.movies.features.movies.features.data.models.CreditsResponse
import com.walcker.movies.mockData.data.creditResponseJsonTestData
import com.walcker.movies.mockData.data.creditsResponseTestData
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertEquals

internal class CreditsResponseTest {

    private val json = Json { ignoreUnknownKeys = true }

    @Test
    fun `given json string when deserializing CreditsResponse then should parse correctly`() {
        // Given
        val jsonString = creditResponseJsonTestData

        // When
        val result = json.decodeFromString<CreditsResponse>(jsonString)

        // Then
        assertEquals(creditsResponseTestData, result)
    }

    @Test
    fun `given CreditsResponse object when serializing then should produce correct json`() {
        // Given
        val creditsResponse = creditsResponseTestData

        // When
        val result = json.encodeToString(CreditsResponse.serializer(), creditsResponse)

        // Then
        assertEquals(creditResponseJsonTestData, result)
    }

    @Test
    fun `given json with null profile_path when deserializing then should handle null values correctly`() {
        // Given
        val jsonWithNullProfilePath = """
            {
              "id": 550,
              "cast": [
                {
                  "id": 287,
                  "known_for_department": "Acting",
                  "name": "Brad Pitt",
                  "character": "Tyler Durden",
                  "profile_path": null
                }
              ]
            }
        """.trimIndent()

        // When
        val result = json.decodeFromString<CreditsResponse>(jsonWithNullProfilePath)

        // Then
        assertEquals(550, result.id)
        assertEquals(1, result.cast.size)
        assertEquals(287, result.cast[0].id)
        assertEquals("Acting", result.cast[0].department)
        assertEquals("Brad Pitt", result.cast[0].name)
        assertEquals("Tyler Durden", result.cast[0].character)
        assertEquals(null, result.cast[0].profilePictureUrl)
    }

    @Test
    fun `given json with empty cast array when deserializing then should handle empty list correctly`() {
        // Given
        val jsonWithEmptyCast = """
            {
              "id": 550,
              "cast": []
            }
        """.trimIndent()

        // When
        val result = json.decodeFromString<CreditsResponse>(jsonWithEmptyCast)

        // Then
        assertEquals(550, result.id)
        assertEquals(0, result.cast.size)
    }

    @Test
    fun `given CreditsResponse with empty cast when serializing then should produce valid json`() {
        // Given
        val creditsResponse = CreditsResponse(
            id = 550,
            cast = emptyList()
        )

        // When
        val result = json.encodeToString(CreditsResponse.serializer(), creditsResponse)

        // Then
        val parsedResult = json.decodeFromString<CreditsResponse>(result)
        assertEquals(550, parsedResult.id)
        assertEquals(0, parsedResult.cast.size)
    }
}