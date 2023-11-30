package com.santimattius.kmp.entertainment.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.LiveTv
import androidx.compose.material.icons.outlined.Movie
import androidx.compose.ui.graphics.vector.ImageVector


enum class NavItem(
    val navCommand: NavCommand,
    val selectedIcon: ImageVector,
    val title: String,
    val unselectedIcon: ImageVector = selectedIcon,
) {
    Movies(
        navCommand = NavCommand.ContentType(Features.Movies),
        selectedIcon = Icons.Outlined.Movie,
        title = "Movies"
    ),
    TV(
        navCommand = NavCommand.ContentType(Features.TvShows),
        selectedIcon = Icons.Outlined.LiveTv,
        title = "Tv Shows"
    ),
    Favorites(
        navCommand = NavCommand.ContentType(Features.Favorites),
        selectedIcon = Icons.Outlined.Favorite,
        unselectedIcon = Icons.Default.FavoriteBorder,
        title = "Favorites"
    );

    fun icon(selected: Boolean) = if (selected) selectedIcon else unselectedIcon
}

sealed class NavCommand(
    internal val feature: Features,
    internal val subRoute: String = "home",
    private val navArgs: List<NavArg> = emptyList(),
) {
    class ContentType(feature: Features) : NavCommand(feature)
    class ContentTypeDetail(feature: Features) : NavCommand(
        feature = feature,
        subRoute = "detail",
        navArgs = listOf(NavArg.ItemId)
    ) {
        fun createRoute(itemId: Long) = "${feature.route}/$subRoute/$itemId"
    }

    val route = run {
        val argValues = navArgs.map { "{${it.key}}" }
        listOf(feature.route)
            .plus(subRoute)
            .plus(argValues)
            .joinToString("/")
    }
}

enum class NavArg(val key: String) {
    ItemId("id")
}