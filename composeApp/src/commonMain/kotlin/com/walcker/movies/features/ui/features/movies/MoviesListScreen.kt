package com.walcker.movies.features.ui.features.movies

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.walcker.movies.features.domain.models.MovieSection
import com.walcker.movies.features.ui.components.MovieTopAppBar
import com.walcker.movies.features.ui.components.MoviesErrorContent
import com.walcker.movies.features.ui.components.MoviesLoadingContent
import com.walcker.movies.features.ui.features.movies.components.MoviesListSuccessContent
import com.walcker.movies.features.ui.preview.movies.MoviesListUiStateProvider
import com.walcker.movies.strings.LocalStrings
import com.walcker.movies.strings.features.MoviesListStrings
import com.walcker.movies.strings.features.moviesListStringsPt
import com.walcker.movies.theme.MoviesAppTheme
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.jetbrains.compose.ui.tooling.preview.PreviewParameter
import org.koin.compose.viewmodel.koinViewModel

@Composable
internal fun MoviesListRoute(
    viewModel: MoviesListViewModel = koinViewModel(),
    navigateToMovieDetails: (Int) -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val strings = LocalStrings.current
    val loadNextPage: (MovieSection.SectionType) -> Unit =
        remember(key1 = viewModel) { { sectionType -> viewModel.loadNextPage(sectionType) } }

    MoviesListScreen(
        uiState = uiState,
        strings = strings.moviesListStrings,
        onPosterClick = navigateToMovieDetails,
        onLoadMore = loadNextPage
    )
}

@Composable
private fun MoviesListScreen(
    uiState: MoviesListViewModel.MoviesListUiState,
    strings: MoviesListStrings,
    onPosterClick: (movieId: Int) -> Unit,
    onLoadMore: (MovieSection.SectionType) -> Unit
) {

    Scaffold(
        topBar = { MovieTopAppBar(title = strings.appName) }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            UiStateCheck(
                uiState = uiState,
                strings = strings,
                onPosterClick = onPosterClick,
                onLoadMore = onLoadMore
            )
        }
    }
}

@Composable
private fun UiStateCheck(
    uiState: MoviesListViewModel.MoviesListUiState,
    strings: MoviesListStrings,
    onPosterClick: (Int) -> Unit,
    onLoadMore: (MovieSection.SectionType) -> Unit,
) {
    when (uiState) {
        is MoviesListViewModel.MoviesListUiState.Loading ->
            MoviesLoadingContent()

        is MoviesListViewModel.MoviesListUiState.Success ->
            MoviesListSuccessContent(
                strings = strings,
                movies = uiState.movies,
                onPosterClick = onPosterClick,
                onLoadMore = onLoadMore
            )

        is MoviesListViewModel.MoviesListUiState.Error ->
            MoviesErrorContent(message = uiState.message)
    }
}

@Preview
@Composable()
private fun Preview(
    @PreviewParameter(MoviesListUiStateProvider::class) uiState: MoviesListViewModel.MoviesListUiState,
) {
    MoviesAppTheme {
        MoviesListScreen(
            uiState = uiState,
            strings = moviesListStringsPt,
            onPosterClick = { },
            onLoadMore = { }
        )
    }
}