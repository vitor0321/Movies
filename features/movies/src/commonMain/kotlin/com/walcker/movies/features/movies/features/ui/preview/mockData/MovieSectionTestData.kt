package com.walcker.movies.features.movies.features.ui.preview.mockData

import com.walcker.movies.features.movies.features.domain.models.MovieSection
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toImmutableList

internal val movieSectionTestData = persistentListOf(
    MovieSection(
        sectionType = MovieSection.SectionType.HIGHLIGHT,
        movies = List(5) { movieTestData }.toImmutableList()
    ),
    MovieSection(
        sectionType = MovieSection.SectionType.POPULAR,
        movies = List(10) { movieTestData }.toImmutableList()
    ),
    MovieSection(
        sectionType = MovieSection.SectionType.TOP_RATED,
        movies = List(10) { movieTestData }.toImmutableList()
    ),
    MovieSection(
        sectionType = MovieSection.SectionType.UPCOMING,
        movies = List(10) { movieTestData }.toImmutableList()
    )
)