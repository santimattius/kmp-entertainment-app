package com.santimattius.kmp.entertainment.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.santimattius.kmp.entertainment.Navigator
import org.koin.compose.navigation3.koinEntryProvider
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
fun AppNavigation(
    modifier: Modifier = Modifier,
    navigator: Navigator,
) {
    val koinEntryProvider = koinEntryProvider()

    NavDisplay(
        modifier = modifier,
        backStack = navigator.backStack,
        onBack = { navigator.onUpClick() },
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator(),
        ),
        entryProvider = koinEntryProvider
    )
}