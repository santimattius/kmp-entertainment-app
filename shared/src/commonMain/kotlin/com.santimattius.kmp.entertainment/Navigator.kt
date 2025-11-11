package com.santimattius.kmp.entertainment

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.santimattius.kmp.entertainment.navigation.Favorite
import com.santimattius.kmp.entertainment.navigation.Movie
import com.santimattius.kmp.entertainment.navigation.NavItem
import com.santimattius.kmp.entertainment.navigation.Splash
import com.santimattius.kmp.entertainment.navigation.TvShow
import com.santimattius.kmp.entertainment.navigation.toDestination
import kotlinx.coroutines.CoroutineScope
import org.koin.compose.koinInject
import org.koin.core.parameter.parametersOf

@Composable
fun rememberNavigator(
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    backStack: SnapshotStateList<Any> = remember { mutableStateListOf(Splash) }
): Navigator = koinInject {
    parametersOf(coroutineScope, backStack)
}

class Navigator(
    private val coroutineScope: CoroutineScope,
    val backStack: SnapshotStateList<Any> = mutableStateListOf(Splash)
) {

    companion object Companion {
        val BOTTOM_NAV_ITEMS = listOf(NavItem.Movies, NavItem.TV, NavItem.Favorites)
        val HOME_ROUTES = listOf(
            Movie::class.qualifiedName.orEmpty(),
            TvShow::class.qualifiedName.orEmpty(),
            Favorite::class.qualifiedName.orEmpty()
        )
    }

    fun getCurrentRoute(): Any? = backStack.lastOrNull()

    fun getCurrentRouteString(): String {
        val currentRoute = getCurrentRoute() ?: return ""
        return currentRoute::class.qualifiedName.orEmpty()
    }


    fun isFullScreen(): Boolean {
        val currentRoute = getCurrentRoute()
        return currentRoute is Splash
    }

    fun showUpNavigation(): Boolean {
        val currentRoute = getCurrentRouteString()
        return !HOME_ROUTES.contains(currentRoute)
    }

    fun showBottomNavigation(): Boolean {
        val currentRoute = getCurrentRoute()
        return currentRoute !is Splash
    }

    fun showTopAppBar(): Boolean {
        val currentRoute = getCurrentRoute()
        return currentRoute !is Splash
    }


    fun onUpClick() {
        backStack.removeLastOrNull()
    }


    fun onNavClick(route: Any) {
        backStack.add(route)
    }

    fun onNavItemClick(navItem: NavItem) {
        val destination = navItem.feature.toDestination()
        backStack.add(destination)
    }
}
