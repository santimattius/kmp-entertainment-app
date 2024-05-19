package com.santimattius.kmp.entertainment.core.extensions

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.core.parameter.ParametersDefinition

/**
 * Retrieves a ViewModel instance from the Koin dependency injection framework.
 *
 * @param parameters Optional parameters to be used to resolve
 *
 **/
@OptIn(KoinExperimentalAPI::class)
@Composable
inline fun <reified T> koinViewModel(
    noinline parameters: ParametersDefinition? = null,
): T where T : ViewModel {
//    val scope = LocalKoinScope.current
//    return viewModel<T> { scope.get<T>(parameters = parameters) }
    return org.koin.compose.viewmodel.koinViewModel(parameters = parameters)
}