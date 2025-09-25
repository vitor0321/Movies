package com.walcker.movies.app

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.window.ComposeUIViewController
import com.walcker.movies.features.movies.App
import platform.UIKit.UIViewController

private val IsDarkThemeState = mutableStateOf(false)

fun mainViewController(isDarkTheme: Boolean = false): UIViewController {
    IsDarkThemeState.value = isDarkTheme

    return ComposeUIViewController {
        App(isDarkTheme = IsDarkThemeState.value)
    }
}

fun updateTheme(controller: UIViewController, isDarkTheme: Boolean) {
    IsDarkThemeState.value = isDarkTheme
    controller.view?.setNeedsDisplay()
}