package com.walcker.movies.features.ui.features.movieDetail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.walcker.movies.features.ui.components.MovieTopAppBar
import com.walcker.movies.features.ui.components.MoviesErrorContent
import com.walcker.movies.features.ui.components.MoviesLoadingContent
import com.walcker.movies.features.ui.features.movieDetail.components.MovieDetailSuccessContent
import com.walcker.movies.features.ui.preview.mockData.movieTestData
import com.walcker.movies.strings.LocalStrings
import com.walcker.movies.strings.features.MovieDetailString
import com.walcker.movies.strings.features.movieDetailStringsPt
import com.walcker.movies.theme.MoviesAppTheme
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.ArrowLeft
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
internal fun MovieDetailRoute(
    viewModel: MovieDetailViewModel = koinViewModel(),
    onNavigationBack: () -> Unit,
    onOpenTrailer: (String) -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val trailerUrl by viewModel.trailerUrl.collectAsStateWithLifecycle()
    val strings = LocalStrings.current
    val onEvent: (MovieDetailInternalRoute) -> Unit = remember { { viewModel.onEvent(it) } }

    LaunchedEffect(key1 = trailerUrl) {
        trailerUrl?.let { url ->
            onOpenTrailer(url)
            onEvent(MovieDetailInternalRoute.OnResetTrailerUrl)
        }
    }

    MovieDetailScreen(
        uiState = uiState,
        string = strings.movieDetailStrings,
        onNavigationBack = onNavigationBack,
        onEvent = { onEvent(it) },
    )
}

@Composable
internal fun MovieDetailScreen(
    uiState: MovieDetailUiState,
    string: MovieDetailString,
    onNavigationBack: () -> Unit,
    onEvent: (MovieDetailInternalRoute) -> Unit
) {
    Scaffold(
        topBar = {
            MovieTopAppBar(
                title = string.title,
                icon = FontAwesomeIcons.Solid.ArrowLeft,
                onNavigationBack = onNavigationBack,
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            UiStateCheck(
                uiState = uiState,
                string = string,
                onWatchClick = { onEvent(MovieDetailInternalRoute.OnFetchTrailerUrl) }
            )
        }
    }
}

@Composable
private fun UiStateCheck(
    uiState: MovieDetailUiState,
    string: MovieDetailString,
    onWatchClick: () -> Unit
) {
    when (uiState) {
        is MovieDetailUiState.Loading ->
            MoviesLoadingContent()

        is MovieDetailUiState.Success ->
            MovieDetailSuccessContent(
                movie = uiState.movie,
                string = string,
                onWatchClick = { onWatchClick() },
            )

        is MovieDetailUiState.Error ->
            MoviesErrorContent(message = uiState.message)
    }
}

@Preview
@Composable
private fun LightPreview() {
    MoviesAppTheme(isDarkTheme = false) {
        MovieDetailScreen(
            uiState = MovieDetailUiState.Success(movieTestData),
            string = movieDetailStringsPt,
            onNavigationBack = {},
            onEvent = {},
        )
    }
}

@Preview
@Composable
private fun DarkPreview() {
    MoviesAppTheme {
        MovieDetailScreen(
            uiState = MovieDetailUiState.Success(movieTestData),
            string = movieDetailStringsPt,
            onNavigationBack = {},
            onEvent = {},
        )
    }
}