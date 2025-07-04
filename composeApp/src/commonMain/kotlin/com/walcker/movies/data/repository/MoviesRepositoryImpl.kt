package com.walcker.movies.data.repository

import com.walcker.movies.data.mapper.MovieResponseMapper.toDomain
import com.walcker.movies.data.network.KtorClient
import com.walcker.movies.domain.models.MovieSection
import com.walcker.movies.domain.repository.MoviesRepository
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.withContext

internal class MoviesRepositoryImpl(
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
) : MoviesRepository {

    override suspend fun getMoviesSections(): List<MovieSection> = withContext(ioDispatcher) {
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
        return try {
            val movieResponse = KtorClient.getMovies(sectionType.category)
            MovieSection(
                sectionType = sectionType,
                movies = movieResponse.results.map { it.toDomain() }.toImmutableList()
            )
        } catch (e: Exception) {
            e.printStackTrace()
            throw e
        }
    }
}