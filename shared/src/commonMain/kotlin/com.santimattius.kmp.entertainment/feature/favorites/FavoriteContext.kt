package com.santimattius.kmp.entertainment.feature.favorites


import com.santimattius.kmp.entertainment.di.platformModule
import com.santimattius.kmp.entertainment.di.sharedModules
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.koinApplication
import org.koin.dsl.module

/**
 * create for example using KoinContext
 */
object FavoriteContext {

    val koinApp = koinApplication {
        modules(favoriteModule, platformModule, sharedModules)
    }
}

private val favoriteModule = module {
    viewModelOf(::FavoriteViewModel)
}