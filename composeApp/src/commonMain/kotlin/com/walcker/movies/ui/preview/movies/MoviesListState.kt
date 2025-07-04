package com.walcker.movies.ui.preview.movies

import com.walcker.movies.ui.movies.MoviesListState
import org.jetbrains.compose.ui.tooling.preview.PreviewParameterProvider

internal class MoviesListStateProvider : PreviewParameterProvider<MoviesListState> {
    override val values: Sequence<MoviesListState>
        get() = sequenceOf(
            MoviesListState(
                title = "Popular Movies"
            )
        )
}