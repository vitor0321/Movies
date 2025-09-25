package com.walcker.movies.mockFakes

import com.walcker.movies.features.movies.features.data.mapper.MovieResponseMapper.toDomain
import com.walcker.movies.features.movies.features.domain.models.Movie
import com.walcker.movies.features.movies.features.domain.models.MovieSection
import com.walcker.movies.features.movies.features.domain.models.MoviesPagination
import com.walcker.movies.features.movies.features.domain.repository.MoviesRepository
import com.walcker.movies.mockData.data.creditsResponseTestData
import com.walcker.movies.mockData.data.movieListResponse2TestData
import com.walcker.movies.mockData.data.movieResponse2TestData
import com.walcker.movies.mockData.data.movieResponseTestData
import kotlinx.collections.immutable.toImmutableList

internal object FakeMoviesRepository {

    fun createSuccessRepository(): MoviesRepository = object : MoviesRepository {
        override suspend fun getMoviesSections(pagination: MoviesPagination): Result<List<MovieSection>> =
            Result.success(createDefaultSections())

        override suspend fun getMovieSection(
            sectionType: MovieSection.SectionType,
            page: Int
        ): Result<MovieSection> =
            Result.success(
                createDefaultSections().first { it.sectionType == sectionType }.copy(
                    movies = movieListResponse2TestData.results.map { it.toDomain() }.toImmutableList()
                )
            )

        override suspend fun getMovieDetail(movieId: Int): Result<Movie> =
            Result.success(createMovieDetail(movieId))
    }

    fun createFailureRepository(
        exception: Exception = RuntimeException("Repository error")
    ): MoviesRepository = object : MoviesRepository {
        override suspend fun getMoviesSections(pagination: MoviesPagination): Result<List<MovieSection>> =
            Result.failure(exception)

        override suspend fun getMovieSection(
            sectionType: MovieSection.SectionType,
            page: Int
        ): Result<MovieSection> = Result.failure(exception)

        override suspend fun getMovieDetail(movieId: Int): Result<Movie> =
            Result.failure(exception)
    }

    private fun createDefaultSections(): List<MovieSection> =
        MovieSection.SectionType.entries.map { sectionType ->
            MovieSection(
                sectionType = sectionType,
                movies = movieListResponse2TestData.results.map { it.toDomain() }.toImmutableList()
            )
        }

    private fun createMovieDetail(movieId: Int): Movie =
        when (movieId) {
            1 -> movieResponseTestData.toDomain(castMembersResponse = creditsResponseTestData.cast)
            2 -> movieResponse2TestData.toDomain(castMembersResponse = creditsResponseTestData.cast)
            else -> movieResponseTestData.toDomain(castMembersResponse = creditsResponseTestData.cast)
        }

}
