package com.walcker.movies.features.data.di

import com.walcker.movies.features.data.api.MovieApiImpl
import com.walcker.movies.features.data.network.NetworkClientImpl
import com.walcker.movies.features.domain.api.MovieApi
import com.walcker.movies.features.domain.network.NetworkClient
import org.koin.dsl.module

internal val networkModule = module {
    single<NetworkClient> { NetworkClientImpl() }
    single<MovieApi> { MovieApiImpl(networkClient = get()) }
}