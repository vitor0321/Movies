package com.walcker.movies.features.ui.features.movies

import com.walcker.movies.features.domain.models.MovieSection

internal interface MoviesListInternalRoute {
    data class OnTitleChecked(val sectionType: MovieSection.SectionType) : MoviesListInternalRoute
}