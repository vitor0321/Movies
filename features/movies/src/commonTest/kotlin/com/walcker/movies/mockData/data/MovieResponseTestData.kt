package com.walcker.movies.mockData.data

import com.walcker.movies.features.movies.features.data.models.MovieListResponse
import com.walcker.movies.features.movies.features.data.models.MovieResponse
import kotlinx.datetime.LocalDate

internal val movieResponseTestData = MovieResponse(
    id = 1,
    title = "Test Movie",
    overview = "Test Overview",
    posterPath = "/test.jpg",
    genres = listOf(genreResponseTestData),
    releaseDate = LocalDate(2024, 1, 1),
    runtime = 120,
    voteAverage = 8.5
)

internal val movieResponse2TestData = MovieResponse(
    id = 2,
    title = "Movie 2",
    overview = "Overview 2",
    posterPath = "/movie2.jpg",
    genres = listOf(genreResponseTestData),
    releaseDate = LocalDate(2024, 2, 1),
    runtime = 90,
    voteAverage = 7.5
)

internal val movieResponse3TestData = MovieResponse(
    id = 3,
    title = "Test Movie 3",
    overview = "Test Overview 3",
    posterPath = "/test.jpg",
    genres = listOf(genreResponseTestData),
    releaseDate = LocalDate(2024, 1, 1),
    runtime = 120,
    voteAverage = 8.5
)

internal val movieResponse4TestData = MovieResponse(
    id = 4,
    title = "Movie 4",
    overview = "Overview 4",
    posterPath = "/movie2.jpg",
    genres = listOf(genreResponseTestData),
    releaseDate = LocalDate(2024, 2, 1),
    runtime = 90,
    voteAverage = 7.5
)

internal val movieResponse5TestData = MovieResponse(
    id = 5,
    title = "Movie 5",
    overview = "Overview 5",
    posterPath = "/movie2.jpg",
    genres = listOf(genreResponseTestData),
    releaseDate = LocalDate(2024, 2, 1),
    runtime = 90,
    voteAverage = 7.5
)

internal val movieListResponseTestData = MovieListResponse(
    results = listOf(
        movieResponseTestData,
        movieResponse2TestData,
    )
)

internal val movieListResponse2TestData = MovieListResponse(
    results = listOf(
        movieResponseTestData,
        movieResponse2TestData,
        movieResponse3TestData,
        movieResponse4TestData,
        movieResponse5TestData
    )
)