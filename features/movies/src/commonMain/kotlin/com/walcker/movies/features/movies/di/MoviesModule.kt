package com.walcker.movies.features.movies.di

import com.walcker.movies.features.movies.features.data.di.networkModule
import com.walcker.movies.features.movies.features.data.di.repositoryModule
import com.walcker.movies.features.movies.features.ui.di.viewModelModule
import org.koin.core.module.Module

public val moviesModule = listOf<Module>(
    // Data
    networkModule,
    repositoryModule,

    // UI
    viewModelModule,
)