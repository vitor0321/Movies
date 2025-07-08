package com.walcker.movies.features.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class MovieListResponse(
    val results: List<MovieResponse>
)

@Serializable
internal data class MovieResponse(
    val id: Int,
    val title: String,
    val overview: String,
    @SerialName("poster_path")
    val posterPath: String,
)