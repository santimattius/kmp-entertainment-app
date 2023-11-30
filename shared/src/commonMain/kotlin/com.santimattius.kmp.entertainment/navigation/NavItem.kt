package com.santimattius.kmp.entertainment.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.LiveTv
import androidx.compose.material.icons.outlined.Movie
import androidx.compose.ui.graphics.vector.ImageVector


enum class NavItem(
    val navCommand: NavCommand,
    val icon: ImageVector,
    val title: String,
) {
    Movies(NavCommand.ContentType(Features.Movies), Icons.Outlined.Movie, "Movies"),
    TV(NavCommand.ContentType(Features.TvShows), Icons.Outlined.LiveTv, "Tv Shows"),
    Favorites(NavCommand.ContentType(Features.Favorites), Icons.Outlined.Favorite, "Favorites"),
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