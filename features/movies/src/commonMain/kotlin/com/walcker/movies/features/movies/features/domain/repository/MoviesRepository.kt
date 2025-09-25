package com.walcker.movies.features.movies.features.domain.repository

import com.walcker.movies.features.movies.features.domain.models.Movie
import com.walcker.movies.features.movies.features.domain.models.MovieSection
import com.walcker.movies.features.movies.features.domain.models.MoviesPagination

internal interface MoviesRepository {

    suspend fun getMoviesSections(pagination: MoviesPagination): Result<List<MovieSection>>
    suspend fun getMovieSection(sectionType: MovieSection.SectionType, page: Int): Result<MovieSection>
    suspend fun getMovieDetail(movieId: Int): Result<Movie>
}