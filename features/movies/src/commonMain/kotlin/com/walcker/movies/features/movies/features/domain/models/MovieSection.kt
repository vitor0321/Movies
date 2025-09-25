package com.walcker.movies.features.movies.features.domain.models

import com.walcker.movies.features.movies.strings.features.MoviesListStrings
import kotlinx.collections.immutable.ImmutableList

internal data class MovieSection(
    val sectionType: SectionType,
    val movies: ImmutableList<Movie>,
) {
    enum class SectionType(
        val title: (MoviesListStrings) -> String,
        val category: String,
    ) {
        HIGHLIGHT(
            title = { strings -> strings.highlight },
            category = "now_playing",
        ),
        POPULAR(
            title = { strings -> strings.popularMovies },
            category = "popular",
        ),
        TOP_RATED(
            title = { strings -> strings.topRatedMovies },
            category = "top_rated",
        ),
        UPCOMING(
            title = { strings -> strings.upcomingMovies },
            category = "upcoming",
        );
    }
}
