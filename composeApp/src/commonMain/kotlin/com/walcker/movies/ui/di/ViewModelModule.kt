package com.walcker.movies.ui.di

import com.walcker.movies.ui.features.movies.MoviesListViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

internal val viewModelModule = module {
    viewModel { MoviesListViewModel(moviesRepository = get()) }
}