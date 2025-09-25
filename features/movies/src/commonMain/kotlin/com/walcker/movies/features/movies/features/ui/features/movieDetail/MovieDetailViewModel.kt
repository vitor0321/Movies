package com.walcker.movies.features.movies.features.ui.features.movieDetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.walcker.movies.features.movies.features.domain.repository.MoviesRepository
import com.walcker.movies.features.movies.handle.handleMessageError
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

internal class MovieDetailViewModel internal constructor(
    private val movieId: Int,
    private val moviesRepository: MoviesRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow<MovieDetailUiState>(MovieDetailUiState.Loading)
    internal val uiState = _uiState.asStateFlow()

    init {
        getMovieDetail()
    }

    private fun getMovieDetail() {
        viewModelScope.launch {
            moviesRepository.getMovieDetail(movieId = movieId)
                .onSuccess { movie ->
                    _uiState.update { MovieDetailUiState.Success(movie) }
                }
                .onFailure { error ->
                    _uiState.update { MovieDetailUiState.Error(handleMessageError(exception = error)) }
                }
        }
    }
}