package com.walcker.movies.features.domain.models

internal data class Movie(
    val id: Int,
    val title: String,
    val overview: String,
    val posterUrl: String,
)