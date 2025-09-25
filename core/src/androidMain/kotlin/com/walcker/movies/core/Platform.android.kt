package com.walcker.movies.core

import com.walcker.movies.core.BuildConfig
import java.util.Locale

private const val LANG_EN = "en"
private const val LANG_PT = "pt"

actual fun platformImpl(): Platform = AndroidPlatform()

private class AndroidPlatform : Platform {
    override val languageSystem: String = getSystemLanguage()
    override val accessToken: String = BuildConfig.TMDB_ACCESS_TOKEN.takeIf { it.isNotEmpty() }.orEmpty()
}

private fun getSystemLanguage(): String = when (Locale.getDefault().language) {
    "pt" -> LANG_PT
    else -> LANG_EN
}