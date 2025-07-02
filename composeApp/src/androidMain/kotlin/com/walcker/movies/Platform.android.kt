package com.walcker.movies

import android.os.Build

class AndroidPlatform : Platform {
    override val name: String = "Android ${Build.VERSION.SDK_INT}"

    // https://www.themoviedb.org/settings/api
    override val accessToken: String = BuildConfig.TMDB_ACCESS_TOKEN.takeIf { it.isNotEmpty() }
        ?: throw IllegalStateException("TMDB_ACCESS_TOKEN not found in BuildConfig. Please configure local.properties properly.")
}

actual fun getPlatform(): Platform = AndroidPlatform()