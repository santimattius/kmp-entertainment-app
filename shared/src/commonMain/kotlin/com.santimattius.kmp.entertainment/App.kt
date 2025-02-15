package com.santimattius.kmp.entertainment

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import coil3.ImageLoader
import coil3.compose.setSingletonImageLoaderFactory
import coil3.network.ktor3.KtorNetworkFetcherFactory
import com.santimattius.kmp.entertainment.core.ui.components.AppBar
import com.santimattius.kmp.entertainment.core.ui.components.AppBottomNavigation
import com.santimattius.kmp.entertainment.core.ui.components.ArrowBackIcon
import com.santimattius.kmp.entertainment.core.ui.themes.AppTheme
import com.santimattius.kmp.entertainment.di.appModule
import com.santimattius.kmp.entertainment.navigation.AppNavigation
import org.koin.compose.KoinMultiplatformApplication
import org.koin.dsl.koinConfiguration

@Composable
fun App() {
    setSingletonImageLoaderFactory { context ->
        ImageLoader.Builder(context)
            .components {
                add(KtorNetworkFetcherFactory())
            }
            .build()
    }
    KoinMultiplatformApplication(
        config = koinConfiguration {
            modules(appModule())
        }
    ) {
        AppTheme {
            MainApp()
        }
    }
}


@Composable
fun MainApp(
    appState: AppState = rememberAppState(),
) {
    val upNavigation: @Composable () -> Unit = {
        ArrowBackIcon {
            appState.onUpClick()
        }
    }

    val empty: @Composable () -> Unit = {}

    Scaffold(
        topBar = {
            if (appState.showTopAppBar) {
                AppBar(
                    title = "Movies and TvShows",
                    navigationIcon = if (appState.showUpNavigation) upNavigation else empty
                )
            }
        },
        bottomBar = {
            if (appState.showBottomNavigation) {
                AppBottomNavigation(
                    bottomNavOptions = AppState.BOTTOM_NAV_ITEMS,
                    currentRoute = appState.currentRoute,
                    onNavItemClick = { appState.onNavItemClick(it) })
            }
        }
    ) {
        Box(modifier = Modifier.fillMaxSize().padding(it)) {
            AppNavigation(appState.navController)
        }
    }
}


expect fun getPlatformName(): String
