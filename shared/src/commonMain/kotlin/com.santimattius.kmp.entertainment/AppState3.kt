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
import com.santimattius.kmp.entertainment.navigation.toRoute
import kotlinx.coroutines.CoroutineScope
import kotlin.reflect.KClass

@Composable
fun rememberAppState3(
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    backStack: SnapshotStateList<Any> = remember { mutableStateListOf(Splash) }
): AppState3 = remember(coroutineScope, backStack) {
    AppState3(coroutineScope, backStack)
}

class AppState3(
    private val coroutineScope: CoroutineScope,
    val backStack: SnapshotStateList<Any> = mutableStateListOf(Splash)
) {

    companion object {
        val BOTTOM_NAV_ITEMS = listOf(NavItem.Movies, NavItem.TV, NavItem.Favorites)
        val HOME_ROUTES = listOf(
            Movie::class.qualifiedName.orEmpty(),
            TvShow::class.qualifiedName.orEmpty(),
            Favorite::class.qualifiedName.orEmpty()
        )
    }

    fun getCurrentRoute() = backStack.last()::class.qualifiedName.orEmpty()

    fun isFullScreen(): Boolean {
        val currentRoute = getCurrentRoute()
        return !currentRoute.notContainsRoute(Splash::class)
    }

    fun showUpNavigation(): Boolean {
        val currentRoute = getCurrentRoute()
        return !HOME_ROUTES.contains(currentRoute)
    }

    fun showBottomNavigation(): Boolean {
        val currentRoute = getCurrentRoute()
        return currentRoute.notContainsRoute(Splash::class)
    }

    fun showTopAppBar(): Boolean {
        val currentRoute = getCurrentRoute()
        return currentRoute.notContainsRoute(Splash::class)
    }


    fun onUpClick() {
        backStack.removeLastOrNull()
    }

    private fun <T : Any> String.notContainsRoute(clazz: KClass<T>): Boolean {
        return if (isBlank()) {
            false
        } else {
            !contains(clazz.qualifiedName.orEmpty())
        }
    }

    fun onNavClick(route: Any) {
        backStack.add(route)
    }

    fun onNavItemClick(navItem: NavItem) {
        val route = navItem.feature.toRoute()
        when (route) {
            Splash::class.qualifiedName -> {
                backStack.add(Splash)
            }

            Movie::class.qualifiedName -> {
                backStack.add(Movie)

            }

            TvShow::class.qualifiedName -> {
                backStack.add(TvShow)
            }

            Favorite::class.qualifiedName -> {
                backStack.add(Favorite)
            }
        }
    }
}
