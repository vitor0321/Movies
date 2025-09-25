package com.walcker.movies.features.movies.features.domain.models

internal data class MoviesPagination(
    val highlightPage: Int = 1,
    val popularPage: Int = 1,
    val topRatedPage: Int = 1,
    val upcomingPage: Int = 1
) {
    fun pageFor(section: MovieSection.SectionType): Int = when (section) {
        MovieSection.SectionType.HIGHLIGHT -> highlightPage
        MovieSection.SectionType.POPULAR -> popularPage
        MovieSection.SectionType.TOP_RATED -> topRatedPage
        MovieSection.SectionType.UPCOMING -> upcomingPage
    }

    fun increment(section: MovieSection.SectionType): MoviesPagination = when (section) {
        MovieSection.SectionType.HIGHLIGHT -> copy(highlightPage = highlightPage + 1)
        MovieSection.SectionType.POPULAR -> copy(popularPage = popularPage + 1)
        MovieSection.SectionType.TOP_RATED -> copy(topRatedPage = topRatedPage + 1)
        MovieSection.SectionType.UPCOMING -> copy(upcomingPage = upcomingPage + 1)
    }
}
