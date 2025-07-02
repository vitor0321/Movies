package com.walcker.movies.domain.models

data class Movie(
    val id: Int,
    val title: String,
    val overview: String,
    val posterUrl: String,
)