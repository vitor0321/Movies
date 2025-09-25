package com.walcker.movies.features.domain.mapper

import com.walcker.movies.features.movies.features.domain.mapper.ExceptionMapper.toExceptionPresentation
import com.walcker.movies.features.movies.features.domain.models.exception.ExceptionPresentation
import com.walcker.movies.features.movies.features.domain.models.exception.NetworkException
import kotlin.test.Test
import kotlin.test.assertEquals

internal class ExceptionMapperTest {

    @Test
    fun `given NotFoundException when mapping to presentation then should return NotFound`() {
        // Given
        val exception = NetworkException.NotFoundException(message = "Resource not found")

        // When
        val result = exception.toExceptionPresentation()

        // Then
        assertEquals(ExceptionPresentation.NotFound, result)
    }

    @Test
    fun `given ConflictException when mapping to presentation then should return Conflict`() {
        // Given
        val exception = NetworkException.ConflictException(message ="Resource conflict")

        // When
        val result = exception.toExceptionPresentation()

        // Then
        assertEquals(ExceptionPresentation.Conflict, result)
    }

    @Test
    fun `given BadRequestException when mapping to presentation then should return BadRequest`() {
        // Given
        val exception = NetworkException.BadRequestException(message ="Bad request")

        // When
        val result = exception.toExceptionPresentation()

        // Then
        assertEquals(ExceptionPresentation.BadRequest, result)
    }

    @Test
    fun `given UnauthorizedException when mapping to presentation then should return Unauthorized`() {
        // Given
        val exception = NetworkException.UnauthorizedException(message ="Unauthorized access")

        // When
        val result = exception.toExceptionPresentation()

        // Then
        assertEquals(ExceptionPresentation.Unauthorized, result)
    }

    @Test
    fun `given ServerErrorException when mapping to presentation then should return ServerError`() {
        // Given
        val exception = NetworkException.ServerErrorException(message ="Internal server error")

        // When
        val result = exception.toExceptionPresentation()

        // Then
        assertEquals(ExceptionPresentation.ServerError, result)
    }

    @Test
    fun `given UnprocessableEntityException when mapping to presentation then should return UnprocessableEntity`() {
        // Given
        val exception = NetworkException.UnprocessableEntityException(message ="Unprocessable entity")

        // When
        val result = exception.toExceptionPresentation()

        // Then
        assertEquals(ExceptionPresentation.UnprocessableEntity, result)
    }

    @Test
    fun `given different NetworkException messages when mapping to presentation then should return correct presentation type`() {
        // Given
        val notFoundWithDifferentMessage = NetworkException.NotFoundException(message ="Movie not found")
        val badRequestWithDifferentMessage = NetworkException.BadRequestException(message ="Invalid parameter")
        val serverErrorWithDifferentMessage = NetworkException.ServerErrorException(message ="Database connection failed")

        // When
        val notFoundResult = notFoundWithDifferentMessage.toExceptionPresentation()
        val badRequestResult = badRequestWithDifferentMessage.toExceptionPresentation()
        val serverErrorResult = serverErrorWithDifferentMessage.toExceptionPresentation()

        // Then
        assertEquals(ExceptionPresentation.NotFound, notFoundResult)
        assertEquals(ExceptionPresentation.BadRequest, badRequestResult)
        assertEquals(ExceptionPresentation.ServerError, serverErrorResult)
    }

    @Test
    fun `given multiple exception types when mapping to presentation then should maintain correct mapping`() {
        // Given
        val exceptions = listOf(
            NetworkException.NotFoundException(message ="Not found") to ExceptionPresentation.NotFound,
            NetworkException.ConflictException(message ="Conflict") to ExceptionPresentation.Conflict,
            NetworkException.BadRequestException(message ="Bad request") to ExceptionPresentation.BadRequest,
            NetworkException.UnauthorizedException(message ="Unauthorized") to ExceptionPresentation.Unauthorized,
            NetworkException.ServerErrorException(message ="Server error") to ExceptionPresentation.ServerError,
            NetworkException.UnprocessableEntityException(message ="Unprocessable") to ExceptionPresentation.UnprocessableEntity
        )

        // When & Then
        exceptions.forEach { (exception, expectedPresentation) ->
            val result = exception.toExceptionPresentation()
            assertEquals(expectedPresentation, result)
        }
    }

    @Test
    fun `given NetworkException with empty message when mapping to presentation then should return correct presentation type`() {
        // Given
        val notFoundWithEmptyMessage = NetworkException.NotFoundException(message ="")
        val unauthorizedWithEmptyMessage = NetworkException.UnauthorizedException(message ="")

        // When
        val notFoundResult = notFoundWithEmptyMessage.toExceptionPresentation()
        val unauthorizedResult = unauthorizedWithEmptyMessage.toExceptionPresentation()

        // Then
        assertEquals(ExceptionPresentation.NotFound, notFoundResult)
        assertEquals(ExceptionPresentation.Unauthorized, unauthorizedResult)
    }

    @Test
    fun `given NetworkException with null message when mapping to presentation then should return correct presentation type`() {
        // Given
        val conflictWithNullMessage = NetworkException.ConflictException(null)
        val serverErrorWithNullMessage = NetworkException.ServerErrorException(null)

        // When
        val conflictResult = conflictWithNullMessage.toExceptionPresentation()
        val serverErrorResult = serverErrorWithNullMessage.toExceptionPresentation()

        // Then
        assertEquals(ExceptionPresentation.Conflict, conflictResult)
        assertEquals(ExceptionPresentation.ServerError, serverErrorResult)
    }
}