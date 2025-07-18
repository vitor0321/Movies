package com.walcker.movies

import com.walcker.movies.strings.Locales
import platform.Foundation.NSBundle
import platform.Foundation.NSLocale
import platform.Foundation.preferredLanguages
import com.walcker.movies.platform.IosTrailerOpener
import com.walcker.movies.platform.TrailerOpener

actual fun platformImpl(): Platform = IOSPlatform()

class IOSPlatform : Platform {

    override val languageSystem: String = getSystemLanguage()

    // https://www.themoviedb.org/settings/api
    override val accessToken: String by lazy {
        NSBundle.mainBundle.objectForInfoDictionaryKey("TMDB_ACCESS_TOKEN") as? String ?: ""
    }

    override val trailerOpener: TrailerOpener = IosTrailerOpener()
}

private fun getSystemLanguage(): String {
    val languages = NSLocale.preferredLanguages()
    val primaryLanguage = languages.firstOrNull() ?: Locales.EN

    println("iOS detected language: $primaryLanguage")

    return when (primaryLanguage) {
        "pt-BR" -> Locales.PT
        else -> Locales.EN
    }
}