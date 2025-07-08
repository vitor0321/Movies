package com.walcker.movies.features.data.repository

import com.walcker.movies.features.data.helper.withRetry
import com.walcker.movies.features.data.mapper.MovieResponseMapper.toDomain
import com.walcker.movies.features.domain.api.MovieApi
import com.walcker.movies.features.domain.models.MovieSection
import com.walcker.movies.features.domain.repository.MoviesRepository
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.withContext

internal class MoviesRepositoryImpl(
    private val movieApi: MovieApi,
    private val dispatcher: CoroutineDispatcher,
) : MoviesRepository {

    override suspend fun getMoviesSections(): List<MovieSection> = withContext(dispatcher) {
        val movieCategories = listOf(
            MovieSection.SectionType.POPULAR,
            MovieSection.SectionType.TOP_RATED,
            MovieSection.SectionType.UPCOMING,
        )

        val moviesDeferred = movieCategories.map { sectionType ->
            async { fetchMoviesByCategory(sectionType) }
        }

        moviesDeferred.awaitAll()
    }

    private suspend fun fetchMoviesByCategory(sectionType: MovieSection.SectionType): MovieSection {
        return withRetry(dispatcher = dispatcher) {
            val movieResponse = movieApi.getMovies(sectionType = sectionType)
            MovieSection(
                sectionType = sectionType,
                movies = movieResponse.results.map { it.toDomain() }.toImmutableList()
            )
        }
    }
}