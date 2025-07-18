package com.walcker.movies

import com.walcker.movies.platform.TrailerOpener

public interface Platform {
    val languageSystem: String
    val accessToken: String
    val trailerOpener: TrailerOpener
}

public expect fun platformImpl(): Platform