package com.walcker.movies.core.di

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import org.koin.core.qualifier.named
import org.koin.dsl.module

internal val dispatcherModule = module {
    single(named(Dispatcher.IO)) { Dispatchers.IO }
    single(named(Dispatcher.DEFAULT)) { Dispatchers.Default }
    single(named(Dispatcher.MAIN)) { Dispatchers.Main }
}

public enum class Dispatcher {
    IO,
    DEFAULT,
    MAIN
}