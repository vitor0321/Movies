package com.walcker.movies.features.movies.features.domain.models

import kotlinx.collections.immutable.ImmutableList

internal data class Movie(
    val id: Int,
    val title: String,
    val overview: String,
    val posterUrl: String,
    val genres: ImmutableList<Genre>?,
    val year: String,
    val duration: String?,
    val rating: String,
    val castMembers: ImmutableList<CastMember>?,
    val moviesTrailerYouTubeKey: String?
)