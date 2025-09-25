package com.walcker.movies.features.movies.features.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.walcker.movies.features.movies.features.domain.models.Movie
import com.walcker.movies.features.movies.features.ui.preview.mockData.movieTestData
import com.walcker.movies.features.movies.theme.MoviesAppTheme
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
internal fun MovieSection(
    modifier: Modifier = Modifier,
    title: String,
    movies: ImmutableList<Movie>,
    onPosterClick: (movieId: Int) -> Unit,
    onLoadMore: (() -> Unit)? = null,
) {
    val listState = rememberLazyListState()

    if (onLoadMore != null) {
        LaunchedEffect(listState.firstVisibleItemIndex, listState.layoutInfo.totalItemsCount) {
            val lastVisible = listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index
            val total = listState.layoutInfo.totalItemsCount
            if (lastVisible != null && total > 0 && lastVisible >= total - 3) {
                onLoadMore()
            }
        }
    }

    Column(
        modifier = modifier,
    ) {
        Text(
            text = title,
            modifier = Modifier.padding(horizontal = 16.dp),
            style = MaterialTheme.typography.titleLarge,
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.Bold
        )
        LazyRow(
            modifier = Modifier.padding(top = 8.dp),
            contentPadding = PaddingValues(horizontal = 16.dp),
            state = listState,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(movies) { movie ->
                MoviePoster(
                    movie = movie,
                    onPosterClick = onPosterClick
                )
            }
        }
    }
}

@Composable
@Preview
private fun Preview() {
    MoviesAppTheme {
        MovieSection(
            title = "Filmes Populares",
            movies = List(10) {
                movieTestData
            }.toImmutableList(),
            onPosterClick = {}
        )
    }
}