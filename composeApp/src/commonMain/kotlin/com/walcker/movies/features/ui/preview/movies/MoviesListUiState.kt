package com.walcker.movies.features.ui.preview.movies

import com.walcker.movies.features.ui.features.movies.MoviesListViewModel
import com.walcker.movies.features.ui.preview.mockData.movieSectionTestData
import org.jetbrains.compose.ui.tooling.preview.PreviewParameterProvider

internal class MoviesListUiStateProvider : PreviewParameterProvider<MoviesListViewModel.MoviesListUiState> {
    override val values: Sequence<MoviesListViewModel.MoviesListUiState>
        get() = sequenceOf(
            MoviesListViewModel.MoviesListUiState.Success(
                movies = movieSectionTestData,
            ),
            MoviesListViewModel.MoviesListUiState.Loading,
            MoviesListViewModel.MoviesListUiState.Error(
                message = "Something went wrong. Please try again later."
            )
        )
}