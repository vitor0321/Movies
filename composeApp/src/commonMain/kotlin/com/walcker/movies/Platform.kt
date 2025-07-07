package com.walcker.movies

public interface Platform {
    val languageSystem: String
    val accessToken: String
}

public expect fun getPlatform(): Platform