package com.walcker.movies

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.walcker.movies.navigation.AppRoutes
import com.walcker.movies.ui.movies.MoviesListRoute
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    val platform = getPlatform()

    MaterialTheme {
        val navController = rememberNavController()
        NavHost(
            navController = navController,
            startDestination = AppRoutes.MoviesList
        ) {
            composable<AppRoutes.MoviesList> {
                MoviesListRoute()
            }
            composable<AppRoutes.MovieDetails> {

            }
        }
    }
}