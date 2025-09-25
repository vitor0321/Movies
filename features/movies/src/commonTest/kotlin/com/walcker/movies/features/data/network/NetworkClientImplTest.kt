package com.walcker.movies.features.data.network

import com.walcker.movies.features.movies.features.data.models.CreditsResponse
import com.walcker.movies.mockFakes.FakeNetworkClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

internal class NetworkClientImplTest {

    @Test
    fun `given mock network client when making request then should return mocked response`() = runTest {
        // Given
        val mockJson = """{"message": "Mock response"}"""
        val networkClient = FakeNetworkClient.createMockNetworkClient()

        // When
        val client = networkClient.httpClient()
        val response = client.get("https://api.example.com/test").body<String>()

        // Then
        assertNotNull(response)
        assertEquals(mockJson, response)
    }

    @Test
    fun `given mock network client with credits when requesting credits then should return credits json`() = runTest {
        // Given
        val creditsJson = """
            {
              "id": 550,
              "cast": [
                {
                  "id": 287,
                  "known_for_department": "Acting",
                  "name": "Brad Pitt",
                  "character": "Tyler Durden",
                  "profile_path": "/cckcYc2v0yh1tc9QjRelptcOBko.jpg"
                }
              ]
            }
        """.trimIndent()

        val networkClient = FakeNetworkClient.createMockNetworkClientWithCredits(creditsJson)

        // When
        val client = networkClient.httpClient()
        val response = client.get("https://api.themoviedb.org/3/movie/550/credits").body<CreditsResponse>()

        // Then
        assertNotNull(response)
        assertEquals(550, response.id)
        assertEquals(1, response.cast.size)
        assertEquals("Brad Pitt", response.cast[0].name)
        assertEquals("Tyler Durden", response.cast[0].character)
    }

    @Test
    fun `given mock network client with error when making request then should return error response`() = runTest {
        // Given
        val networkClient = FakeNetworkClient.createMockNetworkClientWithError()

        // When
        val client = networkClient.httpClient()
        val response = client.get("https://api.example.com/error").body<String>()

        // Then
        assertNotNull(response)
        assertEquals("""{"error": "Mock error response"}""", response)
    }

    @Test
    fun `given mock network client with specific endpoints when requesting different urls then should return corresponding responses`() = runTest {
        // Given
        val mockResponses = mapOf(
            "popular" to """{"results": [{"title": "Popular Movie"}]}""",
            "top_rated" to """{"results": [{"title": "Top Rated Movie"}]}""",
            "upcoming" to """{"results": [{"title": "Upcoming Movie"}]}"""
        )

        val networkClient = FakeNetworkClient.createMockNetworkClient(mockResponses)
        val client = networkClient.httpClient()

        // When & Then
        val popularResponse = client.get("https://api.themoviedb.org/3/movie/popular").body<String>()
        assertEquals("""{"results": [{"title": "Popular Movie"}]}""", popularResponse)

        val topRatedResponse = client.get("https://api.themoviedb.org/3/movie/top_rated").body<String>()
        assertEquals("""{"results": [{"title": "Top Rated Movie"}]}""", topRatedResponse)

        val upcomingResponse = client.get("https://api.themoviedb.org/3/movie/upcoming").body<String>()
        assertEquals("""{"results": [{"title": "Upcoming Movie"}]}""", upcomingResponse)
    }
}
