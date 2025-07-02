package com.walcker.movies.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.walcker.movies.domain.models.Movie
import com.walcker.movies.ui.preview.mockData.movieTestData
import movies.composeapp.generated.resources.Res
import movies.composeapp.generated.resources.minecraft_movie
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
public fun MoviePoster(
    modifier: Modifier = Modifier,
    movie: Movie,
) {
    Column(
        modifier = modifier
            .width(140.dp),
    ) {
        Card(
            modifier = Modifier
                .width(140.dp)
                .height(210.dp),
            shape = RoundedCornerShape(12.dp)
        ) {
            Image(
                modifier = Modifier
                    .fillMaxSize(),
                painter = painterResource(Res.drawable.minecraft_movie),
                contentDescription = null,
                contentScale = ContentScale.Crop,
            )
        }

        Text(
            text = movie.title,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.titleMedium,
            maxLines = 1,
        )
    }
}

@Preview()
@Composable
public fun MoviePosterPreview() {
    MoviePoster(
        movie = movieTestData
    )
}
