package com.walcker.movies.features.movies.features.data.network

import com.walcker.movies.features.movies.features.domain.models.exception.NetworkException
import com.walcker.movies.features.movies.features.domain.network.NetworkClient
import com.walcker.movies.core.platformImpl
import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.HttpResponseValidator
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.client.request.HttpRequest
import io.ktor.client.statement.bodyAsText
import io.ktor.client.statement.request
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

internal class NetworkClientImpl : NetworkClient {

    private val accessToken: String =
        try {
            platformImpl().accessToken
        } catch (e: IllegalStateException) {
            Logger.SIMPLE.log("Error getting access token: ${e.message}")
            ""
        }

    override suspend fun httpClient(): HttpClient =
        HttpClient {
            expectSuccess = true

            // Configuração padrão para todas as solicitações
            defaultRequest {
                url(urlString = HttpConfig.BASE_URL.value)
                contentType(type = ContentType.Application.Json)
            }

            // Validadores e handlers
            HttpResponseValidator {
                handleResponseExceptionWithRequest { cause, request ->
                    handleClientRequestException(cause, request)
                }
                handleResponseException { cause, request ->
                    handleClientRequestException(cause, request)
                }
            }

            configureContentNegotiation()
            configureAuth()
            configureLogging()
            configureTimeouts()
        }

    private fun HttpClientConfig<*>.configureContentNegotiation() {
        install(plugin = ContentNegotiation) {
            json(
                Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                }
            )
        }
    }

    private fun HttpClientConfig<*>.configureAuth() {
        install(plugin = Auth) {
            bearer {
                loadTokens {
                    BearerTokens(
                        accessToken = accessToken,
                        refreshToken = "",
                    )
                }
            }
        }
    }

    private fun HttpClientConfig<*>.configureLogging() {
        install(plugin = Logging) {
            logger = Logger.SIMPLE
            level = LogLevel.INFO
            filter { request -> true }
            sanitizeHeader { header -> header.equals(other = HttpHeaders.Authorization, ignoreCase = true) }
        }
    }

    @Suppress("MagicNumber")
    private fun HttpClientConfig<*>.configureTimeouts() {
        install(plugin = HttpTimeout) {
            requestTimeoutMillis = 30_000
            connectTimeoutMillis = 10_000
            socketTimeoutMillis = 15_000
        }
    }
}

private suspend fun handleClientRequestException(cause: Throwable, request: HttpRequest) {
    val exception = cause as? ClientRequestException ?: return

    val response = exception.response
    val errorBody = response.bodyAsText()

    Logger.SIMPLE.log(
        """
            ❌ HTTP ERROR:
            ├── Response URL: ${response.request.url}
            ├── Request Method: ${request.method.value} URL: ${request.url}
            ├── Response Method: ${response.request.method.value}
            ├── Response Status: ${response.status}
            ├── Response Headers: ${response.headers.entries().joinToString("\n│    ") { "${it.key}: ${it.value}" }}
            ├── Response Body: $errorBody
        """.trimIndent()
    )

    throw mapToCustomException(response.status, cause)
}

private fun mapToCustomException(statusCode: HttpStatusCode, cause: Throwable): NetworkException =
    when (statusCode) {
        HttpStatusCode.NotFound -> NetworkException.NotFoundException(cause)
        HttpStatusCode.Conflict -> NetworkException.ConflictException(cause)
        HttpStatusCode.BadRequest -> NetworkException.BadRequestException(cause)
        HttpStatusCode.Unauthorized -> NetworkException.UnauthorizedException(cause)
        HttpStatusCode.InternalServerError -> NetworkException.ServerErrorException(cause)
        HttpStatusCode.UnprocessableEntity -> NetworkException.UnprocessableEntityException(cause)
        else -> NetworkException.UnknownException(cause)
    }