package com.walcker.movies.data.di

import com.walcker.movies.data.repository.MoviesRepositoryImpl
import com.walcker.movies.di.Dispatcher
import com.walcker.movies.domain.repository.MoviesRepository
import org.koin.core.qualifier.named
import org.koin.dsl.module

internal val repositoryModule = module {
    factory<MoviesRepository> { MoviesRepositoryImpl(movieApi = get(), dispatcher = get(named(Dispatcher.IO))) }
}