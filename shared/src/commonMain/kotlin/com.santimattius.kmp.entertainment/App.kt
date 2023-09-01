package com.santimattius.kmp.entertainment

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import com.santimattius.kmp.entertainment.core.ui.themes.AppTheme
import com.santimattius.kmp.entertainment.feature.home.HomeScreen
import org.jetbrains.compose.resources.ExperimentalResourceApi

@Composable
fun App() {
    AppTheme {
        Navigator(HomeScreen)
    }
}

expect fun getPlatformName(): String