package com.walcker.movies.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import coil3.compose.AsyncImage
import com.walcker.movies.domain.models.Movie
import movies.composeapp.generated.resources.Res
import movies.composeapp.generated.resources.error_image
import org.jetbrains.compose.resources.painterResource

@Composable
fun MovieAsyncImage(
    modifier: Modifier = Modifier,
    movie: Movie,
) {
    val loading = remember { mutableStateOf(false) }

    Box(modifier = modifier.fillMaxSize()) {
        AsyncImage(
            modifier = Modifier.fillMaxSize(),
            model = movie.posterUrl,
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