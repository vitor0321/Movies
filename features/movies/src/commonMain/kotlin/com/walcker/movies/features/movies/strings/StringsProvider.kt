package com.walcker.movies.features.movies.strings

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import cafe.adriel.lyricist.Lyricist

@Composable
internal fun ProvideStrings(
    lyricist: Lyricist<MoviesStrings> = rememberStrings(),
    content: @Composable () -> Unit
) {

    CompositionLocalProvider(LocalStrings provides lyricist.strings) {
        content()
    }
}