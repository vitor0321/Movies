package com.walcker.movies.features.movies.features.data.api

import com.walcker.movies.core.Platform
import com.walcker.movies.features.movies.features.data.models.CreditsResponse
import com.walcker.movies.features.movies.features.data.models.MovieListResponse
import com.walcker.movies.features.movies.features.data.models.MovieResponse
import com.walcker.movies.features.movies.features.data.models.VideoResponse
import com.walcker.movies.features.movies.features.data.network.HttpConfig
import com.walcker.movies.features.movies.features.domain.api.MovieApi
import com.walcker.movies.features.movies.features.domain.models.MovieSection
import com.walcker.movies.features.movies.features.domain.network.NetworkClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.get
import io.ktor.client.request.parameter

internal class MovieApiImpl(
    private val networkClient: NetworkClient,
    private val platform: Platform,
) : MovieApi {

    override suspend fun getMovies(
        sectionType: MovieSection.SectionType,
        page: Int,
    ): MovieListResponse =
        networkClient.httpClient().get("/3/movie/${sectionType.category}") {
            addLanguageParam()
            addPageParam(page = page)
        }.body()

    override suspend fun getMovieDetail(movieId: Int): MovieResponse =
        networkClient.httpClient().get("/3/movie/${movieId}") {
            addLanguageParam()
        }.body()

    override suspend fun getCredits(movieId: Int): CreditsResponse =
        networkClient.httpClient().get("/3/movie/${movieId}/credits") {
            addLanguageParam()
        }.body()

    override suspend fun getMovieVideos(movieId: Int): VideoResponse =
        networkClient.httpClient().get("/3/movie/$movieId/videos") {
            addLanguageParam()
        }.body()

    private fun HttpRequestBuilder.addLanguageParam() {
        parameter(HttpConfig.LANGUAGE.value, platform.languageSystem)
    }

    private fun HttpRequestBuilder.addPageParam(page: Int) {
        parameter(HttpConfig.PAGE.value, page)
    }
}