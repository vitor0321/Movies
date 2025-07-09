package com.walcker.movies.features.ui.features.movieDetail.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.walcker.movies.features.ui.components.MovieCastMemberItem
import com.walcker.movies.features.ui.components.MovieRowList
import com.walcker.movies.features.ui.preview.mockData.movieTestData
import com.walcker.movies.theme.MoviesAppTheme
import kotlinx.collections.immutable.persistentListOf
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
internal fun BottomDetail(
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        MovieRowList(
            items = persistentListOf(movieTestData, movieTestData, movieTestData) // TODO move to real result
        ) { castMember, width ->
            MovieCastMemberItem(
                modifier = Modifier.width(width.dp),
                profilePictureUrl = castMember.posterUrl,
                name = castMember.title,
                character = castMember.overview
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Text(
                text = "Similar Movies  » See All » ", // TODO move to real result
                style = MaterialTheme.typography.bodySmall,
            )
        }
    }
}

@Preview
@Composable
internal fun BottomDetailPreview() {
    MoviesAppTheme {
        BottomDetail()
    }
}