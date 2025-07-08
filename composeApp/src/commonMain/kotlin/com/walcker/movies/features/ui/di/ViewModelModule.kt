package com.walcker.movies.features.ui.di

import com.walcker.movies.features.ui.features.movies.MoviesListViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

internal val viewModelModule = module {
    viewModel { MoviesListViewModel(moviesRepository = get()) }
}