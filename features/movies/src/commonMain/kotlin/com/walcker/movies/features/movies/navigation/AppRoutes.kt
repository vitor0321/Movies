package com.walcker.movies.features.movies.navigation

import kotlinx.serialization.Serializable

internal sealed interface AppRoutes {
    @Serializable
    data object MoviesList : AppRoutes

    @Serializable
    data class MovieDetail(val movieId: Int) : AppRoutes
}