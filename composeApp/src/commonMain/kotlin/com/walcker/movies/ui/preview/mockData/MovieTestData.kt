package com.walcker.movies.ui.preview.mockData

import com.walcker.movies.data.network.imageSmallBaseUrl
import com.walcker.movies.domain.models.Movie

val movieTestData = Movie(
    id = 1,
    title = "A Minecraft Movie",
    overview = "Movie Overview",
    posterUrl = "$imageSmallBaseUrl/6WxhEvFsauuACfv8HyoVX6mZKFj.jpg"
)