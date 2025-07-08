package com.walcker.movies.strings.features

internal data class MoviesListStrings(
    val popularMovies: String,
    val topRatedMovies: String,
    val upcomingMovies: String,
)

internal val moviesListStringsPt = MoviesListStrings(
    popularMovies = "Filmes Populares",
    topRatedMovies = "Filmes Mais Bem Avaliados",
    upcomingMovies = "Filmes em Breve",
)

internal val moviesListStringsEn = MoviesListStrings(
    popularMovies = "Popular Movies",
    topRatedMovies = "Top Rated Movies",
    upcomingMovies = "Upcoming Movies",
)


