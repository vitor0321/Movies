package com.walcker.movies.features.ui.features.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.walcker.movies.features.domain.models.MovieSection
import com.walcker.movies.features.domain.models.MoviesPagination
import com.walcker.movies.features.domain.repository.MoviesRepository
import com.walcker.movies.handle.handleMessageError
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

internal class MoviesListViewModel internal constructor(
    private val moviesRepository: MoviesRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow<MoviesListUiState>(MoviesListUiState.Loading)
    internal val uiState = _uiState.asStateFlow()

    private var currentPagination = MoviesPagination()
    private val loadedSections = mutableMapOf<MovieSection.SectionType, MovieSection>()

    init {
        getMovieSections()
    }

    private fun getMovieSections() {
        viewModelScope.launch {
            moviesRepository.getMoviesSections(currentPagination)
                .onSuccess { movieSections ->
                    updateLoadedSections(movieSections)

                    val orderedSections = MovieSection.SectionType.entries
                        .mapNotNull { loadedSections[it] }

                    _uiState.update {
                        MoviesListUiState.Success(movies = orderedSections.toImmutableList())
                    }
                }
                .onFailure { error ->
                    _uiState.update {
                        MoviesListUiState.Error(message = handleMessageError(exception = error))
                    }
                }
        }
    }

    internal fun loadNextPage(sectionType: MovieSection.SectionType) {
        currentPagination = currentPagination.increment(sectionType)
        getMovieSections()
    }

    private fun updateLoadedSections(newSections: List<MovieSection>) {
        newSections.forEach { section ->
            val current = loadedSections[section.sectionType]
            loadedSections[section.sectionType] = section.copy(
                movies = if (current != null)
                    (current.movies + section.movies).toImmutableList()
                else section.movies
            )
        }
    }

    internal sealed interface MoviesListUiState {
        data object Loading : MoviesListUiState
        data class Success(val movies: ImmutableList<MovieSection>) : MoviesListUiState
        data class Error(val message: String) : MoviesListUiState
    }
}