package com.walcker.movies.mockFakes

import com.walcker.movies.features.domain.api.MovieApi
import com.walcker.movies.features.domain.models.MovieSection
import com.walcker.movies.mockData.data.creditsResponseTestData
import com.walcker.movies.mockData.data.movieListResponseTestData
import com.walcker.movies.mockData.data.movieResponse2TestData
import com.walcker.movies.mockData.data.movieResponseTestData

internal object FakeMovieApi {

    internal fun createMockMovieApi(): MovieApi {
        return object : MovieApi {
            override suspend fun getMovies(sectionType: MovieSection.SectionType) = movieListResponseTestData

            override suspend fun getMovieDetail(movieId: Int) = when (movieId) {
                1 -> movieResponseTestData
                2 -> movieResponse2TestData
                else -> movieResponseTestData
            }

            override suspend fun getCredits(movieId: Int) = creditsResponseTestData
        }
    }

    internal fun createMockMovieApiWithError(): MovieApi {
        return object : MovieApi {
            override suspend fun getMovies(sectionType: MovieSection.SectionType) =
                throw RuntimeException("API Error")

            override suspend fun getMovieDetail(movieId: Int) =
                throw RuntimeException("API Error")

            override suspend fun getCredits(movieId: Int) =
                throw RuntimeException("API Error")
        }
    }
}