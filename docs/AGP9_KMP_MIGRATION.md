# Full migration to the Android-KMP plugin (AGP 9)

This document describes the current state of the project with respect to Android Gradle Plugin (AGP) 9 and the steps to complete the migration to the **Android-KMP** plugin (`com.android.kotlin.multiplatform.library`) once KSP supports it.

---

## Current state

The project is set up to build with **AGP 9.0.0** and **Gradle 9.1.0** using a **temporary bypass** that allows the legacy Android plugins to be used together with Kotlin Multiplatform.

### Current configuration

| Component | Status |
|-----------|--------|
| **AGP** | 9.0.0 (`gradle/libs.versions.toml`) |
| **Gradle** | 9.1.0 (`gradle/wrapper/gradle-wrapper.properties`) |
| **`shared` module** | `com.android.library` + `org.jetbrains.kotlin.multiplatform` + `androidTarget()` |
| **`androidApp` module** | `com.android.application` + `org.jetbrains.kotlin.multiplatform` + `androidTarget()` |
| **Android-KMP plugin** | Declared in the version catalog with `apply false` at root (ready to use when applicable) |

### Properties in `gradle.properties`

```properties
# Temporary bypass for KMP + AGP 9 compatibility (remove when migration is complete)
android.builtInKotlin=false
android.newDsl=false
```

These properties allow the same module to use `com.android.application` / `com.android.library` together with `org.jetbrains.kotlin.multiplatform`. **They are deprecated and will be removed in AGP 10.0** (expected in the second half of 2026).

---

## Why the full migration was not done

The migration to the **`com.android.kotlin.multiplatform.library`** plugin in the `shared` module is pending because **KSP (Kotlin Symbol Processing) does not yet support that plugin**.

- **Issue:** [google/ksp#2476 – Support `com.android.kotlin.multiplatform.library`](https://github.com/google/ksp/issues/2476)
- **Impact:** In this project, Room uses KSP to generate code for the Android target. With the Android-KMP plugin, the `kspAndroid` configuration does not exist (the plugin does not register it), so Room’s processor does not run for Android and the build fails.

Until the issue is resolved, the following must remain:

- In **shared**: `com.android.library` + `androidTarget()` and the current `android { }` block.
- The properties `android.builtInKotlin=false` and `android.newDsl=false` in `gradle.properties`.

---

## How to proceed when the KSP issue is resolved

Once KSP adds support for `com.android.kotlin.multiplatform.library`, you can complete the migration by following these steps.

### 1. Verify KSP support

- Check [issue #2476](https://github.com/google/ksp/issues/2476) and KSP release notes.
- Update the KSP version in `gradle/libs.versions.toml` to one that supports the Android-KMP plugin.
- Optionally, on a branch, apply the changes below and confirm that `./gradlew :shared:assembleDebug` and `./gradlew :androidApp:assembleDebug` succeed.

### 2. Migrate the `shared` module to the Android-KMP plugin

**2.1. Change the plugin in `shared/build.gradle.kts`**

```kotlin
// Before
alias(libs.plugins.android.library)

// After
alias(libs.plugins.android.multiplatform.library)
```

**2.2. Replace `androidTarget()` and the `android { }` block with `androidLibrary { }`**

Remove:

- The `compilerOptions { androidTarget { ... } }` block inside `kotlin { }`.
- The entire `android { ... }` block (compileSdk, namespace, sourceSets, defaultConfig, compileOptions, kotlin.jvmToolchain).

Add inside `kotlin { }`:

```kotlin
androidLibrary {
    namespace = "com.santimattius.kmp.entertainment.shared"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    compilerOptions {
        jvmTarget.set(JvmTarget.JVM_17)
    }
    androidResources {
        enable = true
    }
}
```

**2.3. Android dependencies in `shared`**

- Move `implementation(libs.androidx.core.animation)` into `sourceSets.androidMain.dependencies { }`.
- Replace `debugImplementation(libs.androidx.ui.tooling)` with the new plugin’s runtime configuration (Compose Preview / tooling):

  ```kotlin
  dependencies {
      "androidRuntimeClasspath"(libs.androidx.ui.tooling)
      // ...
  }
  ```

- **KSP / Room:** With the Android-KMP plugin, KSP typically exposes a configuration for the Android target (e.g. `kspAndroidMain` or whatever KSP documents). Update the `dependencies` block to use that name:

  ```kotlin
  add("kspAndroidMain", libs.androidx.room.compiler)  // or the name documented by KSP
  add("kspIosX64", libs.androidx.room.compiler)
  add("kspIosSimulatorArm64", libs.androidx.room.compiler)
  add("kspIosArm64", libs.androidx.room.compiler)
  ```

  If KSP still exposes `kspAndroid`, keep `add("kspAndroid", ...)`.

**2.4. Imports**

- Remove `@OptIn(ExperimentalKotlinGradlePluginApi::class)` and the `ExperimentalKotlinGradlePluginApi` import if they are no longer used.

### 3. Extract the Android entry point into an Android-only module (recommended)

With AGP 9, having both `com.android.application` and `org.jetbrains.kotlin.multiplatform` in the same module is not recommended. The recommended approach is for **androidApp** to be an Android-only module that depends on **shared**.

**3.1. Target structure**

- **androidApp:** Only `com.android.application`, no `kotlin.multiplatform`. Code under `src/main/` (not `androidMain`).
- **shared:** KMP + `com.android.kotlin.multiplatform.library` (after step 2).

**3.2. Steps**

1. Create or use the structure `androidApp/src/main/` (instead of `androidMain`).
2. Move the contents of `androidApp/src/androidMain/` into `androidApp/src/main/` (manifest, Kotlin, resources).
3. In `androidApp/build.gradle.kts`:
   - Remove `alias(libs.plugins.kotlin.multiplatform)` and `alias(libs.plugins.compose.multiplatform)` (and `compose.compiler` if only used via shared).
   - Keep `alias(libs.plugins.android.application)` and, if the app uses Compose, `alias(libs.plugins.compose.compiler)` (and any Compose dependencies the app needs).
   - Remove the `kotlin { androidTarget(); sourceSets { ... } }` block.
   - Leave only the `android { }` block and `dependencies { implementation(project(":shared")) }`.
4. Adjust `sourceSets` if needed (e.g. remove the line that pointed the manifest to `androidMain` if it now lives under `main`).
5. Update the IDE run configuration to use the `androidApp` module if it was tied to the old setup.

### 4. Remove the bypass from `gradle.properties`

Once both **shared** and **androidApp** are migrated (shared with Android-KMP, androidApp as Android-only), remove:

```properties
android.builtInKotlin=false
android.newDsl=false
```

### 5. Verification

- Run `./gradlew clean assembleDebug`.
- Run the app on an emulator or device.
- Confirm there are no remaining deprecation warnings related to `androidTarget`, the legacy `android { }` block, or the old plugins.

---

## References

- [Updating multiplatform projects with Android apps to use AGP 9 \| Kotlin](https://kotlinlang.org/docs/multiplatform/multiplatform-project-agp-9-migration.html)
- [Set up the Android Gradle Library Plugin for KMP \| Android Developers](https://developer.android.com/kotlin/multiplatform/plugin)
- [Android Gradle plugin 9.0.0 release notes](https://developer.android.com/build/releases/agp-9-0-0-release-notes)
- [KSP issue #2476 – Support `com.android.kotlin.multiplatform.library`](https://github.com/google/ksp/issues/2476)
