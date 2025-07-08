package com.walcker.movies

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowInsetsControllerCompat

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {
            val view = LocalView.current
            val isSystemInDarkTheme = isSystemInDarkTheme()
            SideEffect {
                WindowInsetsControllerCompat(window, view)
                    .isAppearanceLightStatusBars = !isSystemInDarkTheme
            }
            App()
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App()
}