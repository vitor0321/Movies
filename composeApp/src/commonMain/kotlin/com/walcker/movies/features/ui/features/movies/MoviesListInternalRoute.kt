package com.walcker.movies.features.ui.features.movies

import com.walcker.movies.features.domain.models.MovieSection

internal interface MoviesListInternalRoute {
    data class OnLoadNextPage(val sectionType: MovieSection.SectionType) : MoviesListInternalRoute
}