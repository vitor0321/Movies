package com.walcker.movies.mockData.domain

import com.walcker.movies.features.domain.models.MovieSection
import kotlinx.collections.immutable.persistentListOf

internal val movieSectionTestData = persistentListOf(
    MovieSection(
        sectionType = MovieSection.SectionType.POPULAR,
        movies = persistentListOf(movieTestData, movieTestData2)
    ),
    MovieSection(
        sectionType = MovieSection.SectionType.TOP_RATED,
        movies = persistentListOf(movieTestData, movieTestData2)
    ),
    MovieSection(
        sectionType = MovieSection.SectionType.UPCOMING,
        movies = persistentListOf(movieTestData, movieTestData2)
    )
)