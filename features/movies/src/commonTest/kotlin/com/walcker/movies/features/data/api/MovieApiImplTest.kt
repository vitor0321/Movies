package com.walcker.movies.features.data.api

import com.walcker.movies.features.movies.features.data.api.MovieApiImpl
import com.walcker.movies.features.movies.features.domain.models.MovieSection
import com.walcker.movies.mockData.data.creditResponseJsonTestData
import com.walcker.movies.mockData.data.creditsResponseTestData
import com.walcker.movies.mockData.data.movieResponse2TestData
import com.walcker.movies.mockData.data.movieResponseJsonTestData
import com.walcker.movies.mockData.data.movieResponseTestData
import com.walcker.movies.mockFakes.FakeNetworkClient
import com.walcker.movies.mockFakes.FakePlatform
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

internal class MovieApiImplTest {

    private val platform = FakePlatform.createMockPlatform()

    @Test
    fun `given network client and platform when MovieApiImpl is instantiated then should create instance successfully`() {
        // Given
        val networkClient = FakeNetworkClient.createMockNetworkClient()

        // When
        val movieApi = MovieApiImpl(networkClient, platform)

        // Then
        assertNotNull(movieApi)
    }

    @Test
    fun `given popular movies section when getMovies is called then should return movie list response`() = runTest {
        // Given
        val networkClient = FakeNetworkClient.createMockNetworkClient(
            mockResponses = mapOf(
                "popular" to movieResponseJsonTestData
            )
        )
        val movieApi = MovieApiImpl(networkClient, platform)

        // When
        val result = movieApi.getMovies(MovieSection.SectionType.POPULAR, 1)

        // Then
        assertNotNull(result)
        assertEquals(2, result.results.size)
        assertEquals(movieResponseTestData.id, result.results[0].id)
        assertEquals(movieResponseTestData.title, result.results[0].title)
        assertEquals(movieResponseTestData.overview, result.results[0].overview)
        assertEquals(movieResponseTestData.posterPath, result.results[0].posterPath)
        assertEquals(movieResponseTestData.runtime, result.results[0].runtime)
        assertEquals(movieResponseTestData.voteAverage, result.results[0].voteAverage)
    }

    @Test
    fun `given top rated movies section when getMovies is called then should return movie list response`() = runTest {
        // Given
        val networkClient = FakeNetworkClient.createMockNetworkClient(
            mockResponses = mapOf(
                "top_rated" to movieResponseJsonTestData
            )
        )
        val movieApi = MovieApiImpl(networkClient, platform)

        // When
        val result = movieApi.getMovies(MovieSection.SectionType.TOP_RATED, 1)

        // Then
        assertNotNull(result)
        assertEquals(2, result.results.size)
        assertEquals(movieResponse2TestData.id, result.results[1].id)
        assertEquals(movieResponse2TestData.title, result.results[1].title)
        assertEquals(movieResponse2TestData.overview, result.results[1].overview)
        assertEquals(movieResponse2TestData.posterPath, result.results[1].posterPath)
        assertEquals(movieResponse2TestData.runtime, result.results[1].runtime)
        assertEquals(movieResponse2TestData.voteAverage, result.results[1].voteAverage)
    }

    @Test
    fun `given upcoming movies section when getMovies is called then should return movie list response`() = runTest {
        // Given
        val networkClient = FakeNetworkClient.createMockNetworkClient(
            mockResponses = mapOf(
                "upcoming" to movieResponseJsonTestData
            )
        )
        val movieApi = MovieApiImpl(networkClient, platform)

        // When
        val result = movieApi.getMovies(MovieSection.SectionType.UPCOMING, 1)

        // Then
        assertNotNull(result)
        assertEquals(2, result.results.size)
        assertEquals("Test Movie", result.results[0].title)
        assertEquals("Movie 2", result.results[1].title)
    }

    @Test
    fun `given movie id when getMovieDetail is called then should return movie response`() = runTest {
        // Given
        val movieId = 1
        val movieDetailJson = """
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
                "runtime": 120,
                "vote_average": 8.5
            }
        """.trimIndent()

        val networkClient = FakeNetworkClient.createMockNetworkClient(
            mockResponses = mapOf(
                "movie" to movieDetailJson
            )
        )
        val movieApi = MovieApiImpl(networkClient, platform)

        // When
        val result = movieApi.getMovieDetail(movieId)

        // Then
        assertNotNull(result)
        assertEquals(movieResponseTestData.id, result.id)
        assertEquals(movieResponseTestData.title, result.title)
        assertEquals(movieResponseTestData.overview, result.overview)
        assertEquals(movieResponseTestData.posterPath, result.posterPath)
        assertEquals(movieResponseTestData.releaseDate, result.releaseDate)
        assertEquals(movieResponseTestData.runtime, result.runtime)
        assertEquals(movieResponseTestData.voteAverage, result.voteAverage)
        assertNotNull(result.genres)
        assertEquals(1, result.genres?.size)
        assertEquals("Action", result.genres?.get(0)?.name)
    }

    @Test
    fun `given movie id when getCredits is called then should return credits response`() = runTest {
        // Given
        val movieId = 550
        val networkClient = FakeNetworkClient.createMockNetworkClient(
            mockResponses = mapOf(
                "credits" to creditResponseJsonTestData
            )
        )
        val movieApi = MovieApiImpl(networkClient, platform)

        // When
        val result = movieApi.getCredits(movieId)

        // Then
        assertNotNull(result)
        assertEquals(creditsResponseTestData.id, result.id)
        assertEquals(creditsResponseTestData.cast.size, result.cast.size)

        // Verify first cast member
        val firstCast = result.cast[0]
        assertEquals(287, firstCast.id)
        assertEquals("Acting", firstCast.department)
        assertEquals("Brad Pitt", firstCast.name)
        assertEquals("Tyler Durden", firstCast.character)
        assertEquals("/cckcYc2v0yh1tc9QjRelptcOBko.jpg", firstCast.profilePictureUrl)

        // Verify second cast member
        val secondCast = result.cast[1]
        assertEquals(819, secondCast.id)
        assertEquals("Acting", secondCast.department)
        assertEquals("Edward Norton", secondCast.name)
        assertEquals("The Narrator", secondCast.character)
        assertEquals("/5XBzD5WuTyVQZeS4VI25z2moMeY.jpg", secondCast.profilePictureUrl)
    }

    @Test
    fun `given empty movie list when getMovies is called then should return empty results`() = runTest {
        // Given
        val emptyMovieListJson = """
            {
                "results": []
            }
        """.trimIndent()

        val networkClient = FakeNetworkClient.createMockNetworkClient(
            mockResponses = mapOf(
                "popular" to emptyMovieListJson
            )
        )
        val movieApi = MovieApiImpl(networkClient, platform)

        // When
        val result = movieApi.getMovies(MovieSection.SectionType.POPULAR, 1)

        // Then
        assertNotNull(result)
        assertEquals(0, result.results.size)
    }

    @Test
    fun `given movie with no cast when getCredits is called then should return empty cast list`() = runTest {
        // Given
        val movieId = 550
        val emptyCastJson = """
            {
                "id": 550,
                "cast": []
            }
        """.trimIndent()

        val networkClient = FakeNetworkClient.createMockNetworkClient(
            mockResponses = mapOf(
                "credits" to emptyCastJson
            )
        )
        val movieApi = MovieApiImpl(networkClient, platform)

        // When
        val result = movieApi.getCredits(movieId)

        // Then
        assertNotNull(result)
        assertEquals(550, result.id)
        assertEquals(0, result.cast.size)
    }

    @Test
    fun `given movie detail with null runtime when getMovieDetail is called then should handle null runtime correctly`() = runTest {
        // Given
        val movieId = 1
        val movieDetailWithNullRuntime = """
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
        """.trimIndent()

        val networkClient = FakeNetworkClient.createMockNetworkClient(
            mockResponses = mapOf(
                "movie" to movieDetailWithNullRuntime
            )
        )
        val movieApi = MovieApiImpl(networkClient, platform)

        // When
        val result = movieApi.getMovieDetail(movieId)

        // Then
        assertNotNull(result)
        assertEquals(1, result.id)
        assertEquals("Test Movie", result.title)
        assertEquals("Test Overview", result.overview)
        assertEquals("/test.jpg", result.posterPath)
        assertEquals(null, result.runtime)
        assertEquals(8.5, result.voteAverage)
        assertNotNull(result.genres)
        assertEquals(1, result.genres?.size)
        assertEquals("Action", result.genres?.get(0)?.name)
    }

    @Test
    fun `given cast member with null profile path when getCredits is called then should handle null profile path correctly`() = runTest {
        // Given
        val movieId = 550
        val castWithNullProfilePath = """
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

        val networkClient = FakeNetworkClient.createMockNetworkClient(
            mockResponses = mapOf(
                "credits" to castWithNullProfilePath
            )
        )
        val movieApi = MovieApiImpl(networkClient, platform)

        // When
        val result = movieApi.getCredits(movieId)

        // Then
        assertNotNull(result)
        assertEquals(550, result.id)
        assertEquals(1, result.cast.size)

        val cast = result.cast[0]
        assertEquals(287, cast.id)
        assertEquals("Acting", cast.department)
        assertEquals("Brad Pitt", cast.name)
        assertEquals("Tyler Durden", cast.character)
        assertEquals(null, cast.profilePictureUrl)
    }
}
