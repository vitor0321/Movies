package com.walcker.movies

import com.walcker.movies.strings.Locales
import platform.Foundation.NSBundle
import platform.Foundation.NSLocale
import platform.Foundation.preferredLanguages

actual fun getPlatform(): Platform = IOSPlatform()

class IOSPlatform : Platform {

    override val languageSystem: String = getSystemLanguage()

    // https://www.themoviedb.org/settings/api
    override val accessToken: String by lazy {
        NSBundle.mainBundle.objectForInfoDictionaryKey("TMDB_ACCESS_TOKEN") as? String
            ?: throw IllegalStateException("TMDB_ACCESS_TOKEN not found in Info.plist. Please configure Secrets.xcconfig properly.")
    }
}

private fun getSystemLanguage(): String {
    val languages = NSLocale.preferredLanguages()
    val primaryLanguage = languages.firstOrNull() ?: Locales.EN

    // Para debug
    println("iOS detected language: $primaryLanguage")

    return when (primaryLanguage) {
        "pt-BR" -> Locales.PT
        else -> Locales.EN
    }
}