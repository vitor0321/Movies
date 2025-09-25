package com.walcker.movies.features.movies

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.walcker.movies.core.di.coreModule
import com.walcker.movies.core.platformImpl
import com.walcker.movies.features.movies.di.moviesModule
import com.walcker.movies.features.movies.features.ui.features.movieDetail.MovieDetailRoute
import com.walcker.movies.features.movies.features.ui.features.movies.MoviesListRoute
import com.walcker.movies.features.movies.navigation.AppRoutes
import com.walcker.movies.features.movies.strings.ProvideStrings
import com.walcker.movies.features.movies.strings.rememberStrings
import com.walcker.movies.features.movies.theme.MoviesAppTheme
import org.koin.compose.KoinApplication

@Composable
public fun App(
    isDarkTheme: Boolean = isSystemInDarkTheme(),
) {
    val platform = platformImpl()
    val lyricist = rememberStrings(currentLanguageTag = platform.languageSystem)
    KoinApplication(
        application = { modules(modules = coreModule + moviesModule) }
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