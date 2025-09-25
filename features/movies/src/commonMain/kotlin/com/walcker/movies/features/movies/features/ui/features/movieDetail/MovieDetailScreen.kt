package com.walcker.movies.features.movies.features.ui.features.movieDetail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.walcker.movies.features.movies.features.ui.components.MovieErrorContent
import com.walcker.movies.features.movies.features.ui.components.MovieLoadingContent
import com.walcker.movies.features.movies.features.ui.components.MovieTopAppBar
import com.walcker.movies.features.movies.features.ui.features.movieDetail.components.ModalBottomSheetDetail
import com.walcker.movies.features.movies.features.ui.features.movieDetail.components.MovieDetailSuccessContent
import com.walcker.movies.features.movies.features.ui.preview.mockData.movieTestData
import com.walcker.movies.features.movies.strings.LocalStrings
import com.walcker.movies.features.movies.strings.features.MovieDetailString
import com.walcker.movies.features.movies.strings.features.movieDetailStringsPt
import com.walcker.movies.features.movies.theme.MoviesAppTheme
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.ArrowLeft
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
internal fun MovieDetailRoute(
    viewModel: MovieDetailViewModel = koinViewModel(),
    onNavigationBack: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val strings = LocalStrings.current

    MovieDetailScreen(
        uiState = uiState,
        string = strings.movieDetailStrings,
        onNavigationBack = onNavigationBack,
    )
}

@Composable
internal fun MovieDetailScreen(
    uiState: MovieDetailUiState,
    string: MovieDetailString,
    onNavigationBack: () -> Unit,
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
        var youtubeVideoKey by remember { mutableStateOf<String?>(null) }
        var showModal by remember { mutableStateOf(false) }
        if (showModal && youtubeVideoKey != null) {
            ModalBottomSheetDetail(
                url = youtubeVideoKey.orEmpty(),
                onDismissRequest = { showModal = false }
            )
        }

        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            UiStateCheck(
                uiState = uiState,
                string = string,
                onWatchClick = {
                    youtubeVideoKey = it
                    showModal = !showModal
                }
            )
        }
    }
}

@Composable
private fun UiStateCheck(
    uiState: MovieDetailUiState,
    string: MovieDetailString,
    onWatchClick: (String) -> Unit,
) {
    when (uiState) {
        is MovieDetailUiState.Loading ->
            MovieLoadingContent()

        is MovieDetailUiState.Success ->
            MovieDetailSuccessContent(
                movie = uiState.movie,
                string = string,
                onWatchClick = { onWatchClick(it) },
            )

        is MovieDetailUiState.Error ->
            MovieErrorContent(message = uiState.message)
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
        )
    }
}

@Preview
@Composable
private fun DarkPreview() {
    MoviesAppTheme {
        MovieDetailScreen(
            uiState = MovieDetailUiState.Success(movieTestData.copy(moviesTrailerYouTubeKey = null)),
            string = movieDetailStringsPt,
            onNavigationBack = {},
        )
    }
}