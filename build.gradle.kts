@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    // this is necessary to avoid the plugins to be loaded multiple times
    // in each subproject's classloader
    alias(libs.plugins.kotlin.multiplatform) apply false
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.compose.multiplatform) apply false
    alias(libs.plugins.cocoa.pods) apply false
    alias(libs.plugins.compose.compiler) apply false
    alias(libs.plugins.kotlin.serialization) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.room) apply false
}
true

buildscript {
    dependencies {
        classpath(libs.buildkonfig.gradle.plugin)
    }
}

allprojects {
    configurations.all {
        //TODO: Temporal resolution
        resolutionStrategy {
            force("androidx.compose.material:material-ripple:1.7.0-alpha05")
        }
    }
}