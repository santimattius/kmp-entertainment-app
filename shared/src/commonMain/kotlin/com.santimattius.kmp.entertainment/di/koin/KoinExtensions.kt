package com.santimattius.kmp.entertainment.di.koin

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import org.koin.compose.LocalKoinApplication
import org.koin.compose.LocalKoinScope
import org.koin.core.Koin
import org.koin.core.annotation.KoinInternalApi
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.koinApplication

@OptIn(KoinInternalApi::class)
@Composable
fun CustomKoinApplication(
    application: KoinAppDeclaration, //Better to directly use KoinConfiguration class
    content: @Composable () -> Unit
) {
    val koin = rememberKoinApplication(application)
    CompositionLocalProvider(
        LocalKoinApplication provides koin,
        LocalKoinScope provides koin.scopeRegistry.rootScope,
        content = content
    )
}

@Composable
@KoinInternalApi
inline fun rememberKoinApplication(noinline koinAppDeclaration: KoinAppDeclaration): Koin {
    val wrapper = remember(koinAppDeclaration) {
        KoinApplicationLoader(koinApplication(koinAppDeclaration))
    }
    return wrapper.koin ?: error("Koin context has not been initialized in rememberKoinApplication")
}
