package com.walcker.movies.features.data.mapper

import com.walcker.movies.features.movies.features.data.models.GenreResponse
import com.walcker.movies.features.movies.features.data.mapper.GenreResponseMapper.toDomain
import com.walcker.movies.mockData.data.genreResponseTestData
import com.walcker.movies.mockData.domain.genre1TestData
import com.walcker.movies.mockData.domain.genre2TestData
import kotlin.test.Test
import kotlin.test.assertEquals

internal class GenreResponseMapperTest {

    @Test
    fun `given genre response when mapping to domain then should map all fields correctly`() {
        // Given
        val genreResponse = genreResponseTestData

        // When
        val result = genreResponse.toDomain()

        // Then
        assertEquals(genre1TestData, result)
    }

    @Test
    fun `given Adventure genre response when mapping to domain then should create correct domain object`() {
        // Given
        val genreResponse = GenreResponse(id = 2, name = "Adventure")

        // When
        val result = genreResponse.toDomain()

        // Then
        assertEquals(genre2TestData, result)
    }
}
