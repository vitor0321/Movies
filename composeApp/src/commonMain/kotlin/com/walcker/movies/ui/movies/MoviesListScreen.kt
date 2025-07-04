package com.walcker.movies.ui.movies

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.walcker.movies.data.repository.MoviesRepositoryImpl
import com.walcker.movies.domain.models.MovieSection
import com.walcker.movies.ui.components.MovieSection
import com.walcker.movies.ui.preview.movies.MoviesListStateProvider
import com.walcker.movies.ui.preview.movies.MoviesListUiStateProvider
import kotlinx.collections.immutable.ImmutableList
import movies.composeapp.generated.resources.Res
import movies.composeapp.generated.resources.error_image
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.jetbrains.compose.ui.tooling.preview.PreviewParameter

@Composable
internal fun MoviesListRoute(
    viewModel: MoviesListViewModel = viewModel {
        MoviesListViewModel(
            moviesRepository = MoviesRepositoryImpl()
        )
    },
) {
    val moviesListUiState by viewModel.moviesListUiState.collectAsStateWithLifecycle()
    val moviesListData by viewModel.moviesListData.collectAsStateWithLifecycle()
    val onEvent: (MoviesListInternalRoute) -> Unit = { { viewModel.onEvent(event = it) } }

    MoviesListScreen(
        moviesListUiState = moviesListUiState,
        titleSection = moviesListData.title,
        onEvent = onEvent,
    )
}

@Composable
private fun MoviesListScreen(
    moviesListUiState: MoviesListViewModel.MoviesListUiState,
    titleSection: String,
    onEvent: (MoviesListInternalRoute) -> Unit,
) {
    Scaffold { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            when (moviesListUiState) {
                is MoviesListViewModel.MoviesListUiState.Loading ->
                    MoviesListLoadingContent()

                is MoviesListViewModel.MoviesListUiState.Success ->
                    MoviesListSuccessContent(
                        movies = moviesListUiState.movies,
                        title = titleSection,
                        onTitleCheck = { onEvent(MoviesListInternalRoute.OnTitleChecked(sectionType = it)) }
                    )

                is MoviesListViewModel.MoviesListUiState.Error ->
                    MoviesListErrorContent(message = moviesListUiState.message)
            }
        }
    }
}

@Composable
private fun MoviesListSuccessContent(
    movies: ImmutableList<MovieSection>,
    title: String,
    onTitleCheck: (MovieSection.SectionType) -> Unit,
) {
    LazyColumn(
        contentPadding = PaddingValues(vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(32.dp)
    ) {
        items(items = movies) { movieSection ->
            onTitleCheck(movieSection.sectionType)
            MovieSection(
                title = title,
                movies = movieSection.movies
            )
        }
    }
}

@Composable
private fun MoviesListLoadingContent() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(color = Color.LightGray)
    }
}

@Composable
private fun MoviesListErrorContent(message: String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painterResource(resource = Res.drawable.error_image),
            contentDescription = null,
        )
        Text(
            text = message,
            textAlign = TextAlign.Center,
        )
    }
}

@Preview
@Composable()
private fun MoviesListScreenPreview(
    @PreviewParameter(MoviesListUiStateProvider::class) uiState: MoviesListViewModel.MoviesListUiState,
    @PreviewParameter(MoviesListStateProvider::class) state: MoviesListState,
) {
    MaterialTheme {
        MoviesListScreen(
            moviesListUiState = uiState,
            titleSection = state.title,
            onEvent = { }
        )
    }
}