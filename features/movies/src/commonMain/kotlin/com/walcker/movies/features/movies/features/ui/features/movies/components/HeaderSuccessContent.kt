package com.walcker.movies.features.movies.features.ui.features.movies.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.walcker.movies.features.movies.features.domain.models.Movie
import com.walcker.movies.features.movies.features.ui.components.MovieAsyncImage
import com.walcker.movies.features.movies.features.ui.components.MovieDotsIndicator
import com.walcker.movies.features.movies.features.ui.preview.mockData.movieTestData
import com.walcker.movies.features.movies.handle.getCenterItemIndex
import com.walcker.movies.features.movies.theme.MoviesAppTheme
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
internal fun HeaderSuccessContent(
    modifier: Modifier = Modifier,
    movies: ImmutableList<Movie>,
    onPosterClick: (movieId: Int) -> Unit,
) {
    val listState = rememberLazyListState()
    val selectedIndex by remember {
        derivedStateOf { getCenterItemIndex(listState) }
    }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp),
            state = listState,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            itemsIndexed(movies) { index, movie ->
                Card(
                    modifier = Modifier
                        .width(300.dp)
                        .height(350.dp),
                    shape = RoundedCornerShape(12.dp),
                ) {
                    MovieAsyncImage(
                        imageUrl = movie.posterUrl,
                        modifier = Modifier
                            .clip(MaterialTheme.shapes.large)
                            .clickable(
                                enabled = true,
                                onClick = { onPosterClick(movie.id) },
                            )
                    )
                }
            }
        }
        MovieDotsIndicator(
            modifier = Modifier.padding(top = 8.dp),
            count = movies.size,
            selectedIndex = selectedIndex
        )
    }
}

@Preview
@Composable
private fun Preview() {
    MoviesAppTheme {
        HeaderSuccessContent(
            movies = List(5) {
                movieTestData
            }.toImmutableList(),
            onPosterClick = {},
        )
    }
}