package com.walcker.movies.features.ui.features.movieDetail

internal interface MovieDetailInternalRoute {
    data object OnFetchTrailerUrl : MovieDetailInternalRoute
    data object OnResetTrailerUrl : MovieDetailInternalRoute
}