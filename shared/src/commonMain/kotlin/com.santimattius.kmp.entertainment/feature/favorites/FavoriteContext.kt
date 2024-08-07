package com.santimattius.kmp.entertainment.feature.favorites


import com.santimattius.kmp.entertainment.di.platformModule
import com.santimattius.kmp.entertainment.di.sharedModules
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.koinApplication
import org.koin.dsl.module

object FavoriteContext {

    val koinApp = koinApplication {
        modules(favoriteModule, platformModule, sharedModules)
    }
}

val favoriteModule = module {
    viewModelOf(::FavoriteViewModel)
}