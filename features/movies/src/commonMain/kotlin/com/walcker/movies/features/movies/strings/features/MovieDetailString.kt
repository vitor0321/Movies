package com.walcker.movies.features.movies.strings.features

internal data class MovieDetailString(
    val title: String,
    val buttonText: String,
)

internal val movieDetailStringsPt = MovieDetailString(
    title = "Detalhes",
    buttonText = "Assistir trailer",
)

internal val movieDetailStringsEn = MovieDetailString(
    title = "Details",
    buttonText = "Watch trailer",
)