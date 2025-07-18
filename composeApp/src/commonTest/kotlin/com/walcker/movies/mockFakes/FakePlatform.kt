package com.walcker.movies.mockFakes

import com.walcker.movies.Platform
import com.walcker.movies.platform.TrailerOpener

internal object FakePlatform {
    internal var trailerOpenerCont: Int = 0
    fun createMockPlatform(): Platform = object : Platform {
            override val languageSystem: String = "pt-BR"
            override val accessToken: String = "test_token"
        override val trailerOpener: TrailerOpener
            get() = AndroidTrailerOpenerFake()
    }
}

private class AndroidTrailerOpenerFake : TrailerOpener {
    override fun openTrailer(url: String) {
        FakePlatform.trailerOpenerCont++
    }
}