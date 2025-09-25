package com.walcker.movies.features.movies.features.ui.features.movieDetail

import com.walcker.movies.features.movies.features.domain.models.Movie

internal sealed interface MovieDetailUiState {
    data object Loading : MovieDetailUiState
    data class Success(val movie: Movie) : MovieDetailUiState
    data class Error(val message: String) : MovieDetailUiState
}