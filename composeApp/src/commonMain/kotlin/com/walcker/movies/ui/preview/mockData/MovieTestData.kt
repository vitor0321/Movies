package com.walcker.movies.ui.preview.mockData

import com.walcker.movies.data.network.HttpConfig
import com.walcker.movies.domain.models.Movie

internal val movieTestData = Movie(
    id = 1,
    title = "A Minecraft Movie",
    overview = "Movie Overview",
    posterUrl = "${HttpConfig.IMAGE_SMALL_BASE_URL}/6WxhEvFsauuACfv8HyoVX6mZKFj.jpg"
)