package com.walcker.movies.features.movies.features.data.di

import com.walcker.movies.features.movies.features.data.api.MovieApiImpl
import com.walcker.movies.features.movies.features.data.network.NetworkClientImpl
import com.walcker.movies.features.movies.features.domain.api.MovieApi
import com.walcker.movies.features.movies.features.domain.network.NetworkClient
import org.koin.dsl.module

internal val networkModule = module {
    single<NetworkClient> { NetworkClientImpl() }
    single<MovieApi> { MovieApiImpl(networkClient = get(), platform = get()) }
}