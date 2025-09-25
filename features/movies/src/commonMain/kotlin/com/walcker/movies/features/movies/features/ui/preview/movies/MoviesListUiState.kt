package com.walcker.movies.features.movies.features.ui.preview.movies

import com.walcker.movies.features.movies.features.ui.features.movies.MoviesListUiState
import com.walcker.movies.features.movies.features.ui.preview.mockData.movieSectionTestData
import org.jetbrains.compose.ui.tooling.preview.PreviewParameterProvider

internal class MoviesListUiStateProvider : PreviewParameterProvider<MoviesListUiState> {
    override val values: Sequence<MoviesListUiState>
        get() = sequenceOf(
            MoviesListUiState.Success(
                movies = movieSectionTestData,
            ),
            MoviesListUiState.Loading,
            MoviesListUiState.Error(
                message = "Something went wrong. Please try again later."
            )
        )
}