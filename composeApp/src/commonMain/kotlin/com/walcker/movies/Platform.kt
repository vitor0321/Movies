package com.walcker.movies

interface Platform {
    val name: String
    val accessToken: String
}

expect fun getPlatform(): Platform