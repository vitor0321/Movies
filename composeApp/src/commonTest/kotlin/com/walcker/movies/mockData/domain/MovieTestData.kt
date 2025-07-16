package com.walcker.movies.mockData.domain

import com.walcker.movies.features.domain.models.Movie
import kotlinx.collections.immutable.persistentListOf

internal val movieTestData = Movie(
    id = 1,
    title = "Test Movie",
    overview = "Test Overview",
    posterUrl = "https://image.tmdb.org/t/p/w185//test.jpg",
    genres = persistentListOf(genre1TestData),
    year = "2024",
    duration = "2h ",
    rating = "8.5",
    castMembers = null
)

internal val movieTestData2 = Movie(
    id = 2,
    title = "Movie 2",
    overview = "Overview 2",
    posterUrl = "https://image.tmdb.org/t/p/w185//movie2.jpg",
    genres = persistentListOf(genre1TestData),
    year = "2024",
    duration = "1h 30min", // 90 min vira 1h 30min
    rating = "7.5",
    castMembers = null
)

internal val movieTestData1 = Movie(
    id = 1,
    title = "Test Movie",
    overview = "Test Overview",
    posterUrl = "https://image.tmdb.org/t/p/w185//test.jpg",
    genres = persistentListOf(genre1TestData),
    year = "2024",
    duration = "2h ",
    rating = "8.5",
    castMembers = castMemberListTestData
)