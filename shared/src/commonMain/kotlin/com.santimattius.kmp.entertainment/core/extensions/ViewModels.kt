package com.santimattius.kmp.entertainment.core.extensions

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import org.koin.compose.LocalKoinScope
import org.koin.core.parameter.ParametersDefinition
import kotlin.reflect.KClass

/**
 * Create Pre Compose view model instance using koin
 *
 * @param T, View Model Type
 * @param parameters, parameters
 * @return view model instance
 */
@Composable
inline fun <reified T> koinViewModel(
    noinline parameters: ParametersDefinition? = null,
): T where T : ViewModel {
    val scope = LocalKoinScope.current
    return viewModel<T> { scope.get<T>(parameters = parameters) }
}