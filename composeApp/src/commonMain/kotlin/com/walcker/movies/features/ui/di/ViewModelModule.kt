package com.walcker.movies.features.ui.di

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.toRoute
import com.walcker.movies.di.Dispatcher
import com.walcker.movies.features.ui.features.movieDetail.MovieDetailViewModel
import com.walcker.movies.features.ui.features.movies.MoviesListViewModel
import com.walcker.movies.navigation.AppRoutes
import org.koin.core.module.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

internal val viewModelModule = module {
    viewModel { MoviesListViewModel(moviesRepository = get()) }
    viewModel { (state: SavedStateHandle) ->
        MovieDetailViewModel(
            movieId = state.toRoute<AppRoutes.MovieDetail>().movieId,
            moviesRepository = get(),
            dispatcher = get(named(Dispatcher.DEFAULT))
        )
    }
}