package com.walcker.movies.features.domain.api

import com.walcker.movies.features.data.models.CreditsResponse
import com.walcker.movies.features.data.models.MovieListResponse
import com.walcker.movies.features.data.models.MovieResponse
import com.walcker.movies.features.domain.models.MovieSection

internal interface MovieApi {
    suspend fun getMovies(
        sectionType: MovieSection.SectionType,
        page: Int,
    ): MovieListResponse
    suspend fun getMovieDetail(movieId: Int): MovieResponse
    suspend fun getCredits(movieId: Int): CreditsResponse
}