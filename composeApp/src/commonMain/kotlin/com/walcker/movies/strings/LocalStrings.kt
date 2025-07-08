package com.walcker.movies.strings

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import cafe.adriel.lyricist.Lyricist

internal val LocalStrings = staticCompositionLocalOf<MoviesStrings> { error("LocalStrings not provided") }

private fun createLyricist(
    defaultLanguageTag: String = Locales.EN,
): Lyricist<MoviesStrings> {
    return Lyricist(
        defaultLanguageTag = defaultLanguageTag,
        translations = mapOf(
            Locales.EN to EnStrings,
            Locales.PT to PtStrings
        )
    )
}

@Composable
internal fun rememberStrings(
    defaultLanguageTag: String = Locales.EN,
    currentLanguageTag: String = defaultLanguageTag
): Lyricist<MoviesStrings> {
    return remember(currentLanguageTag) {
        createLyricist(
            defaultLanguageTag = defaultLanguageTag
        ).apply {
            languageTag = currentLanguageTag
        }
    }
}