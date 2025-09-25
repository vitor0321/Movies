package com.walcker.movies.features.movies.features.ui.features.movieDetail.components

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
import com.walcker.movies.features.movies.features.domain.models.Movie
import com.walcker.movies.features.movies.features.ui.components.MovieCastMemberItem
import com.walcker.movies.features.movies.features.ui.components.MovieRowList
import com.walcker.movies.features.movies.features.ui.preview.mockData.movieTestData
import com.walcker.movies.features.movies.theme.MoviesAppTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
internal fun BottomDetail(
    modifier: Modifier = Modifier,
    movie: Movie,
) {
    Column(modifier = modifier) {
        movie.castMembers?.let { castMembers ->
            MovieRowList(
                items = castMembers
            ) { castMember, width ->
                MovieCastMemberItem(
                    modifier = Modifier.width(width.dp),
                    imageUrl = castMember.profileUrl,
                    name = castMember.name,
                    character = castMember.character
                )
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Text(
                text = movie.overview,
                style = MaterialTheme.typography.bodyMedium,
            )
        }
    }
}

@Preview
@Composable
private fun Preview() {
    MoviesAppTheme {
        BottomDetail(
            movie = movieTestData,
        )
    }
}