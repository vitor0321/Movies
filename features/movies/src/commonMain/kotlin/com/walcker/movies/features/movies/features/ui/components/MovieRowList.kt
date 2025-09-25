package com.walcker.movies.features.movies.features.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.walcker.movies.features.movies.features.ui.preview.mockData.movieTestData
import com.walcker.movies.features.movies.theme.MoviesAppTheme
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
internal fun <T> MovieRowList(
    items: ImmutableList<T>,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(horizontal = 16.dp),
    horizontalArrangement: Arrangement.Horizontal = Arrangement.spacedBy(16.dp),
    itemWidthFraction: Float = 0.65f,
    itemContent: @Composable (item: T, width: Float) -> Unit
) {
    BoxWithConstraints(
        modifier = modifier.fillMaxWidth()
    ) {
        val itemWidth = this.maxWidth * itemWidthFraction
        LazyRow(
            contentPadding = contentPadding,
            horizontalArrangement = horizontalArrangement
        ) {
            items(items.size) { index ->
                itemContent(items[index], itemWidth.value)
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    MoviesAppTheme {
        MovieRowList(
            items = persistentListOf(
                movieTestData,
                movieTestData,
                movieTestData
            )
        ) { castMember, width ->
            MovieCastMemberItem(
                modifier = Modifier.width(width.dp),
                imageUrl = castMember.posterUrl,
                name = castMember.title,
                character = castMember.overview
            )
        }
    }
}