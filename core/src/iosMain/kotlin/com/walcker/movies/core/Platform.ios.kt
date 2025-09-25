package com.walcker.movies.core

import platform.Foundation.NSBundle
import platform.Foundation.NSLocale
import platform.Foundation.preferredLanguages

private const val LANG_EN = "en"
private const val LANG_PT = "pt"

actual fun platformImpl(): Platform = IOSPlatform()

private class IOSPlatform : Platform {
    override val languageSystem: String = getSystemLanguage()
    override val accessToken: String by lazy {
        (NSBundle.mainBundle.objectForInfoDictionaryKey("TMDB_ACCESS_TOKEN") as? String) ?: ""
    }
}

private fun getSystemLanguage(): String {
    val languages = NSLocale.preferredLanguages()
    val primaryLanguage = languages.firstOrNull() as? String ?: LANG_EN
    return when (primaryLanguage) {
        "pt-BR", "pt" -> LANG_PT
        else -> LANG_EN
    }
}