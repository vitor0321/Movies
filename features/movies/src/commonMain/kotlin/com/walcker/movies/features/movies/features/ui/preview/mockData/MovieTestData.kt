package com.walcker.movies.features.movies.features.ui.preview.mockData

import com.walcker.movies.features.movies.features.data.network.HttpConfig
import com.walcker.movies.features.movies.features.domain.models.Movie
import kotlinx.collections.immutable.persistentListOf

internal val movieTestData = Movie(
    id = 1,
    title = "A Minecraft Movie",
    overview = "Movie Overview",
    posterUrl = "${HttpConfig.IMAGE_BASE_URL}/6WxhEvFsauuACfv8HyoVX6mZKFj.jpg",
    genres = persistentListOf(genre1TestData, genre2TestData),
    year = "2025",
    duration = "1h 55m",
    rating = "8.5",
    castMembers = persistentListOf(castMember1TestData, castMember2TestData),
    moviesTrailerYouTubeKey = "vasdfOUBFKkaasdf2"
)