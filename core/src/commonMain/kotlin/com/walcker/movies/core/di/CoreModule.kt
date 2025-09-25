package com.walcker.movies.core.di

import org.koin.core.module.Module

public val coreModule = listOf<Module>(
    platformModule,
    dispatcherModule,
)