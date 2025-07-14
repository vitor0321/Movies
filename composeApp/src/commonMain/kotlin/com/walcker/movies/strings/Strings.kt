package com.walcker.movies.strings

import cafe.adriel.lyricist.LyricistStrings
import com.walcker.movies.strings.features.MovieDetailString
import com.walcker.movies.strings.features.MoviesListStrings
import com.walcker.movies.strings.features.movieDetailStringsEn
import com.walcker.movies.strings.features.movieDetailStringsPt
import com.walcker.movies.strings.features.moviesListStringsEn
import com.walcker.movies.strings.features.moviesListStringsPt

internal data class MoviesStrings(
    val appName: String,
    val moviesListStrings: MoviesListStrings,
    val movieDetailStrings: MovieDetailString,
)

internal object Locales {
    const val EN = "en"
    const val PT = "pt"
}

@LyricistStrings(languageTag = Locales.EN, default = true)
internal val EnStrings = MoviesStrings(
    appName = "Movies",
    moviesListStrings = moviesListStringsEn,
    movieDetailStrings = movieDetailStringsEn,
)

@LyricistStrings(languageTag = Locales.PT)
internal val PtStrings = MoviesStrings(
    appName = "Filmes",
    moviesListStrings = moviesListStringsPt,
    movieDetailStrings = movieDetailStringsPt,
)