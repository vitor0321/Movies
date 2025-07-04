package com.walcker.movies.domain.models

internal data class Movie(
    val id: Int,
    val title: String,
    val overview: String,
    val posterUrl: String,
)