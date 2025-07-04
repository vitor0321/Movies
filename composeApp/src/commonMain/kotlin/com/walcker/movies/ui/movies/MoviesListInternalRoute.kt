package com.walcker.movies.ui.movies

import com.walcker.movies.domain.models.MovieSection

internal interface MoviesListInternalRoute {
    data class OnTitleChecked(val sectionType: MovieSection.SectionType) : MoviesListInternalRoute
}