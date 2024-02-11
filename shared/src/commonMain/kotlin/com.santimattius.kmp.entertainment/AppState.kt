package com.santimattius.kmp.entertainment

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.santimattius.kmp.entertainment.navigation.NavItem
import moe.tlaster.precompose.navigation.BackStackEntry
import moe.tlaster.precompose.navigation.Navigator
import moe.tlaster.precompose.navigation.rememberNavigator

@Composable
fun rememberAppState(
    navigator: Navigator = rememberNavigator(),
): AppState = remember(navigator) {
    AppState(navigator)
}

class AppState(
    val navigator: Navigator,
) {

    companion object {
        val BOTTOM_NAV_ITEMS = listOf(NavItem.Movies, NavItem.TV, NavItem.Favorites)
        val HOME_ROUTES = listOf(
            NavItem.Movies.navCommand.route,
            NavItem.TV.navCommand.route,
            NavItem.Favorites.navCommand.route
        )
    }

    fun onUpClick() {
        navigator.popBackStack()
    }

    fun onNavItemClick(navItem: NavItem) {
        navigator.navigate(navItem.navCommand.route)
    }
}

fun BackStackEntry?.notContainsRoute(route: String) = this.containsRoute(route).not()

fun BackStackEntry?.containsRoute(route: String) = this?.hasRoute(route) ?: false
