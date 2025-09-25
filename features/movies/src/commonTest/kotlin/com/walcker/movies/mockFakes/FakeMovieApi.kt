package com.walcker.movies.mockFakes

import com.walcker.movies.features.movies.features.data.models.VideoResponse
import com.walcker.movies.features.movies.features.domain.api.MovieApi
import com.walcker.movies.features.movies.features.domain.models.MovieSection
import com.walcker.movies.mockData.data.creditsResponseTestData
import com.walcker.movies.mockData.data.movieListResponseTestData
import com.walcker.movies.mockData.data.movieResponse2TestData
import com.walcker.movies.mockData.data.movieResponseTestData
import com.walcker.movies.mockData.data.videoResponseTestData

internal object FakeMovieApi {

    internal fun createMockMovieApi(): MovieApi =
        object : MovieApi {
            override suspend fun getMovies(sectionType: MovieSection.SectionType, page: Int) = movieListResponseTestData

            override suspend fun getMovieDetail(movieId: Int) = when (movieId) {
                1 -> movieResponseTestData
                2 -> movieResponse2TestData
                else -> movieResponseTestData
            }

            override suspend fun getCredits(movieId: Int) = creditsResponseTestData
            override suspend fun getMovieVideos(movieId: Int): VideoResponse = videoResponseTestData
        }

    internal fun createMockMovieApiWithError(): MovieApi =
        object : MovieApi {
            override suspend fun getMovies(sectionType: MovieSection.SectionType, page: Int) =
                throw RuntimeException("API Error getMovies")

            override suspend fun getMovieDetail(movieId: Int) =
                throw RuntimeException("API Error getMovieDetail")

            override suspend fun getCredits(movieId: Int) =
                throw RuntimeException("API Error getCredits")

            override suspend fun getMovieVideos(movieId: Int): VideoResponse =
                throw RuntimeException("API Error getMovieVideos")

        }
}