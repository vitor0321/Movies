package com.walcker.movies.mockFakes

import com.walcker.movies.Platform

internal object FakePlatform {
    fun createMockPlatform(): Platform {
        return object : Platform {
            override val languageSystem: String = "pt-BR"
            override val accessToken: String = "test_token"
        }
    }
}