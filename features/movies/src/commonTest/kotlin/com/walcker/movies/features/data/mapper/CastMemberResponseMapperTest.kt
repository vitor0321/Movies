package com.walcker.movies.features.data.mapper

import com.walcker.movies.features.movies.features.data.mapper.CastMemberResponseMapper.toDomain
import com.walcker.movies.mockData.data.castMemberResponse1TestData
import com.walcker.movies.mockData.data.castMemberResponse2TestData
import com.walcker.movies.mockData.domain.castMember1TestData
import com.walcker.movies.mockData.domain.castMember2TestData
import kotlin.test.Test
import kotlin.test.assertEquals

internal class CastMemberResponseMapperTest {

    @Test
    fun `given cast member response when mapping to domain then should map all fields correctly`() {
        // Given
        val castMemberResponse = castMemberResponse1TestData

        // When
        val result = castMemberResponse.toDomain()

        // Then
        assertEquals(castMember1TestData, result)
    }

    @Test
    fun `given multiple cast member responses when mapping to domain then should create correct domain objects`() {
        // Given
        val castMemberResponses = listOf(
            castMemberResponse1TestData,
            castMemberResponse2TestData
        )

        // When
        val results = castMemberResponses.map { it.toDomain() }

        // Then
        assertEquals(2, results.size)

        // Verify first cast member
        val firstResult = results[0]
        assertEquals(castMember1TestData, firstResult)

        // Verify second cast member
        val secondResult = results[1]
        assertEquals(castMember2TestData, secondResult)
    }
}