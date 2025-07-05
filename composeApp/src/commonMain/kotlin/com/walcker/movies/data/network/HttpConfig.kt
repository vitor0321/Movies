package com.walcker.movies.data.network

internal enum class HttpConfig(val value: String) {
    BASE_URL("https://api.themoviedb.org"),
    IMAGE_SMALL_BASE_URL("https://image.tmdb.org/t/p/w154"),
    LANGUAGE("language"),
}