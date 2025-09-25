package com.walcker.movies.features.movies.features.ui.features.movies

import com.walcker.movies.features.movies.features.domain.models.MovieSection
import kotlinx.collections.immutable.ImmutableList

internal sealed interface MoviesListUiState {
    data object Loading : MoviesListUiState
    data class Success(val movies: ImmutableList<MovieSection>) : MoviesListUiState
    data class Error(val message: String) : MoviesListUiState
}