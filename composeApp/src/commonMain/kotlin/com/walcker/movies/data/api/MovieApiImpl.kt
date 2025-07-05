package com.walcker.movies.data.api

import com.walcker.movies.data.models.MovieListResponse
import com.walcker.movies.data.network.HttpConfig
import com.walcker.movies.domain.api.MovieApi
import com.walcker.movies.domain.models.MovieSection
import com.walcker.movies.domain.network.NetworkClient
import io.ktor.client.call.body
import io.ktor.client.request.get

internal class MovieApiImpl(
    private val networkClient: NetworkClient,
) : MovieApi {

    override suspend fun getMovies(sectionType: MovieSection.SectionType, language: String): MovieListResponse {
        return networkClient.httpClient().get("/3/movie/${sectionType.category}") {
            mapOf(HttpConfig.LANGUAGE.value to language)
        }.body()
    }
}