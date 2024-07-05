package com.santimattius.kmp.entertainment

import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.santimattius.kmp.entertainment.navigation.Favorite
import com.santimattius.kmp.entertainment.navigation.Movie
import com.santimattius.kmp.entertainment.navigation.NavItem
import com.santimattius.kmp.entertainment.navigation.Splash
import com.santimattius.kmp.entertainment.navigation.TvShow
import com.santimattius.kmp.entertainment.navigation.toRoute
import com.santimattius.kmp.entertainment.navigation.navigatePoppingUpToStartDestination
import kotlinx.coroutines.CoroutineScope
import kotlin.reflect.KClass

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
        val HOME_ROUTES = listOf(
            Movie::class.qualifiedName.orEmpty(),
            TvShow::class.qualifiedName.orEmpty(),
            Favorite::class.qualifiedName.orEmpty()
        )
    }

    val currentRoute: String
        @Composable get() = navController.currentBackStackEntryAsState().value?.destination?.route.orEmpty()

    val isFullScreen: Boolean
        @Composable get() = !currentRoute.notContainsRoute(Splash::class)


    val showUpNavigation: Boolean
        @Composable get() = !HOME_ROUTES.contains(currentRoute)

    val showBottomNavigation: Boolean
        @Composable get() = currentRoute.notContainsRoute(Splash::class)

    val showTopAppBar: Boolean
        @Composable get() = currentRoute.notContainsRoute(Splash::class)


    fun onUpClick() {
        navController.popBackStack()
    }

    private fun <T : Any> String.notContainsRoute(clazz: KClass<T>): Boolean {
        return if (isBlank()) {
            false
        } else {
            !contains(clazz.qualifiedName.orEmpty())
        }
    }

    // TODO: refactor this code
    fun onNavItemClick(navItem: NavItem) {
        val route = navItem.navCommand.feature.toRoute()
        when (route) {
            Splash::class.qualifiedName -> {
                navController.navigatePoppingUpToStartDestination(Splash)
            }

            Movie::class.qualifiedName -> {
                navController.navigatePoppingUpToStartDestination(Movie)

            }

            TvShow::class.qualifiedName -> {
                navController.navigatePoppingUpToStartDestination(TvShow)
            }

            Favorite::class.qualifiedName -> {
                navController.navigatePoppingUpToStartDestination(Favorite)
            }
        }
    }
}
