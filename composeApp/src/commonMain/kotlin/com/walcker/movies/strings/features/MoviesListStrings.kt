package com.walcker.movies.strings.features

internal data class MoviesListStrings(
    val appName: String,
    val popularMovies: String,
    val highlight: String,
    val topRatedMovies: String,
    val upcomingMovies: String,
)

internal val moviesListStringsPt = MoviesListStrings(
    appName = "Filmes",
    highlight = "Destaque",
    popularMovies = "Populares",
    topRatedMovies = "Mais Bem Avaliados",
    upcomingMovies = "Em Breve",
)

internal val moviesListStringsEn = MoviesListStrings(
    appName = "Movies",
    highlight = "Highlight",
    popularMovies = "Popular",
    topRatedMovies = "Top Rated",
    upcomingMovies = "Upcoming",
)