package com.walcker.movies.features.data.api

import com.walcker.movies.Platform
import com.walcker.movies.features.data.models.CreditsResponse
import com.walcker.movies.features.data.models.MovieListResponse
import com.walcker.movies.features.data.models.MovieResponse
import com.walcker.movies.features.data.network.HttpConfig
import com.walcker.movies.features.domain.api.MovieApi
import com.walcker.movies.features.domain.models.MovieSection
import com.walcker.movies.features.domain.network.NetworkClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.get
import io.ktor.client.request.parameter

internal class MovieApiImpl(
    private val networkClient: NetworkClient,
    private val platform: Platform,
) : MovieApi {

    override suspend fun getMovies(sectionType: MovieSection.SectionType): MovieListResponse =
        networkClient.httpClient().get("/3/movie/${sectionType.category}") {
            addLanguageParam()
        }.body()

    override suspend fun getMovieDetail(movieId: Int): MovieResponse =
        networkClient.httpClient().get("/3/movie/${movieId}") {
            addLanguageParam()
        }.body()

    override suspend fun getCredits(movieId: Int): CreditsResponse =
        networkClient.httpClient().get("/3/movie/${movieId}/credits") {
            addLanguageParam()
        }.body()

    private fun HttpRequestBuilder.addLanguageParam() {
        parameter(HttpConfig.LANGUAGE.value, platform.languageSystem)
    }
}