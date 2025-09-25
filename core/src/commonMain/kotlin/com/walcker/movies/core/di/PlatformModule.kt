package com.walcker.movies.core.di

import com.walcker.movies.core.Platform
import com.walcker.movies.core.platformImpl
import org.koin.dsl.module

internal val platformModule = module {
    single<Platform> { platformImpl() }
}