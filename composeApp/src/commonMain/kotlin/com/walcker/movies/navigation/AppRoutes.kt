package com.walcker.movies.navigation

import kotlinx.serialization.Serializable

sealed interface AppRoutes {
    @Serializable
    data object MoviesList : AppRoutes

    @Serializable
    data class MovieDetails(val id: Int) : AppRoutes
}