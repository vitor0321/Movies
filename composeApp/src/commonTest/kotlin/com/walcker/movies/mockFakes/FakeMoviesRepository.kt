package com.walcker.movies.mockFakes

import com.walcker.movies.features.data.mapper.MovieResponseMapper.toDomain
import com.walcker.movies.features.domain.models.ImageSize
import com.walcker.movies.features.domain.models.Movie
import com.walcker.movies.features.domain.models.MovieSection
import com.walcker.movies.features.domain.repository.MoviesRepository
import com.walcker.movies.mockData.data.castMemberResponse1TestData
import com.walcker.movies.mockData.data.castMemberResponse2TestData
import com.walcker.movies.mockData.data.creditsResponseTestData
import com.walcker.movies.mockData.data.movieListResponseTestData
import com.walcker.movies.mockData.data.movieResponseTestData
import com.walcker.movies.mockData.data.movieResponse2TestData
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.delay

internal object FakeMoviesRepository {

    fun createSuccessRepository(): MoviesRepository = createRepository()

    fun createFailureRepository(
        exception: Exception = RuntimeException("Repository error")
    ): MoviesRepository = createRepository(
        sectionsResult = { Result.failure(exception) },
        detailResult = { Result.failure(exception) }
    )

    fun createDelayedRepository(delayMs: Long = 1000L): MoviesRepository = createRepository(
        sectionsResult = {
            delay(delayMs)
            Result.success(createDefaultSections())
        },
        detailResult = { movieId ->
            delay(delayMs)
            Result.success(createMovieDetail(movieId))
        }
    )

    fun createEmptyRepository(): MoviesRepository = createRepository(
        sectionsResult = { Result.success(createEmptySections()) },
        detailResult = { Result.failure(RuntimeException("Movie not found")) }
    )

    fun createPartialFailureRepository(): MoviesRepository = createRepository(
        sectionsResult = { Result.success(createPartialSections()) },
        detailResult = { movieId ->
            if (movieId == 1) Result.success(createMovieDetail(movieId))
            else Result.failure(RuntimeException("Movie detail not found"))
        }
    )

    fun createRepositoryWithoutCast(): MoviesRepository = createRepository(
        detailResult = { movieId ->
            Result.success(createMovieDetail(movieId, castMembers = emptyList()))
        }
    )

    fun createRepositoryWithSpecificMovie(movie: Movie): MoviesRepository = createRepository(
        sectionsResult = { Result.success(createSectionsWithMovie(movie)) },
        detailResult = { Result.success(movie) }
    )

    fun createRepositoryWithLimitedCast(castCount: Int = 3): MoviesRepository = createRepository(
        detailResult = { movieId ->
            val limitedCast = listOf(castMemberResponse1TestData, castMemberResponse2TestData).take(castCount)
            Result.success(createMovieDetail(movieId, castMembers = limitedCast))
        }
    )

    fun createRepositoryWithDifferentImageSizes(): MoviesRepository = createRepository(
        sectionsResult = { Result.success(createSectionsWithImageSize(ImageSize.SMALL)) },
        detailResult = { movieId ->
            Result.success(createMovieDetail(movieId, imageSize = ImageSize.LARGE))
        }
    )

    private fun createRepository(
        sectionsResult: suspend () -> Result<List<MovieSection>> = { Result.success(createDefaultSections()) },
        detailResult: suspend (Int) -> Result<Movie> = { movieId -> Result.success(createMovieDetail(movieId)) }
    ): MoviesRepository = object : MoviesRepository {
        override suspend fun getMoviesSections(): Result<List<MovieSection>> = sectionsResult()
        override suspend fun getMovieDetail(movieId: Int): Result<Movie> = detailResult(movieId)
    }

    private fun createDefaultSections(): List<MovieSection> =
        MovieSection.SectionType.values().map { sectionType ->
            MovieSection(
                sectionType = sectionType,
                movies = movieListResponseTestData.results.map { it.toDomain() }.toImmutableList()
            )
        }

    private fun createEmptySections(): List<MovieSection> =
        MovieSection.SectionType.values().map { sectionType ->
            MovieSection(
                sectionType = sectionType,
                movies = emptyList<Movie>().toImmutableList()
            )
        }

    private fun createPartialSections(): List<MovieSection> = listOf(
        MovieSection(
            sectionType = MovieSection.SectionType.POPULAR,
            movies = movieListResponseTestData.results.map { it.toDomain() }.toImmutableList()
        ),
        MovieSection(
            sectionType = MovieSection.SectionType.TOP_RATED,
            movies = emptyList<Movie>().toImmutableList()
        ),
        MovieSection(
            sectionType = MovieSection.SectionType.UPCOMING,
            movies = listOf(movieResponseTestData.toDomain()).toImmutableList()
        )
    )

    private fun createSectionsWithMovie(movie: Movie): List<MovieSection> =
        MovieSection.SectionType.values().map { sectionType ->
            MovieSection(
                sectionType = sectionType,
                movies = listOf(movie).toImmutableList()
            )
        }

    private fun createSectionsWithImageSize(imageSize: ImageSize): List<MovieSection> =
        MovieSection.SectionType.values().map { sectionType ->
            MovieSection(
                sectionType = sectionType,
                movies = movieListResponseTestData.results.map {
                    it.toDomain(imageSize = imageSize)
                }.toImmutableList()
            )
        }

    private fun createMovieDetail(
        movieId: Int,
        castMembers: List<com.walcker.movies.features.data.models.CastMemberResponse> = creditsResponseTestData.cast,
        imageSize: ImageSize = ImageSize.X_LARGE
    ): Movie = when (movieId) {
        1 -> movieResponseTestData.toDomain(castMembersResponse = castMembers, imageSize = imageSize)
        2 -> movieResponse2TestData.toDomain(castMembersResponse = castMembers, imageSize = imageSize)
        else -> movieResponseTestData.toDomain(castMembersResponse = castMembers, imageSize = imageSize)
    }
}
