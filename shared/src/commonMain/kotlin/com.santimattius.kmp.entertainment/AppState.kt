package com.santimattius.kmp.entertainment

import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.santimattius.kmp.entertainment.navigation.Features
import com.santimattius.kmp.entertainment.navigation.NavItem
import com.santimattius.kmp.entertainment.navigation.navigatePoppingUpToStartDestination
import kotlinx.coroutines.CoroutineScope

@Composable
fun rememberAppState(
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    navController: NavHostController = rememberNavController(),
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
): AppState = remember(scaffoldState, navController, coroutineScope) {
    AppState(scaffoldState, navController, coroutineScope)
}

class AppState(
    val scaffoldState: ScaffoldState,
    val navController: NavHostController,
    private val coroutineScope: CoroutineScope,
) {

    companion object {
        val BOTTOM_NAV_ITEMS = listOf(NavItem.Movies, NavItem.TV, NavItem.Favorites)
        val HOME_ROUTES = listOf(NavItem.Movies.navCommand.route, NavItem.TV.navCommand.route, NavItem.Favorites.navCommand.route)
    }

    val currentRoute: String
        @Composable get() = navController.currentBackStackEntryAsState().value?.destination?.route.orEmpty()

    val isFullScreen: Boolean
        @Composable get() = !currentRoute.notContainsRoute(Features.Splash)


    val showUpNavigation: Boolean
        @Composable get() = !HOME_ROUTES.contains(currentRoute)

    val showBottomNavigation: Boolean
        @Composable get() = currentRoute.notContainsRoute(Features.Splash)

    val showTopAppBar: Boolean
        @Composable get() = currentRoute.notContainsRoute(Features.Splash)


    fun onUpClick() {
        navController.popBackStack()
    }

    private fun String.notContainsRoute(feature: Features): Boolean {
        return if (isBlank()) {
            false
        } else {
            !contains(feature.route)
        }
    }

    fun onNavItemClick(navItem: NavItem) {
        navController.navigatePoppingUpToStartDestination(navItem.navCommand.route)
    }
}
