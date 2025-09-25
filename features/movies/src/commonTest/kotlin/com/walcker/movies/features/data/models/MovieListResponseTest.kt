package com.walcker.movies.features.data.models

import com.walcker.movies.features.movies.features.data.models.MovieListResponse
import com.walcker.movies.mockData.data.movieListResponseTestData
import com.walcker.movies.mockData.data.movieResponseJsonTestData
import com.walcker.movies.mockData.data.movieResponseTestData
import kotlinx.datetime.LocalDate
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

internal class MovieListResponseTest {

    private val json = Json { ignoreUnknownKeys = true }

    @Test
    fun `given json string when deserializing MovieListResponse then should parse correctly`() {
        // Given // When
        val result = json.decodeFromString<MovieListResponse>(movieResponseJsonTestData)

        // Then
        assertEquals(movieListResponseTestData, result)
    }

    @Test
    fun `given MovieListResponse object when serializing then should produce correct json`() {
        // Given // When
        val result = json.encodeToString(MovieListResponse.serializer(), movieListResponseTestData)

        // Then
        assertEquals(movieResponseJsonTestData, result)
    }

    @Test
    fun `given json with empty results when deserializing then should handle empty list correctly`() {
        // Given
        val jsonWithEmptyResults = """
            {
              "results": []
            }
        """.trimIndent()

        // When
        val result = json.decodeFromString<MovieListResponse>(jsonWithEmptyResults)

        // Then
        assertNotNull(result)
        assertEquals(0, result.results.size)
        assertTrue(result.results.isEmpty())
    }

    @Test
    fun `given json with movie without genres when deserializing then should handle null genres correctly`() {
        // Given
        val jsonWithoutGenres = """
            {
              "results": [
                {
                  "id": 1,
                  "title": "Test Movie",
                  "overview": "Test Overview",
                  "poster_path": "/test.jpg",
                  "release_date": "2024-01-01",
                  "vote_average": 8.5
                }
              ]
            }
        """.trimIndent()

        // When
        val result = json.decodeFromString<MovieListResponse>(jsonWithoutGenres)

        // Then
        assertNotNull(result)
        assertEquals(1, result.results.size)
        val movie = result.results[0]
        assertEquals(1, movie.id)
        assertEquals("Test Movie", movie.title)
        assertEquals("Test Overview", movie.overview)
        assertEquals("/test.jpg", movie.posterPath)
        assertEquals(LocalDate(2024, 1, 1), movie.releaseDate)
        assertEquals(null, movie.runtime)
        assertEquals(8.5, movie.voteAverage)
        assertEquals(null, movie.genres)
    }

    @Test
    fun `given json with movie without runtime when deserializing then should handle null runtime correctly`() {
        // Given
        val jsonWithoutRuntime = """
            {
              "results": [
                {
                  "id": 1,
                  "title": "Test Movie",
                  "overview": "Test Overview",
                  "poster_path": "/test.jpg",
                  "genres": [
                    {
                      "id": 1,
                      "name": "Action"
                    }
                  ],
                  "release_date": "2024-01-01",
                  "vote_average": 8.5
                }
              ]
            }
        """.trimIndent()

        // When
        val result = json.decodeFromString<MovieListResponse>(jsonWithoutRuntime)

        // Then
        assertNotNull(result)
        assertEquals(1, result.results.size)
        val movie = result.results[0]
        assertEquals(1, movie.id)
        assertEquals("Test Movie", movie.title)
        assertEquals("Test Overview", movie.overview)
        assertEquals("/test.jpg", movie.posterPath)
        assertEquals(LocalDate(2024, 1, 1), movie.releaseDate)
        assertEquals(null, movie.runtime)
        assertEquals(8.5, movie.voteAverage)
        assertNotNull(movie.genres)
        assertEquals(1, movie.genres?.size)
        assertEquals("Action", movie.genres?.get(0)?.name)
    }

    @Test
    fun `given MovieListResponse with single movie when serializing then should produce valid json`() {
        // Given
        val movieListResponse = MovieListResponse(
            results = listOf(movieResponseTestData)
        )

        // When
        val result = json.encodeToString(MovieListResponse.serializer(), movieListResponse)

        // Then
        val parsedResult = json.decodeFromString<MovieListResponse>(result)
        assertEquals(1, parsedResult.results.size)

        val movie = parsedResult.results[0]
        assertEquals(movieResponseTestData.id, movie.id)
        assertEquals(movieResponseTestData.title, movie.title)
        assertEquals(movieResponseTestData.overview, movie.overview)
        assertEquals(movieResponseTestData.posterPath, movie.posterPath)
        assertEquals(movieResponseTestData.releaseDate, movie.releaseDate)
        assertEquals(movieResponseTestData.runtime, movie.runtime)
        assertEquals(movieResponseTestData.voteAverage, movie.voteAverage)
    }

    @Test
    fun `given MovieListResponse with empty results when serializing then should produce valid json`() {
        // Given
        val movieListResponse = MovieListResponse(results = emptyList())

        // When
        val result = json.encodeToString(MovieListResponse.serializer(), movieListResponse)

        // Then
        val parsedResult = json.decodeFromString<MovieListResponse>(result)
        assertEquals(0, parsedResult.results.size)
        assertTrue(parsedResult.results.isEmpty())
    }
}