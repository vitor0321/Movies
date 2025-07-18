package com.walcker.movies

import android.app.Application

class AppApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        setupPlatformTrailerOpener(this)
    }
}
