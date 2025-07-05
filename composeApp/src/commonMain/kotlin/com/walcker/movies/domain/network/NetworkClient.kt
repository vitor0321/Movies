package com.walcker.movies.domain.network

import io.ktor.client.HttpClient

internal interface NetworkClient {

    suspend fun httpClient(): HttpClient
}