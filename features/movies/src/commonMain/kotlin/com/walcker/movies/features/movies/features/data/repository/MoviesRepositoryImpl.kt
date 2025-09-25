package com.walcker.movies.features.movies.features.data.repository

import com.walcker.movies.features.movies.features.data.mapper.MovieResponseMapper.toDomain
import com.walcker.movies.features.movies.features.data.network.HttpConfig
import com.walcker.movies.features.movies.features.domain.api.MovieApi
import com.walcker.movies.features.movies.features.domain.models.ImageSize
import com.walcker.movies.features.movies.features.domain.models.Movie
import com.walcker.movies.features.movies.features.domain.models.MovieSection
import com.walcker.movies.features.movies.features.domain.models.MoviesPagination
import com.walcker.movies.features.movies.features.domain.repository.MoviesRepository
import com.walcker.movies.features.movies.handle.withRetry
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext

private const val MAX_HIGHLIGHT_MOVIES = 5

internal class MoviesRepositoryImpl(
    private val movieApi: MovieApi,
    private val dispatcher: CoroutineDispatcher,
) : MoviesRepository {

    override suspend fun getMoviesSections(pagination: MoviesPagination): Result<List<MovieSection>> =
        withContext(dispatcher) {
            runCatching {
                val movieCategoriesWithPages = MovieSection.SectionType.entries.map { sectionType ->
                    sectionType to pagination.pageFor(sectionType)
                }

                val moviesDeferred = movieCategoriesWithPages.map { (sectionType, page) ->
                    async { fetchMoviesByCategory(sectionType, page) }
                }

                moviesDeferred.awaitAll()
            }
        }

    override suspend fun getMovieSection(sectionType: MovieSection.SectionType, page: Int): Result<MovieSection> =
        withRetry(dispatcher = dispatcher) {
            runCatching {
                fetchMoviesByCategory(sectionType, page)
            }
        }

    private suspend fun fetchMoviesByCategory(
        sectionType: MovieSection.SectionType,
        page: Int,
    ): MovieSection = withRetry(dispatcher = dispatcher) {
        val movieResponse = movieApi.getMovies(sectionType = sectionType, page = page)
        val imageSize =
            if (sectionType != MovieSection.SectionType.HIGHLIGHT) ImageSize.SMALL
            else ImageSize.MEDIUM

        val moviesList = movieResponse.results.map { it.toDomain(imageSize = imageSize) }

        val processedMovies =
            if (sectionType != MovieSection.SectionType.HIGHLIGHT) moviesList
            else moviesList.shuffled().take(MAX_HIGHLIGHT_MOVIES)

        MovieSection(
            sectionType = sectionType,
            movies = processedMovies.toImmutableList()
        )
    }

    override suspend fun getMovieDetail(movieId: Int): Result<Movie> =
        withRetry(dispatcher = dispatcher) {
            runCatching {
                coroutineScope {
                    val movieDetailDeferred = async { movieApi.getMovieDetail(movieId = movieId) }
                    val creditsDeferred = async { movieApi.getCredits(movieId = movieId) }
                    val videosDeferred = async { movieApi.getMovieVideos(movieId = movieId) }

                    val movieDetailResponse = movieDetailDeferred.await()
                    val creditsResponse = creditsDeferred.await()
                    val videosResponse = videosDeferred.await()

                    val movieTrailerYoutubeKey = videosResponse.results.firstOrNull() { videoItemResponse ->
                        videoItemResponse.site == HttpConfig.YOUTUBE.value
                    }?.key?.let { HttpConfig.YOUTUBE_BASE_URL.value + it }

                    movieDetailResponse.toDomain(
                        castMembersResponse = creditsResponse.cast,
                        moviesTrailerYouTubeKey = movieTrailerYoutubeKey,
                        imageSize = ImageSize.X_LARGE,
                    )
                }
            }
        }
}