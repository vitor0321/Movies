package com.walcker.movies.strings

import cafe.adriel.lyricist.LyricistStrings

internal data class AppStrings(
    val appName: String,
)

internal object Locales {
    const val EN = "en"
    const val PT = "pt"
}

@LyricistStrings(languageTag = Locales.EN, default = true)
internal val EnStrings = AppStrings(
    appName = "Movies",
)

@LyricistStrings(languageTag = Locales.PT)
internal val PtStrings = AppStrings(
    appName = "Filmes",
)