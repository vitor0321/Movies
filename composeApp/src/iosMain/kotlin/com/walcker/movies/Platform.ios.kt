package com.walcker.movies

import platform.Foundation.NSBundle
import platform.UIKit.UIDevice

class IOSPlatform: Platform {
    override val name: String = UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion

    // https://www.themoviedb.org/settings/api
    override val accessToken: String by lazy {
        NSBundle.mainBundle.objectForInfoDictionaryKey("TMDB_ACCESS_TOKEN") as? String
            ?: throw IllegalStateException("TMDB_ACCESS_TOKEN not found in Info.plist. Please configure Secrets.xcconfig properly.")
    }
}

actual fun getPlatform(): Platform = IOSPlatform()