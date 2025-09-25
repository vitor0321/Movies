package com.walcker.movies.features.movies.features.domain.network

import io.ktor.client.HttpClient

internal interface NetworkClient {

    suspend fun httpClient(): HttpClient
}