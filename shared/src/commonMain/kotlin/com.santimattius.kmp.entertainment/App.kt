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
import com.santimattius.kmp.entertainment.core.ui.themes.AppContainer
import com.santimattius.kmp.entertainment.di.appModule
import com.santimattius.kmp.entertainment.navigation.AppNavigation
import org.koin.compose.KoinMultiplatformApplication
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.dsl.koinConfiguration


@OptIn(KoinExperimentalAPI::class)
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
        config = koinConfiguration { modules(appModule()) }
    ) {
        AppContainer {
            MainApp()
        }
    }
}


@Composable
fun MainApp(
    navigator: Navigator = rememberNavigator(),
) {
    val upNavigation: @Composable () -> Unit = {
        ArrowBackIcon {
            navigator.onUpClick()
        }
    }

    val empty: @Composable () -> Unit = {}

    Scaffold(
        topBar = {
            if (navigator.showTopAppBar()) {
                AppBar(
                    title = "Movies and TvShows",
                    navigationIcon = if (navigator.showUpNavigation()) upNavigation else empty
                )
            }
        },
        bottomBar = {
            if (navigator.showBottomNavigation()) {
                AppBottomNavigation(
                    bottomNavOptions = Navigator.BOTTOM_NAV_ITEMS,
                    currentRoute = navigator.getCurrentRouteString(),
                    onNavItemClick = { navigator.onNavItemClick(it) })
            }
        }
    ) {
        Box(modifier = Modifier.fillMaxSize().padding(it)) {
            AppNavigation(
                navigator = navigator
            )
        }
    }
}


expect fun getPlatformName(): String
