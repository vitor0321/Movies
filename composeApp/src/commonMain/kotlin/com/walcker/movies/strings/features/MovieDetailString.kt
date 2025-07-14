package com.walcker.movies.strings.features

internal data class MovieDetailString(
    val title: String,
    val buttonText: String,
)

internal val movieDetailStringsPt = MovieDetailString(
    title = "Detalhes do filme",
    buttonText = "Assistir trailer",
)

internal val movieDetailStringsEn = MovieDetailString(
    title = "Movie details",
    buttonText = "Watch trailer",
)