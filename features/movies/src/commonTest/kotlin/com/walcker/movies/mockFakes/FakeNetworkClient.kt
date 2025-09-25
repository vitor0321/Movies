package com.walcker.movies.mockFakes

import com.walcker.movies.features.movies.features.domain.network.NetworkClient
import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

internal object FakeNetworkClient {

    fun createMockNetworkClient(
        mockResponses: Map<String, String> = emptyMap(),
        statusCode: HttpStatusCode = HttpStatusCode.OK
    ): NetworkClient = object : NetworkClient {
            override suspend fun httpClient(): HttpClient {
                val mockEngine = MockEngine { request ->
                    val url = request.url.toString()
                    val responseContent = mockResponses.entries.find { (endpoint, _) ->
                        url.contains(endpoint)
                    }?.value ?: """{"message": "Mock response"}"""

                    respond(
                        content = responseContent,
                        status = statusCode,
                        headers = headersOf(HttpHeaders.ContentType, ContentType.Application.Json.toString())
                    )
                }

                return HttpClient(mockEngine) {
                    install(ContentNegotiation) {
                        json(Json {
                            ignoreUnknownKeys = true
                            prettyPrint = true
                            isLenient = true
                        })
                    }
                }
            }
        }

    fun createMockNetworkClientWithCredits(creditsJson: String): NetworkClient =
        createMockNetworkClient(
            mockResponses = mapOf(
                "credits" to creditsJson
            )
        )

    fun createMockNetworkClientWithError(
        errorCode: HttpStatusCode = HttpStatusCode.InternalServerError
    ): NetworkClient = object : NetworkClient {
            override suspend fun httpClient(): HttpClient {
                val mockEngine = MockEngine {
                    respond(
                        content = """{"error": "Mock error response"}""",
                        status = errorCode,
                        headers = headersOf(HttpHeaders.ContentType, ContentType.Application.Json.toString())
                    )
                }

                return HttpClient(mockEngine) {
                    install(ContentNegotiation) {
                        json(Json {
                            ignoreUnknownKeys = true
                            prettyPrint = true
                            isLenient = true
                        })
                    }
                }
            }
        }
}