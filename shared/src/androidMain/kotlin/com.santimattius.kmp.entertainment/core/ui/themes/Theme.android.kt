package com.santimattius.kmp.entertainment.core.ui.themes

import android.app.Activity
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowInsetsControllerCompat

@Composable
internal actual fun SystemAppearance(
    isDark: Boolean
) {
    val view = LocalView.current
    val primaryColor = if (isDark) {
        MaterialTheme.colorScheme.onSurface
    } else {
        MaterialTheme.colorScheme.background
    }
    LaunchedEffect(isDark) {
        val window = (view.context as Activity).window
        window.statusBarColor = primaryColor.toArgb()
        WindowInsetsControllerCompat(window, window.decorView).apply {
            isAppearanceLightStatusBars = !isDark
            isAppearanceLightNavigationBars = !isDark
        }
    }
}