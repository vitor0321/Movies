package com.walcker.movies.data.di

import com.walcker.movies.data.api.MovieApiImpl
import com.walcker.movies.data.network.NetworkClientImpl
import com.walcker.movies.domain.api.MovieApi
import com.walcker.movies.domain.network.NetworkClient
import org.koin.dsl.module

internal val networkModule = module {
    single<NetworkClient> { NetworkClientImpl() }
    single<MovieApi> { MovieApiImpl(networkClient = get()) }
}