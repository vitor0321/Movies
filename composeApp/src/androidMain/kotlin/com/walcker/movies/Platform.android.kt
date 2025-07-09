package com.walcker.movies

import com.walcker.movies.strings.Locales
import java.util.Locale

actual fun getPlatform(): Platform = AndroidPlatform()

private class AndroidPlatform : Platform {

    override val languageSystem: String = getSystemLanguage()

    // https://www.themoviedb.org/settings/api
    override val accessToken: String = BuildConfig.TMDB_ACCESS_TOKEN.takeIf { it.isNotEmpty() }
        ?: throw IllegalStateException("TMDB_ACCESS_TOKEN not found in BuildConfig. Please configure local.properties properly.")
}

private fun getSystemLanguage(): String {
    val language = Locale.getDefault().language

    // Para debug
    println("Android detected language: $language")

    return when (language) {
        "pt" -> Locales.PT
        else -> Locales.EN
    }
}