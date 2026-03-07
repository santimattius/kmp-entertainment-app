# KMP Entertainment App

A **Kotlin Multiplatform (KMP)** sample application for Android and iOS that showcases movies and TV shows using [TheMovieDB API](https://www.themoviedb.org/documentation/api). The UI is built with **Compose Multiplatform**, and shared logic lives in a single module consumed by both platforms.

<p align="center">
  <img width="270" src="https://github.com/santimattius/kmp-entertainment-app/blob/feature/precompose-navigation/screenshots/kmp-android.png?raw=true" alt="Android screenshot"/>
  <img width="320" src="https://github.com/santimattius/kmp-entertainment-app/blob/feature/precompose-navigation/screenshots/kmp-ios.png?raw=true" alt="iOS screenshot"/>
</p>

---

## Table of contents

- [Features](#features)
- [Tech stack](#tech-stack)
- [Project structure](#project-structure)
- [Prerequisites](#prerequisites)
- [Getting started](#getting-started)
- [Building and running](#building-and-running)
- [Navigation](#navigation)
- [Documentation](#documentation)
- [License](#license)

---

## Features

- **Shared UI** — Common Compose Multiplatform screens for Android and iOS
- **TheMovieDB integration** — Browse movies and TV shows via the official API
- **Offline-ready** — Room for local persistence and SQLite
- **Dependency injection** — Koin with Compose and ViewModel support
- **Networking** — Ktor client with content negotiation and logging
- **Navigation** — Navigation 3 (Nav3) with adaptive layouts

---

## Tech stack

| Layer        | Technology |
|-------------|------------|
| **UI**      | Compose Multiplatform, Material 3, adaptive layouts |
| **DI**      | Koin (Core, Android, Compose, Compose ViewModel, Nav3) |
| **Networking** | Ktor (core, OkHttp/Darwin, content negotiation, Kotlinx JSON, logging) |
| **Persistence** | Room, SQLite (bundled) |
| **Images**  | Coil 3 (Compose, network) |
| **Async**   | Kotlin Coroutines |
| **Config**  | BuildKonfig (build-time config) |
| **Logging** | Kermit |

**Build & tooling:** Kotlin 2.3, AGP 9.0, Gradle 9.1, KSP 2.3, Compose Multiplatform 1.10.

---

## Project structure

```
kmp-entertainment-app/
├── androidApp/          # Android application (Compose + KMP entry point)
├── shared/              # Shared KMP module (commonMain + androidMain + iosMain)
│   ├── commonMain/      # Shared UI, ViewModels, data, networking
│   ├── androidMain/      # Android-specific implementations
│   └── iosMain/         # iOS-specific implementations
├── iosApp/              # iOS app (Xcode project, consumes shared framework)
├── docs/                 # Project documentation
│   └── AGP9_KMP_MIGRATION.md
├── build.gradle.kts
├── settings.gradle.kts
└── gradle/
    └── libs.versions.toml
```

- **androidApp** — Android app; depends on `shared`, uses `androidTarget()` and Compose.
- **shared** — Kotlin Multiplatform library; Compose UI, Room, Ktor, Koin, and platform expect/actuals.
- **iosApp** — Native iOS app (Swift/Xcode) that links the shared framework produced by `shared`.

---

## Prerequisites

- **macOS** — Required for building and running the iOS app (Xcode and Simulator).
- **JDK 17** — Used for Android and Gradle.
- **Android Studio** — Latest stable (or [Android Studio release notes](https://developer.android.com/studio/releases) for compatibility).
- **Xcode** — Latest stable from the [Mac App Store](https://apps.apple.com/us/app/xcode/id497799835).
- **Kotlin Multiplatform Mobile plugin** — [Install in Android Studio / IntelliJ](https://plugins.jetbrains.com/plugin/14936-kotlin-multiplatform-mobile).
- **CocoaPods** — For iOS dependency management. See [Kotlin CocoaPods](https://kotlinlang.org/docs/native-cocoapods.html).

### Verifying your environment

Use [KDoctor](https://github.com/Kotlin/kdoctor) to check that your machine is ready for KMP development:

1. **Install KDoctor** (via [Homebrew](https://brew.sh/)):

   ```bash
   brew install kdoctor
   ```

2. **Run the diagnostic**:

   ```bash
   kdoctor
   ```

   A successful setup looks like:

   ```text
   Environment diagnose (to see all details, use -v option):
   [✓] Operation System
   [✓] Java
   [✓] Android Studio
   [✓] Xcode
   [✓] Cocoapods

   Conclusion:
     ✓ Your system is ready for Kotlin Multiplatform Mobile development!
   ```

   If something fails, KDoctor will point out what to fix.

---

## Getting started

### 1. Clone the repository

```bash
git clone https://github.com/santimattius/kmp-entertainment-app.git
cd kmp-entertainment-app
```

### 2. Configure the API key

The app uses [TheMovieDB API](https://www.themoviedb.org/documentation/api). Create a `local.properties` file in the project root (if it does not exist) and add your API key:

```properties
api_key=YOUR_THEMOVIEDB_API_KEY
```

Replace `YOUR_THEMOVIEDB_API_KEY` with your key from [TheMovieDB](https://www.themoviedb.org/settings/api).

---

## Building and running

### Android

```bash
./gradlew :androidApp:assembleDebug
```

Install and run on a device or emulator from Android Studio, or:

```bash
./gradlew :androidApp:installDebug
```

### iOS

1. Build the shared framework and open the iOS app in Xcode:

   ```bash
   ./gradlew :shared:embedAndSignAppleFrameworkForXcode
   ```

2. Open `iosApp/iosApp.xcworkspace` in Xcode (use the `.xcworkspace`, not the `.xcodeproj`).
3. Select a simulator or device and run the app.

---

## Navigation

This project uses **Navigation 3 (Nav3)** for Compose Multiplatform. For alternative navigation implementations:

- **Navigation 2** — See the [nav2](https://github.com/santimattius/kmp-entertainment-app/tree/nav2) branch.
- **Voyager** — See the [feature/voyager](https://github.com/santimattius/kmp-entertainment-app/tree/feature/voyager) branch.

---

## Documentation

- **[AGP 9 & Android-KMP migration](docs/AGP9_KMP_MIGRATION.md)** — Current state with AGP 9, temporary compatibility flags, and steps to complete the migration to the Android-KMP plugin once KSP supports it.

---

## Dependencies

Main versions are centralized in `gradle/libs.versions.toml`. Key entries:

| Dependency area | Version / note |
|----------------|----------------|
| Android Gradle Plugin | 9.0.0 |
| Kotlin | 2.3.0 |
| KSP | 2.3.0 |
| Compose Multiplatform | 1.10.0 |
| Room | 2.8.4 |
| Ktor | 3.3.3 |
| Koin | 4.2.0-beta2 |
| Coil | 3.3.0 |

For the full list, see `gradle/libs.versions.toml`.

---

## License

This project is provided as-is for reference and learning. Use of TheMovieDB is subject to [TheMovieDB’s terms of use](https://www.themoviedb.org/documentation/api/terms-of-use).
