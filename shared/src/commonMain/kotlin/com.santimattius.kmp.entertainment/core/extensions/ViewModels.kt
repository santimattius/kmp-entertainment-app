package com.santimattius.kmp.entertainment.core.extensions

import androidx.compose.runtime.Composable
import moe.tlaster.precompose.viewmodel.ViewModel
import moe.tlaster.precompose.viewmodel.viewModel
import org.koin.compose.LocalKoinScope
import org.koin.core.parameter.ParametersDefinition

/**
 * Create Pre Compose view model instance using koin
 *
 * @param T, View Model Type
 * @param parameters, parameters
 * @return view model instance
 */
@Composable
inline fun <reified T : ViewModel> koinViewModel(noinline parameters: ParametersDefinition? = null): T {
    val scope = LocalKoinScope.current
    return viewModel { scope.get(parameters = parameters) }
}