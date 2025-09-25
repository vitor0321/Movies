package com.walcker.movies.features.movies.features.data.di

import com.walcker.movies.core.di.Dispatcher
import com.walcker.movies.features.movies.features.data.repository.MoviesRepositoryImpl
import com.walcker.movies.features.movies.features.domain.repository.MoviesRepository
import org.koin.core.qualifier.named
import org.koin.dsl.module

internal val repositoryModule = module {
    factory<MoviesRepository> { MoviesRepositoryImpl(movieApi = get(), dispatcher = get(named(Dispatcher.IO))) }
}