package com.walcker.movies.handle

import com.walcker.movies.features.movies.handle.handleMessageError
import com.walcker.movies.features.movies.features.domain.models.exception.NetworkException
import kotlin.test.Test
import kotlin.test.assertEquals

internal class HandleErrorTest {

    @Test
    fun `given ApiException with status 300 when handleMessageError is called then should return redirect message`() {
        // Given
        val exception = NetworkException.ApiException("Redirection", 300)

        // When
        val result = handleMessageError(exception)

        // Then
        assertEquals("O recurso requisitado foi redirecionado.", result)
    }

    @Test
    fun `given ApiException with status 400 when handleMessageError is called then should return bad request message`() {
        // Given
        val exception = NetworkException.ApiException("Bad Request", 400)

        // When
        val result = handleMessageError(exception)

        // Then
        assertEquals("Requisição inválida. Confira os dados fornecidos.", result)
    }

    @Test
    fun `given ApiException with status 401 when handleMessageError is called then should return unauthorized message`() {
        // Given
        val exception = NetworkException.ApiException("Unauthorized", 401)

        // When
        val result = handleMessageError(exception)

        // Then
        assertEquals("Não autorizado. Por favor, faça login novamente.", result)
    }

    @Test
    fun `given ApiException with status 403 when handleMessageError is called then should return forbidden message`() {
        // Given
        val exception = NetworkException.ApiException("Forbidden", 403)

        // When
        val result = handleMessageError(exception)

        // Then
        assertEquals("Você não tem permissão para acessar este recurso.", result)
    }

    @Test
    fun `given ApiException with status 404 when handleMessageError is called then should return not found message`() {
        // Given
        val exception = NetworkException.ApiException("Not Found", 404)

        // When
        val result = handleMessageError(exception)

        // Then
        assertEquals("Recurso não encontrado. Tente novamente mais tarde.", result)
    }

    @Test
    fun `given ApiException with status 409 when handleMessageError is called then should return conflict message`() {
        // Given
        val exception = NetworkException.ApiException("Conflict", 409)

        // When
        val result = handleMessageError(exception)

        // Then
        assertEquals("Conflito de dados. Verifique possíveis duplicações.", result)
    }

    @Test
    fun `given ApiException with status 500 when handleMessageError is called then should return internal server error message`() {
        // Given
        val exception = NetworkException.ApiException("Internal Server Error", 500)

        // When
        val result = handleMessageError(exception)

        // Then
        assertEquals("Erro interno do servidor. Por favor, tente novamente mais tarde.", result)
    }

    @Test
    fun `given ApiException with status 502 when handleMessageError is called then should return bad gateway message`() {
        // Given
        val exception = NetworkException.ApiException("Bad Gateway", 502)

        // When
        val result = handleMessageError(exception)

        // Then
        assertEquals("Bad Gateway. O servidor recebeu uma resposta inválida.", result)
    }

    @Test
    fun `given ApiException with status 503 when handleMessageError is called then should return service unavailable message`() {
        // Given
        val exception = NetworkException.ApiException("Service Unavailable", 503)

        // When
        val result = handleMessageError(exception)

        // Then
        assertEquals("Serviço indisponível. O servidor está temporariamente fora do ar.", result)
    }

    @Test
    fun `given ApiException with status 504 when handleMessageError is called then should return gateway timeout message`() {
        // Given
        val exception = NetworkException.ApiException("Gateway Timeout", 504)

        // When
        val result = handleMessageError(exception)

        // Then
        assertEquals("Tempo de resposta excedido. Verifique sua conexão de internet.", result)
    }

    @Test
    fun `given ApiException with unknown status code when handleMessageError is called then should return generic error message`() {
        // Given
        val exception = NetworkException.ApiException("Unknown Error", 999)

        // When
        val result = handleMessageError(exception)

        // Then
        assertEquals("Um erro inesperado ocorreu. Tente novamente.", result)
    }

    @Test
    fun `given ApiException with status 422 when handleMessageError is called then should return generic error message`() {
        // Given
        val exception = NetworkException.ApiException("Unprocessable Entity", 422)

        // When
        val result = handleMessageError(exception)

        // Then
        assertEquals("Um erro inesperado ocorreu. Tente novamente.", result)
    }

    @Test
    fun `given NotFoundException when handleMessageError is called then should return generic network error message`() {
        // Given
        val exception = NetworkException.NotFoundException(message ="Resource not found")

        // When
        val result = handleMessageError(exception)

        // Then
        assertEquals("Algo deu errado. Tente novamente mais tarde.", result)
    }

    @Test
    fun `given ConflictException when handleMessageError is called then should return generic network error message`() {
        // Given
        val exception = NetworkException.ConflictException(message ="Resource conflict")

        // When
        val result = handleMessageError(exception)

        // Then
        assertEquals("Algo deu errado. Tente novamente mais tarde.", result)
    }

    @Test
    fun `given BadRequestException when handleMessageError is called then should return generic network error message`() {
        // Given
        val exception = NetworkException.BadRequestException(message ="Bad request")

        // When
        val result = handleMessageError(exception)

        // Then
        assertEquals("Algo deu errado. Tente novamente mais tarde.", result)
    }

    @Test
    fun `given UnauthorizedException when handleMessageError is called then should return generic network error message`() {
        // Given
        val exception = NetworkException.UnauthorizedException(message ="Unauthorized")

        // When
        val result = handleMessageError(exception)

        // Then
        assertEquals("Algo deu errado. Tente novamente mais tarde.", result)
    }

    @Test
    fun `given ServerErrorException when handleMessageError is called then should return generic network error message`() {
        // Given
        val exception = NetworkException.ServerErrorException(message ="Server error")

        // When
        val result = handleMessageError(exception)

        // Then
        assertEquals("Algo deu errado. Tente novamente mais tarde.", result)
    }

    @Test
    fun `given UnprocessableEntityException when handleMessageError is called then should return generic network error message`() {
        // Given
        val exception = NetworkException.UnprocessableEntityException(message ="Unprocessable entity")

        // When
        val result = handleMessageError(exception)

        // Then
        assertEquals("Algo deu errado. Tente novamente mais tarde.", result)
    }

    @Test
    fun `given generic Exception when handleMessageError is called then should return generic network error message`() {
        // Given
        val exception = RuntimeException("Generic runtime error")

        // When
        val result = handleMessageError(exception)

        // Then
        assertEquals("Algo deu errado. Tente novamente mais tarde.", result)
    }

    @Test
    fun `given IllegalArgumentException when handleMessageError is called then should return generic network error message`() {
        // Given
        val exception = IllegalArgumentException("Invalid argument")

        // When
        val result = handleMessageError(exception)

        // Then
        assertEquals("Algo deu errado. Tente novamente mais tarde.", result)
    }

    @Test
    fun `given multiple ApiException status codes when handleMessageError is called then should return correct messages`() {
        // Given
        val testCases = mapOf(
            300 to "O recurso requisitado foi redirecionado.",
            400 to "Requisição inválida. Confira os dados fornecidos.",
            401 to "Não autorizado. Por favor, faça login novamente.",
            403 to "Você não tem permissão para acessar este recurso.",
            404 to "Recurso não encontrado. Tente novamente mais tarde.",
            409 to "Conflito de dados. Verifique possíveis duplicações.",
            500 to "Erro interno do servidor. Por favor, tente novamente mais tarde.",
            502 to "Bad Gateway. O servidor recebeu uma resposta inválida.",
            503 to "Serviço indisponível. O servidor está temporariamente fora do ar.",
            504 to "Tempo de resposta excedido. Verifique sua conexão de internet."
        )

        // When & Then
        testCases.forEach { (statusCode, expectedMessage) ->
            val exception = NetworkException.ApiException("Test message", statusCode)
            val result = handleMessageError(exception)
            assertEquals(expectedMessage, result)
        }
    }

    @Test
    fun `given different NetworkException types when handleMessageError is called then should return generic network error message`() {
        // Given
        val networkExceptions = listOf(
            NetworkException.NotFoundException(message ="Not found"),
            NetworkException.ConflictException(message ="Conflict"),
            NetworkException.BadRequestException(message ="Bad request"),
            NetworkException.UnauthorizedException(message ="Unauthorized"),
            NetworkException.ServerErrorException(message ="Server error"),
            NetworkException.UnprocessableEntityException(message ="Unprocessable entity")
        )

        // When & Then
        networkExceptions.forEach { exception ->
            val result = handleMessageError(exception)
            assertEquals("Algo deu errado. Tente novamente mais tarde.", result)
        }
    }

    @Test
    fun `given different non-NetworkException types when handleMessageError is called then should return generic network error message`() {
        // Given
        val nonNetworkExceptions = listOf(
            RuntimeException("Runtime error"),
            IllegalArgumentException("Invalid argument"),
            NullPointerException("Null pointer"),
            Exception("Generic exception")
        )

        // When & Then
        nonNetworkExceptions.forEach { exception ->
            val result = handleMessageError(exception)
            assertEquals("Algo deu errado. Tente novamente mais tarde.", result)
        }
    }

    @Test
    fun `given ApiException with different response messages when handleMessageError is called then should return message based on status code only`() {
        // Given
        val exception1 = NetworkException.ApiException("Custom message 1", 400)
        val exception2 = NetworkException.ApiException("Custom message 2", 400)
        val exception3 = NetworkException.ApiException("", 400)

        // When
        val result1 = handleMessageError(exception1)
        val result2 = handleMessageError(exception2)
        val result3 = handleMessageError(exception3)

        // Then
        assertEquals("Requisição inválida. Confira os dados fornecidos.", result1)
        assertEquals("Requisição inválida. Confira os dados fornecidos.", result2)
        assertEquals("Requisição inválida. Confira os dados fornecidos.", result3)
    }
}
