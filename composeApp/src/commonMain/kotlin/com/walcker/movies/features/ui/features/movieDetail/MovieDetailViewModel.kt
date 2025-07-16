package com.walcker.movies.features.ui.features.movieDetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.walcker.movies.features.domain.models.Movie
import com.walcker.movies.features.domain.repository.MoviesRepository
import com.walcker.movies.handle.handleMessageError
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

internal class MovieDetailViewModel internal constructor(
    private val movieId: Int,
    private val moviesRepository: MoviesRepository,
    private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _uiState = MutableStateFlow<MovieDetailUiState>(MovieDetailUiState.Loading)
    internal val uiState = _uiState.asStateFlow()

    init {
        getMovieDetail()
    }

    private fun getMovieDetail() {
        viewModelScope.launch(context = dispatcher) {
            moviesRepository.getMovieDetail(movieId = movieId)
                .onSuccess { movie ->
                    _uiState.update { MovieDetailUiState.Success(movie) }
                }
                .onFailure { error ->
                    _uiState.update { MovieDetailUiState.Error(handleMessageError(exception = error)) }
                }
        }
    }

    internal sealed interface MovieDetailUiState {
        data object Loading : MovieDetailUiState
        data class Success(val movie: Movie) : MovieDetailUiState
        data class Error(val message: String) : MovieDetailUiState
    }
}