package com.walcker.movies.features.data.repository

import com.walcker.movies.features.data.mapper.MovieResponseMapper.toDomain
import com.walcker.movies.features.data.network.HttpConfig
import com.walcker.movies.features.domain.api.MovieApi
import com.walcker.movies.features.domain.models.ImageSize
import com.walcker.movies.features.domain.models.Movie
import com.walcker.movies.features.domain.models.MovieSection
import com.walcker.movies.features.domain.models.MoviesPagination
import com.walcker.movies.features.domain.repository.MoviesRepository
import com.walcker.movies.handle.withRetry
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext

internal class MoviesRepositoryImpl(
    private val movieApi: MovieApi,
    private val dispatcher: CoroutineDispatcher,
) : MoviesRepository {

    override suspend fun getMoviesSections(pagination: MoviesPagination): Result<List<MovieSection>> =
        withContext(dispatcher) {
            runCatching {
                coroutineScope {
                    val movieCategoriesWithPages = MovieSection.SectionType.entries.map { sectionType ->
                        sectionType to pagination.pageFor(sectionType)
                    }

                    val moviesDeferred = movieCategoriesWithPages.map { (sectionType, page) ->
                        async { fetchMoviesByCategory(sectionType, page) }
                    }

                    moviesDeferred.awaitAll()
                }
            }
        }

    private suspend fun fetchMoviesByCategory(
        sectionType: MovieSection.SectionType,
        page: Int,
    ): MovieSection =
        withRetry(dispatcher = dispatcher) {
            val movieResponse = movieApi.getMovies(sectionType = sectionType, page = page)
            MovieSection(
                sectionType = sectionType,
                movies = movieResponse.results.map { it.toDomain() }.toImmutableList()
            )
        }

    override suspend fun getMovieDetail(movieId: Int): Result<Movie> =
        withRetry(dispatcher = dispatcher) {
            runCatching {
                coroutineScope {
                    val movieDetailDeferred = async { movieApi.getMovieDetail(movieId = movieId) }
                    val creditsDeferred = async { movieApi.getCredits(movieId = movieId) }

                    val movieDetailResponse = movieDetailDeferred.await()
                    val creditsResponse = creditsDeferred.await()

                    movieDetailResponse.toDomain(
                        castMembersResponse = creditsResponse.cast,
                        imageSize = ImageSize.X_LARGE,
                    )
                }
            }
        }

    override suspend fun getTrailerUrl(movieId: Int): Result<String?> = runCatching {
        val response = movieApi.getMovieVideos(movieId)
        val trailers = response.results.filter {
            it.type == HttpConfig.TRAILER.value && it.site == HttpConfig.YOUTUBE.value
        }

        val officialTrailer = trailers.firstOrNull { it.official }
        val selectedTrailer = officialTrailer ?: trailers.firstOrNull()
        selectedTrailer?.key?.let { HttpConfig.YOUTUBE_BASE_URL.value + it }
    }
}