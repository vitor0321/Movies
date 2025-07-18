package com.walcker.movies

import android.content.Context
import com.walcker.movies.platform.AndroidTrailerOpener
import com.walcker.movies.platform.TrailerOpener
import com.walcker.movies.strings.Locales
import java.util.Locale

private lateinit var trailerOpenerDelegate: TrailerOpener

fun setupPlatformTrailerOpener(context: Context) {
    trailerOpenerDelegate = AndroidTrailerOpener(context)
}

actual fun platformImpl(): Platform = AndroidPlatform()

private class AndroidPlatform : Platform {
    override val languageSystem: String = getSystemLanguage()
    // https://www.themoviedb.org/settings/api
    override val accessToken: String = BuildConfig.TMDB_ACCESS_TOKEN.takeIf { it.isNotEmpty() }.orEmpty()

    override val trailerOpener: TrailerOpener
        get() = trailerOpenerDelegate
}

private fun getSystemLanguage(): String {
    val language = Locale.getDefault().language

    return when (language) {
        "pt" -> Locales.PT
        else -> Locales.EN
    }
}