package com.santimattius.kmp.entertainment.core.ui.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import com.santimattius.kmp.entertainment.navigation.NavItem
import com.santimattius.kmp.entertainment.navigation.toRoute

@Composable
fun AppBottomNavigation(
    bottomNavOptions: List<NavItem>,
    currentRoute: String,
    onNavItemClick: (NavItem) -> Unit,
) {
    AppBottomNavigation {
        bottomNavOptions.forEach { item ->
            val isSelected = currentRoute.contains(item.feature.toRoute())
            NavigationBarItem(
                selected = isSelected,
                icon = {
                    Icon(
                        imageVector = item.icon(isSelected),
                        contentDescription = item.title,
                    )
                },
                label = {
                    Text(text = item.title)
                },
                onClick = { onNavItemClick(item) }
            )
        }
    }
}

@Composable
fun AppBottomNavigation(
    modifier: Modifier = Modifier,
    elevation: Dp = NavigationBarDefaults.Elevation,
    content: @Composable RowScope.() -> Unit,
) {
    NavigationBar(
        modifier = modifier,
        tonalElevation = elevation,
        content = content
    )
}