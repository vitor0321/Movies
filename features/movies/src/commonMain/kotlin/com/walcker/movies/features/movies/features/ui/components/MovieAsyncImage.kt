package com.walcker.movies.features.movies.features.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.walcker.movies.features.movies.features.ui.preview.mockData.movieTestData
import com.walcker.movies.features.movies.theme.MoviesAppTheme
import flickly.features.movies.generated.resources.Res
import flickly.features.movies.generated.resources.error_image
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
internal fun MovieAsyncImage(
    modifier: Modifier = Modifier,
    imageUrl: String?,
) {
    val loading = remember { mutableStateOf(false) }

    Box(modifier = modifier.fillMaxSize()) {
        AsyncImage(
            modifier = Modifier.fillMaxSize(),
            model = imageUrl,
            error = painterResource(resource = Res.drawable.error_image),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            onLoading = { loading.value = true },
            onError = { loading.value = false },
            onSuccess = { loading.value = false }
        )

        if (loading.value) {
            Box(
                modifier = Modifier
                    .background(color = White)
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = Color.LightGray)
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    MoviesAppTheme {
        Card(
            modifier = Modifier
                .width(140.dp)
                .height(210.dp),
        ) {
            MovieAsyncImage(imageUrl = movieTestData.posterUrl)
        }
    }
}