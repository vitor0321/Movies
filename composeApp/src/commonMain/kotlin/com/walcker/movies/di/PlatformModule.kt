package com.walcker.movies.di

import com.walcker.movies.Platform
import com.walcker.movies.platformImpl
import org.koin.dsl.module

internal val platformModule = module {
    single<Platform> { platformImpl() }
}