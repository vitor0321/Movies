package com.walcker.movies

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.walcker.movies.di.coreModule
import com.walcker.movies.features.ui.features.movieDetail.MovieDetailRoute
import com.walcker.movies.features.ui.features.movies.MoviesListRoute
import com.walcker.movies.navigation.AppRoutes
import com.walcker.movies.strings.ProvideStrings
import com.walcker.movies.strings.rememberStrings
import com.walcker.movies.theme.MoviesAppTheme
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinApplication

@Composable
@Preview
public fun App(
    isDarkTheme: Boolean = isSystemInDarkTheme(),
) {
    val platform = platformImpl()
    val lyricist = rememberStrings(currentLanguageTag = platform.languageSystem)

    KoinApplication(
        application = { modules(modules = coreModule) }
    ) {
        ProvideStrings(lyricist) {
            MoviesAppTheme(isDarkTheme = isDarkTheme) {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = AppRoutes.MoviesList
                ) {
                    composable<AppRoutes.MoviesList> {
                        MoviesListRoute(
                            navigateToMovieDetails = { movieId ->
                                navController.navigate(AppRoutes.MovieDetail(movieId = movieId))
                            }
                        )
                    }
                    composable<AppRoutes.MovieDetail> {
                        MovieDetailRoute(
                            onNavigationBack = {
                                navController.popBackStack()
                            }
                        )
                    }
                }
            }
        }
    }
}