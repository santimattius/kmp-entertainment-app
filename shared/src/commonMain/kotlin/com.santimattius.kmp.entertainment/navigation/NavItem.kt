package com.santimattius.kmp.entertainment.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.LiveTv
import androidx.compose.material.icons.outlined.Movie
import androidx.compose.ui.graphics.vector.ImageVector


enum class NavItem(
    val feature: Features,
    val selectedIcon: ImageVector,
    val title: String,
    val unselectedIcon: ImageVector = selectedIcon,
) {
    Movies(
        feature = Features.Movies,
        selectedIcon = Icons.Outlined.Movie,
        title = "Movies"
    ),
    TV(
        feature = Features.TvShows,
        selectedIcon = Icons.Outlined.LiveTv,
        title = "Tv Shows"
    ),
    Favorites(
        feature = Features.Favorites,
        selectedIcon = Icons.Outlined.Favorite,
        unselectedIcon = Icons.Default.FavoriteBorder,
        title = "Favorites"
    );

    fun icon(selected: Boolean) = if (selected) selectedIcon else unselectedIcon
}
