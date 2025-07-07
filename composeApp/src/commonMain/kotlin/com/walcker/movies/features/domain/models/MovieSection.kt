package com.walcker.movies.features.domain.models

import kotlinx.collections.immutable.ImmutableList

internal data class MovieSection(
    val sectionType: SectionType,
    val movies: ImmutableList<Movie>,
) {
    enum class SectionType(
        val title: String,
        val category: String,
    ) {
        POPULAR(
            title = "Popular",
            category = "popular",
        ),
        TOP_RATED(
            title = "Top Rated",
            category = "top_rated",
        ),
        UPCOMING(
            title = "Upcoming",
            category = "upcoming",
        )
    }
}
