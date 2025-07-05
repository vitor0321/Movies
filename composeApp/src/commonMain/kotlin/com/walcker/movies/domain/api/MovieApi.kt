package com.walcker.movies.domain.api

import com.walcker.movies.data.models.MovieListResponse
import com.walcker.movies.domain.models.MovieSection

internal interface MovieApi {

    suspend fun getMovies(sectionType: MovieSection.SectionType, language: String = "pt-BR"): MovieListResponse
}