package com.walcker.movies.strings

import cafe.adriel.lyricist.LyricistStrings
import com.walcker.movies.strings.features.MoviesListStrings
import com.walcker.movies.strings.features.moviesListStringsEn
import com.walcker.movies.strings.features.moviesListStringsPt

internal data class MoviesStrings(
    val appName: String,
    val moviesListStrings: MoviesListStrings,
)

internal object Locales {
    const val EN = "en"
    const val PT = "pt"
}

@LyricistStrings(languageTag = Locales.EN, default = true)
internal val EnStrings = MoviesStrings(
    appName = "Movies",
    moviesListStrings = moviesListStringsEn
)

@LyricistStrings(languageTag = Locales.PT)
internal val PtStrings = MoviesStrings(
    appName = "Filmes",
    moviesListStrings = moviesListStringsPt
)