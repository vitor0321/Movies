package com.walcker.movies.features.data.network

internal enum class HttpConfig(val value: String) {
    BASE_URL("https://api.themoviedb.org"),
    IMAGE_BASE_URL("https://image.tmdb.org/t/p"),
    LANGUAGE("language"),
    PAGE("page"),
}