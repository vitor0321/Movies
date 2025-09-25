package com.walcker.movies.features.movies.features.ui.features.movies.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.valentinilk.shimmer.shimmer
import com.walcker.movies.features.movies.features.ui.components.MovieDotsIndicator
import com.walcker.movies.features.movies.theme.MoviesAppTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
internal fun MovieSplashScreen() {
    Column(
        modifier = Modifier.shimmer(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Card(shape = RoundedCornerShape(12.dp)) {
                Box(
                    modifier = Modifier
                        .size(width = 300.dp, height = 350.dp)
                        .background(Color.LightGray),
                )
            }
            Card(
                shape = RoundedCornerShape(12.dp).copy(
                    topEnd = CornerSize(0.dp),
                    bottomEnd = CornerSize(0.dp),
                )
            ) {
                Box(
                    modifier = Modifier
                        .size(width = 300.dp, height = 350.dp)
                        .background(Color.LightGray),
                )
            }
        }
        MovieDotsIndicator(
            modifier = Modifier.padding(top = 8.dp),
            count = 5,
            selectedIndex = 0
        )
        Column(
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .padding(top = 16.dp)
                .padding(start = 16.dp),
        ) {
            Box(
                modifier = Modifier
                    .padding(top = 20.dp)
                    .padding(bottom = 12.dp)
                    .size(100.dp, 20.dp)
                    .background(Color.LightGray),
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Card(shape = RoundedCornerShape(12.dp)) {
                        Box(
                            modifier = Modifier
                                .size(140.dp, 200.dp)
                                .background(Color.LightGray),
                        )
                    }
                    Box(
                        modifier = Modifier
                            .padding(top = 8.dp)
                            .size(130.dp, 15.dp)
                            .background(Color.LightGray),
                    )
                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Card(shape = RoundedCornerShape(12.dp)) {
                        Box(
                            modifier = Modifier
                                .size(140.dp, 200.dp)
                                .background(Color.LightGray),
                        )
                    }
                    Box(
                        modifier = Modifier
                            .padding(top = 8.dp)
                            .size(130.dp, 15.dp)
                            .background(Color.LightGray),
                    )
                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Card(
                        shape = RoundedCornerShape(12.dp).copy(
                            topEnd = CornerSize(0.dp),
                            bottomEnd = CornerSize(0.dp),
                        )
                    ) {
                        Box(
                            modifier = Modifier
                                .size(140.dp, 200.dp)
                                .background(Color.LightGray),
                        )
                    }
                    Box(
                        modifier = Modifier
                            .padding(top = 8.dp)
                            .size(130.dp, 15.dp)
                            .background(Color.LightGray),
                    )
                }

            }
        }
        Column(
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .padding(top = 16.dp)
                .padding(start = 16.dp)
        ) {
            Box(
                modifier = Modifier
                    .padding(top = 20.dp)
                    .padding(bottom = 12.dp)
                    .size(210.dp, 20.dp)
                    .background(Color.LightGray),
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Card(
                        shape = RoundedCornerShape(12.dp).copy(
                            bottomEnd = CornerSize(0.dp),
                            bottomStart = CornerSize(0.dp),
                        )
                    ) {
                        Box(
                            modifier = Modifier
                                .size(140.dp, 200.dp)
                                .background(Color.LightGray),
                        )
                    }
                    Box(
                        modifier = Modifier
                            .padding(top = 8.dp)
                            .size(130.dp, 15.dp)
                            .background(Color.LightGray),
                    )
                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Card(
                        shape = RoundedCornerShape(12.dp).copy(
                            bottomEnd = CornerSize(0.dp),
                            bottomStart = CornerSize(0.dp),
                        )
                    ) {
                        Box(
                            modifier = Modifier
                                .size(140.dp, 200.dp)
                                .background(Color.LightGray),
                        )
                    }
                    Box(
                        modifier = Modifier
                            .padding(top = 8.dp)
                            .size(130.dp, 15.dp)
                            .background(Color.LightGray),
                    )
                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Card(
                        shape = RoundedCornerShape(12.dp).copy(
                            topEnd = CornerSize(0.dp),
                            bottomEnd = CornerSize(0.dp),
                            bottomStart = CornerSize(0.dp),
                        )
                    ) {
                        Box(
                            modifier = Modifier
                                .size(140.dp, 200.dp)
                                .background(Color.LightGray),
                        )
                    }
                    Box(
                        modifier = Modifier
                            .padding(top = 8.dp)
                            .size(130.dp, 15.dp)
                            .background(Color.LightGray),
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    MoviesAppTheme {
        MovieSplashScreen()
    }
}