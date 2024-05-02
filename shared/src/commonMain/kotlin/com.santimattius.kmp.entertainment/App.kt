package com.santimattius.kmp.entertainment

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import coil3.ImageLoader
import coil3.annotation.ExperimentalCoilApi
import coil3.compose.setSingletonImageLoaderFactory
import coil3.network.ktor.KtorNetworkFetcherFactory
import com.santimattius.kmp.entertainment.core.db.DriverFactory
import com.santimattius.kmp.entertainment.core.db.createDatabase
import com.santimattius.kmp.entertainment.core.ui.components.AppBar
import com.santimattius.kmp.entertainment.core.ui.components.AppBottomNavigation
import com.santimattius.kmp.entertainment.core.ui.components.ArrowBackIcon
import com.santimattius.kmp.entertainment.core.ui.themes.AppTheme
import com.santimattius.kmp.entertainment.di.appModule
import com.santimattius.kmp.entertainment.navigation.Navigation
import org.koin.compose.KoinApplication
import org.koin.dsl.module

@OptIn(ExperimentalCoilApi::class)
@Composable
fun App(
    driverFactory: DriverFactory,
) {
    setSingletonImageLoaderFactory { context ->
        ImageLoader.Builder(context)
            .components {
                add(KtorNetworkFetcherFactory())
            }
            .build()
    }
    KoinApplication(
        application = {
            modules(appModule() + module {
                single { driverFactory }
                single { createDatabase(get()) }
            })
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
            Navigation(appState.navController)
        }
    }
}


expect fun getPlatformName(): String