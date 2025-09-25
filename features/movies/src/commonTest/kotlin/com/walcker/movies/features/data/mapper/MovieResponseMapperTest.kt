package com.walcker.movies.features.data.mapper

import com.walcker.movies.features.movies.features.data.mapper.MovieResponseMapper.toDomain
import com.walcker.movies.features.movies.features.data.network.HttpConfig
import com.walcker.movies.features.movies.features.domain.models.ImageSize
import com.walcker.movies.mockData.data.castMemberResponse1TestData
import com.walcker.movies.mockData.data.movieResponse2TestData
import com.walcker.movies.mockData.data.movieResponseTestData
import com.walcker.movies.mockData.domain.movieTestData
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull

internal class MovieResponseMapperTest {

    @Test
    fun `given movie response when mapping to domain then should map all fields correctly`() {
        // Given  // When
        val result = movieResponseTestData.toDomain()

        // Then
        assertEquals(movieTestData, result)
    }

    @Test
    fun `given movie response with different image size when mapping to domain then should use correct image size`() {
        // Given
        val movieResponse = movieResponseTestData
        val imageSize = ImageSize.LARGE

        // When
        val result = movieResponse.toDomain(imageSize = imageSize)

        // Then
        assertEquals("${HttpConfig.IMAGE_BASE_URL.value}/${ImageSize.LARGE.size}/${movieResponse.posterPath}", result.posterUrl)
    }

    @Test
    fun `given movie response with null runtime when mapping to domain then should handle null duration`() {
        // Given
        val movieResponse = movieResponseTestData.copy(runtime = null)

        // When
        val result = movieResponse.toDomain()

        // Then
        assertNull(result.duration)
    }

    @Test
    fun `given movie response with null genres when mapping to domain then should handle null genres`() {
        // Given
        val movieResponse = movieResponseTestData.copy(genres = null)

        // When
        val result = movieResponse.toDomain()

        // Then
        assertNull(result.genres)
    }

    @Test
    fun `given movie response with empty genres when mapping to domain then should handle empty genres`() {
        // Given
        val movieResponse = movieResponseTestData.copy(genres = emptyList())

        // When
        val result = movieResponse.toDomain()

        // Then
        assertNotNull(result.genres)
        assertEquals(0, result.genres?.size)
    }

    @Test
    fun `given movie response with 90 minutes runtime when mapping to domain then should format duration correctly`() {
        // Given
        val movieResponse = movieResponse2TestData

        // When
        val result = movieResponse.toDomain()

        // Then
        assertEquals("1h 30min", result.duration)
    }

    @Test
    fun `given movie response with 60 minutes runtime when mapping to domain then should format duration as hours only`() {
        // Given
        val movieResponse = movieResponseTestData.copy(runtime = 60)

        // When
        val result = movieResponse.toDomain()

        // Then
        assertEquals("1h ", result.duration)
    }

    @Test
    fun `given movie response with 45 minutes runtime when mapping to domain then should format duration as minutes only`() {
        // Given
        val movieResponse = movieResponseTestData.copy(runtime = 45)

        // When
        val result = movieResponse.toDomain()

        // Then
        assertEquals("45min", result.duration)
    }

    @Test
    fun `given movie response with 125 minutes runtime when mapping to domain then should format duration correctly`() {
        // Given
        val movieResponse = movieResponseTestData.copy(runtime = 125)

        // When
        val result = movieResponse.toDomain()

        // Then
        assertEquals("2h 5min", result.duration)
    }

    @Test
    fun `given movie response with vote average when mapping to domain then should format rating correctly`() {
        // Given
        val movieResponse = movieResponseTestData.copy(voteAverage = 7.89)

        // When
        val result = movieResponse.toDomain()

        // Then
        assertEquals("7.9", result.rating)
    }

    @Test
    fun `given movie response with more than 20 cast members when mapping to domain then should limit to 20 cast members`() {
        // Given
        val movieResponse = movieResponseTestData
        val castMembers = (1..25).map { index ->
            castMemberResponse1TestData.copy(
                id = index,
                name = "Actor $index",
                character = "Character $index"
            )
        }

        // When
        val result = movieResponse.toDomain(castMembersResponse = castMembers)

        // Then
        assertNotNull(result.castMembers)
        assertEquals(20, result.castMembers?.size)
    }
}
