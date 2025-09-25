package com.walcker.movies.features.movies.features.ui.features.movies

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.walcker.movies.features.movies.features.domain.models.MovieSection
import com.walcker.movies.features.movies.features.ui.components.MovieErrorContent
import com.walcker.movies.features.movies.features.ui.components.MovieTopAppBar
import com.walcker.movies.features.movies.features.ui.features.movies.components.MovieSplashScreen
import com.walcker.movies.features.movies.features.ui.features.movies.components.MoviesListSuccessContent
import com.walcker.movies.features.movies.features.ui.preview.movies.MoviesListUiStateProvider
import com.walcker.movies.features.movies.strings.LocalStrings
import com.walcker.movies.features.movies.strings.features.MoviesListStrings
import com.walcker.movies.features.movies.strings.features.moviesListStringsPt
import com.walcker.movies.features.movies.theme.MoviesAppTheme
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
    val onEvent: (MoviesListInternalRoute) -> Unit = remember { { viewModel.onEvent(it) } }

    MoviesListScreen(
        uiState = uiState,
        strings = strings.moviesListStrings,
        onPosterClick = navigateToMovieDetails,
        onEvent = onEvent
    )
}

@Composable
internal fun MoviesListScreen(
    uiState: MoviesListUiState,
    strings: MoviesListStrings,
    onPosterClick: (movieId: Int) -> Unit,
    onEvent: (MoviesListInternalRoute) -> Unit
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
                onLoadMore = { onEvent(MoviesListInternalRoute.OnLoadNextPage(sectionType = it)) }
            )
        }
    }
}

@Composable
private fun UiStateCheck(
    uiState: MoviesListUiState,
    strings: MoviesListStrings,
    onPosterClick: (Int) -> Unit,
    onLoadMore: (MovieSection.SectionType) -> Unit,
) {
    when (uiState) {
        is MoviesListUiState.Loading ->
            MovieSplashScreen()

        is MoviesListUiState.Success ->
            MoviesListSuccessContent(
                strings = strings,
                movies = uiState.movies,
                onPosterClick = onPosterClick,
                onLoadMore = onLoadMore
            )

        is MoviesListUiState.Error ->
            MovieErrorContent(message = uiState.message)
    }
}

@Preview
@Composable()
private fun Preview(
    @PreviewParameter(MoviesListUiStateProvider::class) uiState: MoviesListUiState,
) {
    MoviesAppTheme {
        MoviesListScreen(
            uiState = uiState,
            strings = moviesListStringsPt,
            onPosterClick = { },
            onEvent = {},
        )
    }
}