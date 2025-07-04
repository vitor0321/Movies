package com.walcker.movies.ui.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.walcker.movies.domain.models.MovieSection
import com.walcker.movies.domain.repository.MoviesRepository
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

internal class MoviesListViewModel internal constructor(
    private val moviesRepository: MoviesRepository
) : ViewModel() {

    private val _moviesListUiState = MutableStateFlow<MoviesListUiState>(MoviesListUiState.Loading)
    internal val moviesListUiState = _moviesListUiState.asStateFlow()

    private val _moviesListData = MutableStateFlow<MoviesListState>(MoviesListState())
    internal val moviesListData = _moviesListData.asStateFlow()

    init {
        getMovieSections()
    }

    internal fun onEvent(event: MoviesListInternalRoute) {
        when (event) {
            is MoviesListInternalRoute.OnTitleChecked ->
                updateTitle(section = event.sectionType)
        }
    }

    private fun getMovieSections() {
        viewModelScope.launch {
            runCatching {
                moviesRepository.getMoviesSections()
            }.onSuccess { movieSections ->
                _moviesListUiState.update {
                    MoviesListUiState.Success(movies = movieSections.toImmutableList())
                }
            }.onFailure { error ->
                _moviesListUiState.update {
                    MoviesListUiState.Error(message = error.message ?: "Unknown error")
                }
            }
        }
    }

    private fun updateTitle(section: MovieSection.SectionType) {
        _moviesListData.update {
            MoviesListState(title = section.title)
        }
    }

    internal sealed interface MoviesListUiState {
        data object Loading : MoviesListUiState
        data class Success(val movies: ImmutableList<MovieSection>) : MoviesListUiState
        data class Error(val message: String) : MoviesListUiState
    }
}