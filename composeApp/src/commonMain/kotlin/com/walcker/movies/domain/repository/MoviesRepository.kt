package com.walcker.movies.domain.repository

import com.walcker.movies.domain.models.MovieSection

internal interface MoviesRepository {

    suspend fun getMoviesSections(): List<MovieSection>
}