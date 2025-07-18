package com.walcker.movies.features.ui.features.movieDetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.walcker.movies.features.domain.repository.MoviesRepository
import com.walcker.movies.handle.handleMessageError
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

    private val _trailerUrl = MutableStateFlow<String?>(null)
    internal val trailerUrl = _trailerUrl.asStateFlow()

    init {
        getMovieDetail()
    }

    internal fun onEvent(onEvent: MovieDetailInternalRoute) {
        when (onEvent) {
            is MovieDetailInternalRoute.OnFetchTrailerUrl -> fetchTrailerUrl()
            is MovieDetailInternalRoute.OnResetTrailerUrl -> resetTrailerUrl()
        }
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

    private fun fetchTrailerUrl() {
        viewModelScope.launch {
            val result = moviesRepository.getTrailerUrl(movieId)
            _trailerUrl.value = result.getOrNull()
        }
    }

    private fun resetTrailerUrl() {
        _trailerUrl.value = null
    }
}