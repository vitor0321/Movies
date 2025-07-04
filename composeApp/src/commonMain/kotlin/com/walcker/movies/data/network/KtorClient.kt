package com.walcker.movies.data.network

import com.walcker.movies.data.models.MovieListResponse
import com.walcker.movies.getPlatform
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.HttpResponseValidator
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

private const val baseUrl = "https://api.themoviedb.org"
internal const val imageSmallBaseUrl = "https://image.tmdb.org/t/p/w154"

internal object KtorClient {

    private val accessToken: String =
        try {
            getPlatform().accessToken
        } catch (e: IllegalStateException) {
            println("walcker: Error getting access token: ${e.message}")
            ""
        }

    private val client = HttpClient {
        install(ContentNegotiation) {
            json(
                Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                }
            )
        }

        install(Auth) {
            bearer {
                loadTokens {
                    BearerTokens(
                        accessToken = accessToken,
                        refreshToken = "",
                    )
                }
            }
        }

        install(Logging) {
            logger = Logger.SIMPLE
            level = LogLevel.ALL
            sanitizeHeader { header -> header === HttpHeaders.Authorization }
        }
        install(HttpTimeout) {
            connectTimeoutMillis = 15_000
            requestTimeoutMillis = 30_000
            socketTimeoutMillis = 15_000
        }

        expectSuccess = true
        HttpResponseValidator {
            validateResponse { response ->
                val statusCode = response.status.value
                if (statusCode > 200) {
                    throw Exception("walcker: Http Response error with status code: $statusCode")
                }
            }
        }
    }

    suspend fun getMovies(category: String, language: String = "pt-BR"): MovieListResponse {
        return client.get("$baseUrl/3/movie/$category") {
            parameter("language", language)
        }.body()
    }
}