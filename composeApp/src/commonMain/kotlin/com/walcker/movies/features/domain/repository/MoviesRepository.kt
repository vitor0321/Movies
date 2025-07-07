package com.walcker.movies.features.domain.repository

import com.walcker.movies.features.domain.models.MovieSection

internal interface MoviesRepository {

    suspend fun getMoviesSections(): List<MovieSection>
}