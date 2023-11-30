package com.santimattius.kmp.entertainment

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.santimattius.kmp.entertainment.core.db.DriverFactory
import com.santimattius.kmp.entertainment.core.db.createDatabase
import com.santimattius.kmp.entertainment.core.ui.components.AppBar
import com.santimattius.kmp.entertainment.core.ui.components.AppBottomNavigation
import com.santimattius.kmp.entertainment.core.ui.components.ArrowBackIcon
import com.santimattius.kmp.entertainment.core.ui.themes.AppTheme
import com.santimattius.kmp.entertainment.di.appModule
import com.santimattius.kmp.entertainment.navigation.Features
import com.santimattius.kmp.entertainment.navigation.Navigation
import moe.tlaster.precompose.PreComposeApp
import org.koin.compose.KoinApplication
import org.koin.dsl.module

@Composable
fun App(
    driverFactory: DriverFactory,
) {
    KoinApplication(
        application = {
            modules(appModule() + module {
                single { driverFactory }
                single { createDatabase(get()) }
            })
        }
    ) {
        PreComposeApp {
            AppTheme {
                MainApp()
            }
        }
    }
}


@Composable
fun MainApp(
    appState: AppState = rememberAppState(),
) {

    val currentRoute by appState.navigator.currentEntry.collectAsState(null)
    val route = currentRoute?.route?.route.orEmpty()

    val upNavigation: @Composable () -> Unit = {
        ArrowBackIcon {
            appState.onUpClick()
        }
    }

    val empty: @Composable () -> Unit = {}

    Scaffold(
        topBar = {
            if (currentRoute.notContainsRoute(Features.SPLASH.route)) {
                AppBar(
                    title = if (AppState.HOME_ROUTES.contains(route)) "Movies and TvShows" else "",
                    navigationIcon = if (!AppState.HOME_ROUTES.contains(route)) upNavigation else empty
                )
            }
        },
        bottomBar = {
            if (currentRoute.notContainsRoute(Features.SPLASH.route)) {
                AppBottomNavigation(
                    bottomNavOptions = AppState.BOTTOM_NAV_ITEMS,
                    currentRoute = route,
                    onNavItemClick = { appState.onNavItemClick(it) })
            }
        }
    ) {
        Box(modifier = Modifier.fillMaxSize().padding(it)) {
            Navigation(appState.navigator)
        }
    }
}


expect fun getPlatformName(): String