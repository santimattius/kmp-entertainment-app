package com.santimattius.kmp.entertainment.core.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationDefaults
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.ContentAlpha
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import com.santimattius.kmp.entertainment.navigation.NavItem

@Composable
fun AppBottomNavigation(
    bottomNavOptions: List<NavItem>,
    currentRoute: String,
    contentColor: Color = MaterialTheme.colorScheme.onPrimary,
    onNavItemClick: (NavItem) -> Unit,
) {
    AppBottomNavigation {
        bottomNavOptions.forEach { item ->
            val isSelected = currentRoute.contains(item.navCommand.feature.route)
            val color by animateColorAsState(
                if (isSelected) contentColor else contentColor.copy(
                    alpha = ContentAlpha.medium
                )
            )

            BottomNavigationItem(
                selected = isSelected,
                selectedContentColor = color,
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.title,
                        tint = color
                    )
                },
                label = {
                    Text(text = item.title, color = color)
                },
                onClick = { onNavItemClick(item) }
            )
        }
    }
}

@Composable
fun AppBottomNavigation(
    modifier: Modifier = Modifier,
    backgroundColor: Color = MaterialTheme.colorScheme.primary,
    contentColor: Color = MaterialTheme.colorScheme.onPrimary,
    elevation: Dp = BottomNavigationDefaults.Elevation,
    content: @Composable RowScope.() -> Unit,
) {
    BottomNavigation(
        modifier = modifier,
        backgroundColor = backgroundColor,
        contentColor = contentColor,
        elevation = elevation,
        content = content
    )
}