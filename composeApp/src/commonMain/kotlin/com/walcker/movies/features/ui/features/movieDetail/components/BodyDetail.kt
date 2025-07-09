package com.walcker.movies.features.ui.features.movieDetail.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.walcker.movies.features.domain.models.Movie
import com.walcker.movies.features.ui.components.MovieBadge
import com.walcker.movies.features.ui.components.MovieInfoItem
import com.walcker.movies.features.ui.preview.mockData.movieTestData
import com.walcker.movies.theme.MoviesAppTheme
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.Calendar
import compose.icons.fontawesomeicons.solid.Clock
import compose.icons.fontawesomeicons.solid.Play
import compose.icons.fontawesomeicons.solid.Star
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
internal fun BodyDetail(
    modifier: Modifier = Modifier,
    movie: Movie,
) {
    Column(
        modifier = modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = movie.title,
            modifier = Modifier.padding(horizontal = 16.dp),
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.headlineMedium,
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            MovieInfoItem(
                icon = FontAwesomeIcons.Solid.Star,
                text = "7.6", // TODO move to real result
            )
            Spacer(modifier = Modifier.width(16.dp))
            MovieInfoItem(
                icon = FontAwesomeIcons.Solid.Clock,
                text = "2h 36 min", // TODO move to real result
            )
            Spacer(modifier = Modifier.width(16.dp))
            MovieInfoItem(
                icon = FontAwesomeIcons.Solid.Calendar,
                text = "2022", // TODO move to real result
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            MovieBadge(text = "Action") // TODO move to real result
        }
        Spacer(modifier = Modifier.height(16.dp))
        ElevatedButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            onClick = { /* Handle button click */ }, // TODO move to real result
        ) {
            Icon(
                imageVector = FontAwesomeIcons.Solid.Play,
                modifier = Modifier.size(12.dp),
                contentDescription = null,
            )
            Text(
                text = "Watch Now", // TODO move to real result
                modifier = Modifier.padding(start = 16.dp),
                fontWeight = FontWeight.Medium,
                style = MaterialTheme.typography.bodyMedium,
            )
        }
    }
}

@Preview
@Composable
internal fun BodyDetailPreview() {
    MoviesAppTheme {
        BodyDetail(
            movie = movieTestData
        )
    }
}