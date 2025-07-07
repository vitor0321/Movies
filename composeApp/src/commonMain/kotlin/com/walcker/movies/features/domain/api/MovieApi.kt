package com.walcker.movies.features.domain.api

import com.walcker.movies.features.data.models.MovieListResponse
import com.walcker.movies.features.domain.models.MovieSection

internal interface MovieApi {

    suspend fun getMovies(sectionType: MovieSection.SectionType, language: String = "pt-BR"): MovieListResponse
}