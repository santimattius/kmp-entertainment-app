package com.santimattius.kmp.entertainment.core.extensions

import androidx.compose.runtime.Composable
import moe.tlaster.precompose.viewmodel.ViewModel
import moe.tlaster.precompose.viewmodel.viewModel
import org.koin.compose.koinInject
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
    val newViewModelInstance = koinInject<T>(parameters = parameters)
    return viewModel { newViewModelInstance }
}