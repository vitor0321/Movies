package com.walcker.movies.ui.preview.movies

import com.walcker.movies.domain.models.MovieSection
import com.walcker.movies.ui.movies.MoviesListViewModel
import com.walcker.movies.ui.preview.mockData.movieTestData
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toImmutableList
import org.jetbrains.compose.ui.tooling.preview.PreviewParameterProvider

internal class MoviesListUiStateProvider : PreviewParameterProvider<MoviesListViewModel.MoviesListUiState> {
    override val values: Sequence<MoviesListViewModel.MoviesListUiState>
        get() = sequenceOf(
            MoviesListViewModel.MoviesListUiState.Success(
                movies = persistentListOf(
                    MovieSection(
                        sectionType = MovieSection.SectionType.POPULAR,
                        movies = List(10) { movieTestData }.toImmutableList()
                    ),
                    MovieSection(
                        sectionType = MovieSection.SectionType.TOP_RATED,
                        movies = List(10) { movieTestData }.toImmutableList()
                    ),
                    MovieSection(
                        sectionType = MovieSection.SectionType.UPCOMING,
                        movies = List(10) { movieTestData }.toImmutableList()
                    )
                )
            ),
            MoviesListViewModel.MoviesListUiState.Loading,
            MoviesListViewModel.MoviesListUiState.Error(
                message = "Something went wrong. Please try again later."
            )
        )
}