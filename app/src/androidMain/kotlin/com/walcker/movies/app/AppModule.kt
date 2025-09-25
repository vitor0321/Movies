package com.walcker.movies.app

import com.walcker.movies.core.di.coreModule
import com.walcker.movies.features.movies.di.moviesModule

internal val appModule = coreModule + moviesModule