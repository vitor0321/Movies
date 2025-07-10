package com.walcker.movies.di

import com.walcker.movies.features.data.di.networkModule
import com.walcker.movies.features.data.di.repositoryModule
import com.walcker.movies.features.ui.di.viewModelModule
import org.koin.core.module.Module

internal val coreModule = listOf<Module>(
    // Data
    networkModule,
    repositoryModule,
    // Core
    dispatcherModule,
    // UI
    viewModelModule,
)