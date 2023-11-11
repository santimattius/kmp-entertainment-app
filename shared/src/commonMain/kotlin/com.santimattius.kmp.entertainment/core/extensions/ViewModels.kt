package com.santimattius.kmp.entertainment.core.extensions

import androidx.compose.runtime.Composable
import moe.tlaster.precompose.viewmodel.ViewModel
import moe.tlaster.precompose.viewmodel.viewModel
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
fun <T> koinViewModel(
    modelClass: KClass<T>,
    parameters: ParametersDefinition? = null,
): T where T : ViewModel {
    val scope = LocalKoinScope.current
    return viewModel(modelClass) { scope.get(clazz = modelClass, parameters = parameters) }
}