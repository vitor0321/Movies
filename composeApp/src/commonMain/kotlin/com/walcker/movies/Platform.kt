package com.walcker.movies

public interface Platform {
    val name: String
    val accessToken: String
}

public expect fun getPlatform(): Platform