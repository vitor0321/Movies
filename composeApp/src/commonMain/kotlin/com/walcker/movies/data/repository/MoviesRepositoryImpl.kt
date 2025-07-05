package com.walcker.movies.data.repository

import com.walcker.movies.data.helper.withRetry
import com.walcker.movies.data.mapper.MovieResponseMapper.toDomain
import com.walcker.movies.domain.api.MovieApi
import com.walcker.movies.domain.models.MovieSection
import com.walcker.movies.domain.repository.MoviesRepository
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.client.request.HttpRequest
import io.ktor.client.statement.bodyAsText
import io.ktor.client.statement.request
import io.ktor.http.HttpStatusCode
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.withContext
import kotlin.math.log

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