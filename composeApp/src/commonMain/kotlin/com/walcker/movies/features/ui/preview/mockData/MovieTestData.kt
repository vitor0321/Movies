package com.walcker.movies.features.ui.preview.mockData

import com.walcker.movies.features.data.network.HttpConfig
import com.walcker.movies.features.domain.models.Movie

internal val movieTestData = Movie(
    id = 1,
    title = "A Minecraft Movie",
    overview = "Movie Overview",
    posterUrl = "${HttpConfig.IMAGE_SMALL_BASE_URL}/6WxhEvFsauuACfv8HyoVX6mZKFj.jpg"
)