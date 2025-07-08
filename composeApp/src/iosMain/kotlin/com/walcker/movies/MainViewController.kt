package com.walcker.movies

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.window.ComposeUIViewController
import platform.UIKit.UIViewController

private val isDarkThemeState = mutableStateOf(false)

fun MainViewController(isDarkTheme: Boolean = false): UIViewController {
    isDarkThemeState.value = isDarkTheme

    return ComposeUIViewController {
        App(isDarkTheme = isDarkThemeState.value)
    }
}

fun updateTheme(controller: UIViewController, isDarkTheme: Boolean) {
    isDarkThemeState.value = isDarkTheme
    controller.view?.setNeedsDisplay()
}